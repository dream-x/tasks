class API::V1::TeamsController < ApplicationController

    def index
        @teams = Team.all
        render_to do |format|
            format.csv { send_data @teams.to_csv, filename: "teams-#{Date.today}.csv" }
        end
    end

    def show
        @team = Team.find(params[:id])
        render json: @team
    end


    def create
        @team = Team.new(team_params)
        @team.save
    end

    def update
        @team = Team.find(params[:id])
        @team.update(team_params)
    end

    def destroy
        @team = Team.find(params[:id])
        @team.destroy
    end

    private

    def team_params
        params.require(:team).permit(:name, :games_played, :games_won, :games_drawn, :games_lost, :goal_difference)
    end

end
