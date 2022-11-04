class CreateTeams < ActiveRecord::Migration[7.0]
  def change
    create_table :teams do |t|
      t.string :name
      t.integer :games_played
      t.integer :games_won
      t.integer :games_drawn
      t.integer :games_lost
      t.integer :goal_difference

      t.timestamps
    end
  end
end
