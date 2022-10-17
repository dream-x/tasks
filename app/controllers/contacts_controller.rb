class ContactsController < ApplicationController
  before_action :set_contact, only: %i[ show update destroy ]

  # GET /contacts
  def index
    @contacts = Contact.all
    send_data @contacts.to_csv, filename: "#{Date.today}.csv"
  end

  # GET /contacts/1
  def show
    render json: @contact
  end

  # POST /contacts
  def create
    key = Idempotence.new(key: request.headers["idempotence"])
    if key.save
      @contact = Contact.new(notes: contact_params[:notes], email: contact_params[:email])

      if @contact.save
        render json: @contact, status: :created, location: @contact
      else
        render json: @contact.errors, status: :unprocessable_entity
      end
    else
      render :json => {:response => 'idempotent_key already exists or can not be empty string' },:status => 401
    end
  end

  # PATCH/PUT /contacts/1
  def update
    if @contact.update(notes: contact_params[:notes], email: contact_params[:email])
      render json: @contact
    else
      render json: @contact.errors, status: :unprocessable_entity
    end
  end

  # DELETE /contacts/1
  def destroy
    @contact.destroy
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_contact
      @contact = Contact.find(params[:id])
    end

    # Only allow a list of trusted parameters through.
    def contact_params
      params.fetch(:contact, {})
    end
end
