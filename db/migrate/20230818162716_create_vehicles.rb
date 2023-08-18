class CreateVehicles < ActiveRecord::Migration[7.0]
  def change
    create_table :vehicles do |t|
      t.integer :number, null: false
      t.string :chasis, null: false
      t.string :description
      t.string :company, null: false
      t.index :number, unique: true
      t.index :chasis, unique: true
      t.timestamps
    end
  end
end
