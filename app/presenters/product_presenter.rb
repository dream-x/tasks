class ProductPresenter
  attr_reader :product

  def initialize(product)
    @product = product
  end

  def show
    {
      id: product.id,
      name: product.name,
      description: product.description
    }
  end
end
