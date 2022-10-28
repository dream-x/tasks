require 'csv'

class Contact < ApplicationRecord
    # After installing the to_csv-rails gem and gives us the ability to require 'csv' to use its..
    # constant CSV, it's been used in the self method to be applied on each record is there in the..
    # database table so that when the request made in the format csv, it collects all of the Model's..
    # records and send the data in the controller respond_to method. (See contacts_controller.rb file)
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
