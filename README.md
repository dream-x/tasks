# README

All the api end points and the repective funtionalities:

* List all products:

    URL: GET /products
    Controller Action: index in ProductsController
    Returns a JSON array containing all products.

* Get a specific product by ID:

    URL: GET /products/:id
    Controller Action: show in ProductsController
    Returns a JSON object representing the specific product.

* Create a new product:

    URL: POST /products
    Controller Action: create in ProductsController
    Payload: JSON object containing product attributes (name, description, price).
    Creates a new product and returns the created product's JSON representation.

* Update a product:

    URL: PUT /products/:id
    Controller Action: update in ProductsController
    Payload: JSON object containing updated product attributes (name, description, price).
    Updates the specified product and returns the updated product's JSON representation.

* Delete a product:

    URL: DELETE /products/:id
    Controller Action: destroy in ProductsController
    Deletes the specified product.

* Export all products as CSV:

    URL: GET /products.csv
    Controller Action: index in ProductsController
    Returns a CSV file containing all products in CSV format.