Rails.application.routes.draw do
  # Define your application routes per the DSL in https://guides.rubyonrails.org/routing.html

  namespace :api do
    namespace :v1 do
      get '/contacts', to: 'contacts#index'
      post '/create/contact', to: 'contacts#create'
      get '/contacts/:id', to: 'contacts#show'
      put '/contacts/:id', to: 'contacts#update'
      delete '/contacts/:id', to: 'contacts#destroy'
    end
  end

  get '*page', to: 'home#index'
  root 'home#index'
end
