require 'rails_helper'

RSpec.describe Api::V1::ProductsController, type: :controller do
  describe '#index' do
    before do
      create_list(:product, 5)
    end

    context 'when products are 5' do
      it 'returns 5 products' do
        get :index

        expect(response.status).to eq(200)
        expect(parsed_response['data'].size).to eq(5)
      end
    end

    context 'when products are empty' do
      before do
        Product.delete_all
      end

      it 'returns empty products' do
        get :index

        expect(response.status).to eq(200)
        expect(parsed_response['data'].size).to eq(0)
      end
    end
  end

  describe '#create' do
    before do
      Product.delete_all
    end

    context 'when valid params are passed' do
      it 'creates the product successfully' do
        post :create, params: {name: 'test', description: 'test description'}

        expect(response.status).to eq(200)
        expect(parsed_response['data']['name']).to eq('test')
        expect(parsed_response['data']['description']).to eq('test description')
        expect(Product.count).to eq(1)
      end
    end

    context 'when name is not passed' do
      it 'does not create product' do
        post :create, params: {description: 'test description'}

        expect(response.status).to eq(422)
        expect(parsed_response['error']).to eq("Name can't be blank")
        expect(Product.count).to eq(0)
      end
    end
  end

  describe '#show' do
    before do
      Product.delete_all
    end

    context 'when valid id is passed' do
      let(:product) { create(:product) }

      it 'retreives the product successfully' do
        get :show, params: {id: product.id}

        expect(response.status).to eq(200)
        expect(parsed_response['data']['name']).to eq('test')
      end
    end

    context 'when invalid id is passed' do
      let(:product) { create(:product) }

      it 'does not retreive the product' do
        get :show, params: {id: 'invalid'}

        expect(response.status).to eq(404)
        expect(parsed_response['error']).to eq('product not found')
      end
    end
  end

  describe '#update' do
    before do
      Product.delete_all
    end

    context 'when valid params are passed' do
      let(:product) { create(:product) }

      it 'updates the product successfully' do
        post :update, params: {id: product.id, name: 'updated test', description: 'updated description'}

        expect(response.status).to eq(200)
        expect(parsed_response['data']['name']).to eq('updated test')
        expect(parsed_response['data']['description']).to eq('updated description')
      end
    end

    context 'when null name is passed' do
      let(:product) { create(:product) }

      it 'does not update the product' do
        post :update, params: {id: product.id, name: nil, description: 'updated description'}

        expect(response.status).to eq(422)
        expect(parsed_response['error']).to eq("Name can't be blank")
      end
    end

    context 'when invalid id is passed' do
      let(:product) { create(:product) }

      it 'does not update the product' do
        post :update, params: {id: 'invalid', name: 'updated product', description: 'updated description'}

        expect(response.status).to eq(404)
        expect(parsed_response['error']).to eq('product not found')
      end
    end
  end

  describe '#destroy' do
    before do
      Product.delete_all
    end

    context 'when valid id is passed' do
      let(:product) { create(:product) }

      it 'deletes the product successfully' do
        delete :destroy, params: {id: product.id}

        expect(response.status).to eq(200)
        expect(Product.count).to eq(0)
      end
    end

    context 'when invalid id is passed' do
      before do
        create(:product)
      end

      it 'does not delete the product' do
        delete :destroy, params: {id: 'invalid'}

        expect(response.status).to eq(404)
        expect(Product.count).to eq(1)
      end
    end
  end
end
