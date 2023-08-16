class Product < ApplicationRecord
  validates :name, presence: true

  def self.as_csv
    CSV.generate(headers: true) do |csv|
      csv << ['ID', 'Name', 'Description']
      all.each do |product|
        csv << [product.id, product.name, product.description]
      end
    end
  end
end
