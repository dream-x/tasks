require 'rails_helper'

RSpec.describe "Teams", type: :request do

    before :each do
        Team.create(name: "Arsenal", games_played: 10, games_won: 5, games_drawn: 3, games_lost: 2, goal_difference: 10)
        Team.create(name: "Manchester United", games_played: 10, games_won: 5, games_drawn: 3, games_lost: 2, goal_difference: 10)
    end

    it "returns a csv file" do
        get "/api/v1/teams"
        expect(response.content_type).to eq("text/csv")
    end

    describe "GET/show" do
        let (:team) { Team.first }

        it "checks action for a team" do
            get "/api/v1/teams/#{team.id}"
        expect(response).to have_http_status(:success)
        end

    end

    describe "POST/create" do
        it "creates a team" do
            post "/api/v1/teams", params: {team: {name: "Chelsea", games_played: 10, games_won: 5, games_drawn: 3, games_lost: 2, goal_difference: 10}}
            expect(response).to have_http_status(:success)
        end
    end



    describe "PUT/update" do
        let (:team) { Team.first }

        it "updates a team" do
            put "/api/v1/teams/#{team.id}", params: {team: {name: "Chelsea", games_played: 13, games_won: 10, games_drawn: 1, games_lost: 2, goal_difference: 15}}
            expect(response).to have_http_status(:success)
        end
    end

    it "deletes a team" do
        delete "/api/v1/teams/#{Team.first.id}"
        expect(response).to have_http_status(:success)
    end

end