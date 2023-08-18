require 'swagger_helper'

VehicleSchema = {
  type: :object,
  properties: {
    id: { type: :integer },
    number: { type: :integer },
    chasis: { type: :string },
    description: { type: :string },
    company: { type: :string },
    created_at: { type: :string },
    updated_at: { type: :string }
  },
  required: ['id', 'number', 'chasis', 'company']
}

RSpec.describe 'api/v1/vehicles', type: :request do

  path '/api/v1/vehicles' do

    # GET /api/v1/vehicles
    get('list vehicles') do
      tags 'Vehicles'
      produces 'application/json'

      response(200, 'successful') do
        # Include example response body in documentation
        after do |example|
          example.metadata[:response][:content] = {
            'application/json' => {
              example: JSON.parse(response.body, symbolize_names: true)
            }
          }
        end
        schema type: :array, items: VehicleSchema
        run_test!
      end
    end

    # POST /api/v1/vehicles
    post('create vehicle') do
      tags 'Vehicles'
      consumes 'application/json'
      parameter name: :vehicle, in: :body, schema: {
        type: :object,
        properties: {
          number: { type: :integer },
          chasis: { type: :string },
          description: { type: :string },
          company: { type: :string }
        },
        required: ['number', 'chasis', 'company']
      }

      response(201, 'successful') do
        let(:vehicle) { FactoryBot.attributes_for(:vehicle) }
        # Include example response body in documentation
        after do |example|
          example.metadata[:response][:content] = {
            'application/json' => {
              example: JSON.parse(response.body, symbolize_names: true)
            }
          }
        end
        run_test!
      end
    end
  end

  path '/api/v1/vehicles/{id}' do
    parameter name: 'id', in: :path, type: :string, description: 'id'

    # GET /api/v1/vehicles/{id}
    get('show vehicle') do
      tags 'Vehicles'
      produces 'application/json'

      response(200, 'successful') do
        let(:id) { FactoryBot.create(:vehicle).id }
        after do |example|
          example.metadata[:response][:content] = {
            'application/json' => {
              example: JSON.parse(response.body, symbolize_names: true)
            }
          }
        end
        schema VehicleSchema
        run_test!
      end
    end

    # PATCH /api/v1/vehicles/{id}
    patch('update vehicle') do
      tags 'Vehicles'
      consumes 'application/json'
      parameter name: :vehicle, in: :body, schema: {
        type: :object,
        properties: {
          number: { type: :integer },
          chasis: { type: :string },
          description: { type: :string },
          company: { type: :string }
        },
        required: ['number', 'chasis', 'company']
      }

      response(200, 'successful') do
        let(:id) { FactoryBot.create(:vehicle).id }
        let(:vehicle) { FactoryBot.attributes_for(:vehicle) }
        after do |example|
          example.metadata[:response][:content] = {
            'application/json' => {
              example: JSON.parse(response.body, symbolize_names: true)
            }
          }
        end
        schema VehicleSchema
        run_test!
      end
    end

    # PUT /api/v1/vehicles/{id}
    put('update vehicle') do
      tags 'Vehicles'
      consumes 'application/json'
      let(:id) { '123' }
      parameter name: :vehicle, in: :body, schema: {
        type: :object,
        properties: {
          number: { type: :integer },
          chasis: { type: :string },
          description: { type: :string },
          company: { type: :string }
        },
        required: ['number', 'chasis', 'company']
      }

      response(200, 'successful') do
        let(:id) { FactoryBot.create(:vehicle).id }
        let(:vehicle) { FactoryBot.attributes_for(:vehicle) }
        after do |example|
          example.metadata[:response][:content] = {
            'application/json' => {
              example: JSON.parse(response.body, symbolize_names: true)
            }
          }
        end
        run_test!
      end
    end

    # DELETE /api/v1/vehicles/{id}
    delete('delete vehicle') do
      tags 'Vehicles'
      produces 'application/json'
      response(200, 'successful') do
        let(:id) { FactoryBot.create(:vehicle).id }
        after do |example|
          example.metadata[:response][:content] = {
            'application/json' => {
              example: response.body
            }
          }
        end
        schema type: :string
        run_test!
      end
    end
  end
end
