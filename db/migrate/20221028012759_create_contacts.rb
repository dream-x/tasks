class CreateContacts < ActiveRecord::Migration[7.0]
  def change
    create_table :contacts do |t|
      t.string :name
      t.string :job
      t.integer :age

      t.timestamps
    end
  end
end
