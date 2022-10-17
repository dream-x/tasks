require 'csv'

class Contact < ApplicationRecord
  def self.to_csv
    attributes = %w{id email notes}

    CSV.generate(headers: true) do |csv|
      csv << attributes

      all.find_each do |x|
        csv << attributes.map{ |attr| x.send(attr) }
      end
    end
  end
end
