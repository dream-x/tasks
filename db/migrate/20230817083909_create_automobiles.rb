class CreateAutomobiles < ActiveRecord::Migration[7.0]
  def change
    create_table :automobiles do |t|
      t.string :company
      t.string :model
      t.integer :year
      t.string :vehicle_type
      t.string :color
      t.string :fuel_type
      t.integer :mileage
      t.decimal :price
      t.string :condition
      t.string :safety_rating
      t.string :license_plate_number

      t.timestamps
    end
  end
end
