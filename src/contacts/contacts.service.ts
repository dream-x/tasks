import { Injectable, BadRequestException } from '@nestjs/common';
import { Model } from 'mongoose';
import { InjectModel } from '@nestjs/mongoose';
import { Parser } from 'json2csv';
import { Contact, ContactDocument } from './contact.schema';
import { CreateContactDto } from './dto/create-contact.dto';
import { UpdateContactDto } from './dto/update-contact.dto';

@Injectable()
export class ContactsService {
  constructor(
    @InjectModel(Contact.name)
    private readonly contactModel: Model<ContactDocument>,
  ) {}

  async create(createContactDto: CreateContactDto) {
    const contact = await this.contactModel
      .findOne({ email: createContactDto.email })
      .exec();

    if (contact) {
      throw new BadRequestException(
        `Contact already exists for ${createContactDto.email}`,
      );
    }

    return new this.contactModel(createContactDto).save();
  }

  async findAll() {
    const contacts = await this.contactModel.find().exec();

    try {
      const parser = new Parser({
        fields: ['_id', 'first_name', 'last_name', 'email', 'note'],
      });
      const csv = parser.parse(contacts);

      return csv;
    } catch (err) {
      throw err;
    }
  }

  async findOne(id: string) {
    const contact = await this.contactModel.findById(id).exec();

    if (!contact) {
      throw new BadRequestException(`Contact not found for ${id}`);
    }

    return contact;
  }

  async update(id: string, updateContactDto: UpdateContactDto) {
    const contact = await this.contactModel.findById(id).exec();

    if (!contact) {
      throw new BadRequestException(`Contact not found for ${id}`);
    }

    if (updateContactDto.email) {
      const otherContact = await this.contactModel.findOne({
        email: updateContactDto.email,
      });

      if (contact.id === otherContact.id) {
        throw new BadRequestException(
          `Contact already exists for ${updateContactDto.email}`,
        );
      }
    }

    return this.contactModel
      .findByIdAndUpdate(id, updateContactDto, { new: true })
      .exec();
  }

  async remove(id: string) {
    const contact = await this.contactModel.findById(id).exec();

    if (!contact) {
      throw new BadRequestException(`Contact not found for ${id}`);
    }

    return this.contactModel.findByIdAndDelete(id).exec();
  }
}
