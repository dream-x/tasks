require 'csv'

class Team < ApplicationRecord
    validates :name, presence: true, uniqueness: true
    validates :games_played, presence: true, numericality: { only_integer: true }
    validates :games_won, presence: true, numericality: { only_integer: true }
    validates :games_drawn, presence: true, numericality: { only_integer: true }
    validates :games_lost, presence: true, numericality: { only_integer: true }
    validates :goal_difference, presence: true, numericality: { only_integer: true }

    def self.to_csv(make)
        attributes = %w{id name games_played games_won games_drawn games_lost goal_difference}
        CSV.generate(headers: true) do |csv|
            csv << attributes
            all.each do |team|
                csv << attributes.map{ |attr| team.send(attr) }
            end
        end
    end
end
