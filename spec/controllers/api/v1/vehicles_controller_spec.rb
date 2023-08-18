require 'rails_helper'

RSpec.describe Api::V1::VehiclesController, type: :controller do
  describe "GET #index" do
    it "returns JSON response with success" do
      get :index, params: { format: :json }
      expect(response).to have_http_status(:success)
      expect(response.content_type).to eq("application/json; charset=utf-8")
    end

    it "returns CSV response with success" do
      get :index, params: { format: :csv }
      expect(response).to have_http_status(:success)
      expect(response.content_type).to eq("text/csv")
      expect(response.headers['Content-Disposition']).to match(/vehicles\.csv/)
    end
  end

  describe "GET #show" do
    it "returns JSON response with success" do
      vehicle = FactoryBot.create(:vehicle)
      get :show, params: { id: vehicle.id, format: :json }
      expect(response).to have_http_status(:success)
      expect(response.content_type).to eq("application/json; charset=utf-8")
    end
  end

  describe "POST #create" do
    context "with valid attributes" do
      it "creates a new vehicle" do
        expect {
          post :create, params: { vehicle: FactoryBot.attributes_for(:vehicle), format: :json }
        }.to change(Vehicle, :count).by(1)
        expect(response).to have_http_status(:created)
      end
    end

    context "with invalid attributes" do
      it "returns a bad request response" do
        post :create, params: { vehicle: { number: nil }, format: :json }
        expect(response).to have_http_status(:bad_request)
      end
    end
  end

  describe "DELETE #destroy" do
    it "destroys the requested vehicle" do
      vehicle = FactoryBot.create(:vehicle)
      expect {
        delete :destroy, params: { id: vehicle.id, format: :json }
      }.to change(Vehicle, :count).by(-1)
      expect(response).to have_http_status(:success)
      expect(response.body).to eq("Deleted!")
    end
  end

  describe "PUT #update" do
    context "with valid attributes" do
      it "updates the requested vehicle" do
        vehicle = FactoryBot.create(:vehicle)
        put :update, params: { id: vehicle.id, vehicle: { description: "Updated description" }, format: :json }
        vehicle.reload
        expect(vehicle.description).to eq("Updated description")
        expect(response).to have_http_status(:success)
      end
    end

    context "with invalid attributes" do
      it "returns an unprocessable entity response" do
        vehicle = FactoryBot.create(:vehicle)
        put :update, params: { id: vehicle.id, vehicle: { number: nil }, format: :json }
        expect(response).to have_http_status(:unprocessable_entity)
      end
    end
  end
end
