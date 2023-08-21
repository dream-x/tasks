FactoryBot.define do
  factory :vehicle do
    number { Faker::Number.unique.number(digits: 3) }
    chasis { Faker::Alphanumeric.unique.alphanumeric(number: 10).upcase }
    description { Faker::Vehicle.car_type }
    company { Faker::Vehicle.manufacture }
  end
end
