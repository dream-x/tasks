import { Injectable } from '@nestjs/common';
import { Model } from 'mongoose';
import { InjectModel } from '@nestjs/mongoose';
import { Contact, ContactDocument } from './contact.schema';
import { CreateContactDto } from './dto/create-contact.dto';
import { UpdateContactDto } from './dto/update-contact.dto';

@Injectable()
export class ContactsService {
  constructor(
    @InjectModel(Contact.name)
    private readonly contactModel: Model<ContactDocument>,
  ) {}

  create(createContactDto: CreateContactDto) {
    return new this.contactModel(createContactDto).save();
  }

  findAll() {
    return this.contactModel.find().exec();
  }

  findOne(id: string) {
    return this.contactModel.findById(id).exec();
  }

  update(id: string, updateContactDto: UpdateContactDto) {
    return this.contactModel
      .findByIdAndUpdate(id, updateContactDto, { new: true })
      .exec();
  }

  remove(id: string) {
    return this.contactModel.findByIdAndDelete(id).exec();
  }
}
