class Api::V1::TeamsController < ApplicationController

    def index
        send_data Team.to_csv(params[:make]), filename: "teams-#{Time.now}.csv"

    end

    def show
        @team = Team.find(params[:id])
        render json: @team
    end


    def create
        @team = Team.new(team_params)
        if @team.save
            render json: @team, status: :created
        else
            render json: @team.errors, status: :unprocessable_entity
        end

    end

    def update
        @team = Team.find(params[:id])
        if @team.update(team_params)
            render json: @team, status: :ok
        else
            render json: @team.errors, status: :unprocessable_entity
        end
    end

    def destroy
        @team = Team.find(params[:id])
        if @team.destroy
            render json: {message: "Team deleted"}
        else
            render json: @team.errors, status: :unprocessable_entity
        end
    end

    private

    def team_params
        params.require(:team).permit(:name, :games_played, :games_won, :games_drawn, :games_lost, :goal_difference)
    end

end
