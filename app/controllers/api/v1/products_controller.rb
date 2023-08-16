class Api::V1::ProductsController < ApplicationController
  include ActionController::MimeResponds
  before_action :set_product, only: [:show, :update, :destroy]
  require 'csv'
  def index
    respond_to do |format|
      format.json do
        #Todo pagination needs to implemented soon
        render json: {
          data: Product.all.order(updated_at: :desc).map{
            |product| ProductPresenter.new(product).show
          },
          message: 'products retreived successfully'
        }, status: :ok
      end
      format.csv do
        send_data Product.as_csv, filename: 'products.csv', type: 'text/csv'
      end
    end
  end

  def create
    product = Product.create(product_params)
    render_response(product)
  end

  def show
    render json: {
      message: 'product found',
      data: ProductPresenter.new(@product).show
    }, status: :ok
  end

  def update
    if @product.update(product_params)
      render json: {
        data: ProductPresenter.new(@product).show,
        message: 'product updated successfully'
      }, status: :ok
    else
      render json: {
        error: @product.errors.full_messages.to_sentence,
        message: 'action cannot be performed due to validation errors'
      }, status: :unprocessable_entity
    end
  end

  def destroy
    if @product.destroy
      render json: {
        message: 'product deleted successfully'
      }, status: :ok
    else
      render json: {
        error: product.errors.full_messages.to_sentence,
        message: 'product cannot be deleted'
      }, status: :unprocessable_entity
    end
  end

  private

  def product_params
    params.permit(:name, :description)
  end

  def set_product
    @product = Product.find_by(id: params[:id])

    render json: {
      error: 'product not found'
    }, status: :not_found unless @product.present?
  end

  def render_response(product)
    if product.errors.any?
      render json: {
        error: product.errors.full_messages.to_sentence,
        message: 'action cannot be performed due to validation errors'
      }, status: :unprocessable_entity
    else
      render json: {
        data: ProductPresenter.new(product).show,
        message: "product created successfully"
      }, status: :ok
    end
  end
end
