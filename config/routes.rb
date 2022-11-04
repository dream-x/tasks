Rails.application.routes.draw do
  # Define your application routes per the DSL in https://guides.rubyonrails.org/routing.html

  # Defines the root path route ("/")
  root "teams#index"
  namespace :api do
    namespace :v1 do
      resources :teams, only: [:index, :show, :create, :update, :destroy]
    end
  end
end
