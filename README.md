# README
Middle crud is a simple api service application which stores product information
and expose apis to add update delete products.

**Project dependencies**
- **ruby** 3.0.4
- **postgresql** 9.3 or above
- **rails** 7.0.7

**How to run**
* Make sure to install psql, ruby 
* Create psql user with password on psql
* Set ENV vars for **DB_USER** and **DB_PASS**
* Go to project root folder
* Run these commands
* * bundle install (`gem install bundler -v 2.2.33` if bundle is not installed)
* * bundle exec rails db:create
* * bundle exec rails db:migrate
* * rails s (it will start your rails server)

**How to call apis**

* Install Postman
* this is the endpoint after root url: `api/v1/products`
* check products api routes by running `bundle exec rails routes` in root project

**How to run test suite**

* **Rspec** is configured with the combination of **factory bot** 
* Go to root directory and run: `rspec` in console, it will execute the specs
* You can also execute `rspec spec/controllers/api/v1/products_controller_spec.rb` directly
