class CreateIdempotences < ActiveRecord::Migration[7.0]
  def change
    create_table :idempotences do |t|
      t.string :key

      t.timestamps
    end
  end
end
