require 'csv'
class VehicleService
  def initialize(vehicles)
    @vehicles = vehicles
  end

  def generate_csv
    CSV.generate(headers: true) do |csv|
      csv << ['Number', 'Chasis', 'Description', 'Company']
      @vehicles.each do |vehicle|
        csv << [vehicle.number, vehicle.chasis, vehicle.description, vehicle.company]
      end
    end
  end
end
