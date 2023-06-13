# Import necessary libraries
from flask import Flask, request, send_file, jsonify
from flask_restful import Resource, Api
from flask_pymongo import PyMongo, ObjectId
from flask_marshmallow import Marshmallow
from marshmallow import Schema, fields
from pymongo.errors import PyMongoError
from werkzeug.exceptions import NotFound
import pandas as pd

# Create a new Flask application
app = Flask(_name_)

# Set the MONGO_URI configuration variable
app.config["MONGO_URI"] = "mongodb://localhost:27017/mydatabase"

# Initialize PyMongo to work with MongoDBs
mongo = PyMongo(app)

# Initialize a Flask-Restful API
api = Api(app)

# Initialize Flask-Marshmallow for request validation
ma = Marshmallow(app)

# Specify which collection to use in MongoDB
db = mongo.db.myCollection

# Define the schema for an item. This will be used to validate the item data


class ItemSchema(ma.Schema):
    title = fields.Str(required=True)  # Each item must have a title
    description = fields.Str()  # Description is optional
    price = fields.Float(required=True)  # Each item must have a price


# Create an instance of the ItemSchema
item_schema = ItemSchema()

# Handle PyMongo errors


@app.errorhandler(PyMongoError)
def handle_mongo_error(error):
    response = jsonify({"message": "A database error occurred."})
    response.status_code = 500
    return response

# Handle all other errors


@app.errorhandler(Exception)
def handle_unknown_error(error):
    response = jsonify({"message": "An unknown error occurred."})
    response.status_code = 500
    return response

# Define the item resource


class Item(Resource):
    # Get an item by ID
    def get(self, item_id):
        item = db.find_one({"_id": ObjectId(item_id)})
        if item is None:
            raise NotFound("Item not found")
        item['_id'] = str(item['_id'])
        return item

    # Update an item by ID
    def put(self, item_id):
        # Validate the request data
        item = item_schema.load(request.get_json())
        result = db.replace_one({"_id": ObjectId(item_id)}, item)
        if result.matched_count == 0:
            raise NotFound("Item not found")
        return {"message": "Item updated successfully"}, 200

    # Delete an item by ID
    def delete(self, item_id):
        result = db.delete_one({"_id": ObjectId(item_id)})
        if result.deleted_count == 0:
            raise NotFound("Item not found")
        return {"message": "Item deleted successfully"}, 200

# Define the resource for the item list


class ItemList(Resource):
    # Get all items as a CSV file
    def get(self):
        items = list(db.find())
        for item in items:
            item['_id'] = str(item['_id'])
        df = pd.DataFrame(items)
        df.to_csv('items.csv', index=False)
        return send_file('items.csv',
                         mimetype='text/csv',
                         attachment_filename='items.csv',
                         as_attachment=True)

    # Create a new item
    def post(self):
        # Validate the request data
        item = item_schema.load(request.get_json())
        result = db.insert_one(item)
        return {"_id": str(result.inserted_id)}, 201


# Add the resources to the API
api.add_resource(Item, '/item/<item_id>')
api.add_resource(ItemList, '/items')

# Start the Flask application
if _name_ == '_main_':
    app.run(debug=True)
