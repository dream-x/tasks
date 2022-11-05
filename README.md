# PREMIER LEAGUE API

This applications display data of the english premier leagues team. It shows the number of games played, won, lost, drawn, and goal difference.

## Built With

- Ruby 3.1.2
- Ruby on rails 7.0.3
- postgresql (last version)

## Getting Started

To get a local copy up and running follow these simple example steps.

### Prerequisites

- A text editor(preferably Visual Studio Code)

### Install

- Ruby
- Ruby on Rails
- PostgresSQL
- Rspec


### Setup

Install gems with:

```
bundle install
```

Setup database with:

```
rails db:create
rails db:migrate
```

### Usage

Start server with:

```
rails server
```

Visit http://localhost:3000/ in your browser.

### Run tests


Install rspec with:

```
bundle install
```

and

```
rails generate rspec:install
```

run the test with:

```
rspec spec
```

## API endpoints

| Endpoint | Functionality |
| -------- | ------------- |
| POST /api/v1/teams | Create a team |
| GET /api/v1/teams | Get all teams |
| GET /api/v1/teams/:id | Get a team |
| PUT /api/v1/teams/:id | Update a team |
| DELETE /api/v1/teams/:id | Delete a team |


## Sample csv output

    ```
    id,name,games_played,games_won,games_drawn,games_lost,goal_difference
    1,Arsenal,10,5,3,2,10
    3,Chelsea,10,5,3,2,10
    5,Tottenham,15,5,5,5,10
    6,Nottingham,15,5,5,5,10
    2,Burnley,15,5,5,5,10

    ```


