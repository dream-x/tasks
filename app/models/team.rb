
class Team < ApplicationRecord
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
