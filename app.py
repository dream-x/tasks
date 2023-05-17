import bson
import csv

from bson import ObjectId, json_util
from flask import Flask, request, jsonify, make_response
from pymongo import MongoClient


app = Flask(__name__)
client = MongoClient('localhost', 27017)
db = client['crudDB']
collection = db['crud']


@app.route('/create_user', methods=["POST"])
def create_user():
    email = request.json["email"]
    password = request.json["password"]

    check = collection.find_one({"email": email})
    if check:
        return jsonify(message="User exists")
    else:
        user_info = dict(email=email,
                         password=password,
                         )
        collection.insert_one(user_info)
    return jsonify(message="User created",
                   user=email,
                   password=password
                   ), 201


@app.route('/users/<user_id>', methods=['GET'])
def get_user(user_id):
    try:
        user = collection.find_one({'_id': ObjectId(user_id)})
        return make_response(json_util.dumps({"result": user})), 201
    except bson.errors.InvalidId:
        return jsonify(message="User not found"), 404


@app.route('/users', methods=['GET'])
def get_all_items():
    users = list(collection.find())
    fieldnames = ['_id', 'email', 'password']
    csv_file_path = 'users.csv'

    with open(csv_file_path, 'w', newline='') as csvfile:
        writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
        writer.writeheader()
        for item in users:
            item_dict = {field: item[field] for field in fieldnames}
            writer.writerow(item_dict)

    return jsonify(message="Users retrieved and saved to CSV")


@app.route('/update_user/<user_id>', methods=['PUT'])
def update_user(user_id):
    user = {
        "email": request.json.get("email"),
        "password": request.json.get("password")
    }

    result = collection.update_one({"_id": ObjectId(user_id)}, {"$set": user})

    if result.modified_count == 0:
        return jsonify(message="User not found"), 404

    return jsonify(message="User updated")


@app.route('/delete_user/<user_id>', methods=['DELETE'])
def delete_user(user_id):
    result = collection.delete_one({"_id": ObjectId(user_id)})

    if result.deleted_count == 0:
        return jsonify(message="User not found"), 404

    return jsonify(message="User deleted")


if __name__ == '__main__':
    app.run(debug=True)
