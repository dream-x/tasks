require 'faker'

# Create vehicles
10.times do
  Vehicle.create!(
    number: Faker::Number.unique.number(digits: 3),
    chasis: Faker::Alphanumeric.unique.alphanumeric(number: 10).upcase,
    description: Faker::Vehicle.car_type,
    company: Faker::Vehicle.manufacture
  )
end


