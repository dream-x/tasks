class Vehicle < ApplicationRecord
  validates :number, :company, :chasis, presence: true
  validates :number, :chasis, uniqueness: {message: 'must be unique'}

  validates :number, numericality: { only_integer: true, message: 'must be an integer' }
  validates :chasis, length: { minimum: 10, message: "length must be at least 10 characters" },
    format: { with: /\A[A-Z0-9]+\z/, message: "must contain only uppercase letters and digits" }
end
