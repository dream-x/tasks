# README

# Middle Crud
A complete implementation of a "Vehicles CRUD" in Rails.

- Ruby version

  _*v3.0.4*_

- Dependencies

  - Postgresql
  - rails v7.0.7

Run `bundle install` on terminal


- ## Configuration
  - ### Production
    -  Create new user `middle_crud` in psql
    -  Add `MIDDLE_CRUD_DATABASE_PASSWORD` env variable in your production environment

* Database creation

  - After pgsql config, run following on terminal
    ```sh
      rails db:create
      rails db:migrate
      rails db:seed
    ```

* How to run the test suite
  - run on terminal `rspec`

* Run rails server after successful setup using following command:
  ```sh
    rails s
  ```
----
## Endpoints

#### Swagger Documentation

|HTTP Method|URL|Description|
|---|---|---|
|`GET`|http://localhost:3000/api-docs | Swagger UI page |

#### Vehicles CRUD

|HTTP Method|URL|Description|
|---|---|---|
|`GET`|http://localhost:3000/api/v1/vehicles | Get vehicles json |
|`GET`|http://localhost:3000/api/v1/vehicles.csv | Get vehicles csv data |
|`POST`|http://localhost:3000/api/v1/vehicles | Create new Vehicle |
|`PUT`|http://localhost:3000/api/v1/vehicles/{id} | Update Vehicle by ID |
|`GET`|http://localhost:3000/api/v1/vehicles/{id} | Get Vehicle by ID |
|`DELETE`|http://localhost:3000/api/v1/vehicles/{id} | Delete Vehicle by ID |

