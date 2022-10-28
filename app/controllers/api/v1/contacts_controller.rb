class Api::V1::ContactsController < ApplicationController
    skip_before_action :verify_authenticity_token, raise: false

    def index
        @contacts = Contact.all.order(created_at: :desc)
        respond_to do |format|
            format.json { render json: @contacts }
            format.csv { send_data @contacts.to_csv, filename: "contacts_data.csv" }
        end
    end

    def show
        @contact = Contact.find(params[:id])
        respond_to do |format|
            format.json { render json: @contact }
        end
    end

    def create
        @contact = Contact.new(contact_params)
        @contact.save
    end

    def update
        @contact = Contact.find(params[:id])
        @contact.update(contact_params)
    end

    def destroy
        @contact = Contact.find(params[:id])
        @contact.destroy
    end

    private

    def contact_params
        params.require(:contact).permit(:name, :job, :age)
    end
end
