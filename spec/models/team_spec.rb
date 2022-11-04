require 'rails_helper'

RSpec.describe "Teams", type: :model do
    describe "Valid Team" do
        let(:team) { Team.new(name: "Arsenal", games_played: 10, games_won: 5, games_drawn: 3, games_lost: 2, goal_difference: 10) }

        it "is valid with valid attributes" do
            expect(team).to be_valid
        end
    end

    describe "invalid Team" do
        let(:team) { Team.new(name: nil, games_played: 10, games_won: "none", games_drawn: 3, games_lost: 2, goal_difference: nil) }

        it "is not valid without a name" do
            expect(team).to_not be_valid
        end
        it "is not valid without goal difference" do
            expect(team).to_not be_valid
        end
        it "is not valid without games won" do
            expect(team).to_not be_valid
        end
    end


end