class HomesController < ApplicationController
    def index
        render json: {message: "Welcome to Premier League CRUD API"}
    end
end