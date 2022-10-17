class Idempotence < ApplicationRecord
  validates :key, presence: true
  validates :key, uniqueness: true
end
