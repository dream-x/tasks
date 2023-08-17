class AutomobilesController < ApplicationController
  before_action :get_vehicle, except: [:create, :index]

  def index
    vehicles = Automobile.all
    render json: { vehicle: vehicles }
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

end
