class AutomobilesController < ApplicationController
  before_action :get_vehicle, except: [:create, :index]

  def index
    vehicles = Automobile.all
    send_data vehicles_to_csv(vehicles),
              filename: "All Vehicles-#{Date.today}.csv",
              type: 'text/csv',
              disposition: 'attachment'
  end

  def show
    if @vehicle.present?
      render json: { vehicle: @vehicle }
    else
      render json: { message: "Vehicle with ID #{params["id"]} does not exist..." }
    end
  end

  def create
    vehicle = Automobile.new(vehicle_params)
    if vehicle.save!
      render json: { vehicle: vehicle }
    end
  end

  def update
    if @vehicle.present?
      @vehicle.update!(vehicle_params)
      render json: { vehicle: @vehicle }
    else
      render json: { error: "Vehicle with ID #{params["id"]} does not exist..."}
    end
  end

  def destroy
    if @vehicle.present?
      @vehicle.destroy!
      render json: { message: "Vehicle deleted successfully..." }
    else
      render json: { message: "Vehicle with ID #{params["id"]} does not exist..."}
    end
  end

  private
  def vehicle_params
    params.require(:automobile).permit(:company, :model, :year, :vehicle_type, :color, :fuel_type, :mileage, :price, :condition, :safety_rating, :license_plate_number)
  end

  def get_vehicle
    @vehicle = Automobile.find_by(id: params[:id])
  end

  def vehicles_to_csv(vehicles)
    require 'csv'

    CSV.generate(headers: true) do |csv|
      csv << ['Company', 'Model', 'Year', 'Vehicle Type', 'Color', 'Fuel Type', 'Mileage', 'Price', 'Condition', 'Safety Rating', 'License Plate Number' ]

      vehicles.each do |vehicle|
        csv << [vehicle.company, vehicle.model, vehicle.year, vehicle.vehicle_type, vehicle.color, vehicle.fuel_type, vehicle.mileage, vehicle.price, vehicle.condition, vehicle.safety_rating, vehicle.license_plate_number ]
      end
    end
  end

end
