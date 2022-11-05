class IdempotentAction < ApplicationRecord
  validate_presence_of :idempotency_key, :body, :status, :headers
end
