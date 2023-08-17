# spec/factories/automobiles.rb
FactoryBot.define do
  factory :automobile do
    company { Faker::Company.name }
    model { Faker::Vehicle.model }
    year { Faker::Number.between(from: 2000, to: 2023) }
    vehicle_type { %w[sedan suv truck].sample }
    color { Faker::Vehicle.color }
    fuel_type { %w[gasoline diesel electric].sample }
    mileage { Faker::Number.between(from: 1000, to: 100000) }
    price { Faker::Number.decimal(l_digits: 5, r_digits: 2) }
    condition { %w[new used].sample }
    safety_rating { Faker::Number.between(from: 1, to: 5) }
    license_plate_number { Faker::Vehicle.license_plate }
  end
end
