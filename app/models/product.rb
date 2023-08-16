class Product < ApplicationRecord
  def self.to_csv
    CSV.generate(headers: true) do |csv|
      csv << ["ID", "Name", "Description", "Price"]
      all.each do |product|
        csv << [product.id, product.name, product.description, product.price]
      end
    end
  end
end
