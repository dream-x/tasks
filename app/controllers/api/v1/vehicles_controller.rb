module Api
  module V1
    class VehiclesController < ApplicationController
      before_action :set_vehicle, only: [:show, :update, :destroy]

      def index
        @vehicles = Vehicle.all

        if params[:format] == 'csv'
          csv_data = VehicleService.new(@vehicles).generate_csv
          send_data csv_data, filename: "vehicles.csv", type: "text/csv"
        else
          render json: @vehicles
        end
      end

      def show
        render json: @vehicle
      end

      def create
        vehicle = Vehicle.new(vehicle_params)

        if(vehicle.save)
          render json: vehicle, status: :created
        else
          render json: vehicle.errors.full_messages, status: :bad_request
        end
      end

      def destroy
        @vehicle.destroy

        render plain: "Deleted!"
      end

      def update
        if @vehicle.update(vehicle_params)
          render json: @vehicle
        else
          render json: @vehicle.errors.full_messages, status: :unprocessable_entity
        end
      end

      private
      def vehicle_params
        params.require(:vehicle).permit(:chasis, :number, :description, :company)
      end

      def set_vehicle
        @vehicle = Vehicle.find(params[:id])
      end
    end
  end
end
