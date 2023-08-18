require 'rails_helper'

RSpec.describe Vehicle, type: :model do
  subject(:vehicle) { FactoryBot.build(:vehicle) }

  it { is_expected.to validate_presence_of(:number).with_message("can't be blank") }
  it { is_expected.to validate_uniqueness_of(:number).with_message("must be unique") }
  it { is_expected.to validate_numericality_of(:number).only_integer.with_message("must be an integer") }

  it { is_expected.to validate_presence_of(:chasis).with_message("can't be blank") }
  it { is_expected.to validate_uniqueness_of(:chasis).with_message("must be unique") }
  it { is_expected.to validate_length_of(:chasis).is_at_least(10).with_message("length must be at least 10 characters") }
  it { is_expected.to allow_value("ABCD123456").for(:chasis) }
  it { is_expected.not_to allow_value("abc!@#123").for(:chasis).with_message("must contain only uppercase letters and digits") }

  it { is_expected.to validate_presence_of(:company).with_message("can't be blank") }
end
