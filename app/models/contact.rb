require 'csv'

class Contact < ApplicationRecord
    def self.to_csv
        attrs = %w{id name age job}

        CSV.generate(headers: true) do |csv|
            csv << attrs

            all.find_each do |contact|
                csv << attrs.map{ |attr| contact.send(attr)}
            end
        end
    end
end
