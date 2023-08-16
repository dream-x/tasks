FactoryBot.define do
  factory :product do
    name             { 'test' }
    description      { 'description' }
    created_at       { Time.now }
    updated_at       { Time.now }
  end
end