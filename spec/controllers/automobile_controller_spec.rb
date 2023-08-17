require 'rails_helper'

RSpec.describe AutomobilesController, type: :controller do

  describe "GET #index" do
    it "returns a list of vehicles" do
      FactoryBot.create(:automobile)
      FactoryBot.create(:automobile)
      FactoryBot.create(:automobile)

      get :index
      expect(response.content_type).to eq 'text/csv'
      expect(response.headers['Content-Disposition']).to include('attachment')
      expect(response).to have_http_status(:ok)
    end
  end

  describe "GET #show" do
    it "returns a specific vehicle" do
      vehicle = FactoryBot.create(:automobile)
      get :show, params: { id: vehicle.id }
      expect(response).to have_http_status(:success)
      expect(JSON.parse(response.body)["vehicle"]["id"]).to eq(vehicle.id)
    end

    it "returns an error message for non-existent vehicle" do
      get :show, params: { id: 999 }
      expect(response).to have_http_status(:ok)
      expect(JSON.parse(response.body)["message"]).to include("does not exist")
    end
  end

  describe "POST #create" do
    it "creates a new vehicle" do
      vehicle_params = {
        company: "{ Faker::Company.name }",
        model: "{ Faker::Vehicle.model }",
        year: "{ Faker::Number.between(from: 2000, to: 2023) }",
        vehicle_type: "{ %w[sedan suv truck].sample }",
        color: "{ Faker::Vehicle.color }",
        fuel_type: "{ %w[gasoline diesel electric].sample }",
        mileage: "{ Faker::Number.between(from: 1000, to: 100000) }",
        price: "{ Faker::Number.decimal(l_digits: 5, r_digits: 2) }",
        condition: "{ %w[new used].sample }",
        safety_rating: "{ Faker::Number.between(from: 1, to: 5) }",
        license_plate_number: "{ Faker::Vehicle.license_plate }"
      }
      post :create, params: { automobile: vehicle_params }
      expect(response).to have_http_status(:success)
      expect(JSON.parse(response.body)["vehicle"]["company"]).to eq(vehicle_params[:company])
    end
  end

  describe "PATCH #update" do
    it "updates an existing vehicle" do
      vehicle = FactoryBot.create(:automobile)
      new_model = "New Model"
      patch :update, params: { id: vehicle.id, automobile: { model: new_model } }
      expect(response).to have_http_status(:success)
      expect(JSON.parse(response.body)["vehicle"]["model"]).to eq(new_model)
    end

    it "returns an error message for non-existent vehicle update" do
      patch :update, params: { id: 999, automobile: { model: "New Model" } }
      expect(response).to have_http_status(:ok)
      expect(JSON.parse(response.body)["error"]).to include("does not exist")
    end
  end

  describe "DELETE #destroy" do
    it "deletes an existing vehicle" do
      vehicle = FactoryBot.create(:automobile)
      delete :destroy, params: { id: vehicle.id }
      expect(response).to have_http_status(:success)
      expect(JSON.parse(response.body)["message"]).to include("deleted successfully")
    end

    it "returns an error message for non-existent vehicle deletion" do
      delete :destroy, params: { id: 999 }
      expect(response).to have_http_status(:ok)
      expect(JSON.parse(response.body)["message"]).to include("does not exist")
    end
  end
end
