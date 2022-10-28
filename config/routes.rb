Rails.application.routes.draw do
  # using API::V1 for our api is very helpful as versioning our api makes it more flexible in case..
  # we wanted to update without damaging the current data or have to stop the server for a while!
  namespace :api do
    namespace :v1 do
      get '/contacts', to: 'contacts#index'
      post '/create/contact', to: 'contacts#create'
      get '/contacts/:id', to: 'contacts#show'
      put '/contacts/:id', to: 'contacts#update'
      delete '/contacts/:id', to: 'contacts#destroy'
    end
  end

  get '*page', to: 'home#index' # It sets up the Routes generated in the frontend by React to respond to
  # the home#index page where the Parent Component <App /> is rendered there!
  root 'home#index'
end
