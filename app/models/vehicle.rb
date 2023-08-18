class Vehicle < ApplicationRecord
  validates :number, presence: { message: "can't be blank" }, uniqueness: { message: "must be unique" }, numericality: { only_integer: true, message: "must be an integer" }
  validates :chasis, presence: { message: "can't be blank" }, uniqueness: { message: "must be unique" }, length: { minimum: 10, message: "length must be at least 10 characters" }, format: { with: /\A[A-Z0-9]+\z/, message: "must contain only uppercase letters and digits" }
  validates :company, presence: { message: "can't be blank" }
end
