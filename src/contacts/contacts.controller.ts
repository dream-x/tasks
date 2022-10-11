import {
  Controller,
  Get,
  Post,
  Body,
  Patch,
  Param,
  Delete,
  Header,
  Headers,
  Res,
  BadRequestException,
} from '@nestjs/common';
import type { Response } from 'express';
import { ContactsService } from './contacts.service';
import { CreateContactDto } from './dto/create-contact.dto';
import { UpdateContactDto } from './dto/update-contact.dto';

@Controller('contacts')
export class ContactsController {
  dummyCache = {};

  constructor(private readonly contactsService: ContactsService) {}

  @Post()
  async create(
    @Headers('x-idempotence-key') idemKey: string,
    @Body() createContactDto: CreateContactDto,
    @Res() res: Response,
  ) {
    if (!idemKey) {
      throw new BadRequestException(`Idempotence key required`);
    }

    if (this.dummyCache[idemKey]) {
      return res.status(304).send('Not Modified');
    }

    const newContact = await this.contactsService.create(createContactDto);
    this.dummyCache[idemKey] = newContact;

    return res.status(201).json(newContact);
  }

  @Header('Content-Type', 'text/csv')
  @Get()
  findAll() {
    return this.contactsService.findAll();
  }

  @Get(':id')
  findOne(@Param('id') id: string) {
    return this.contactsService.findOne(id);
  }

  @Patch(':id')
  update(@Param('id') id: string, @Body() updateContactDto: UpdateContactDto) {
    return this.contactsService.update(id, updateContactDto);
  }

  @Delete(':id')
  remove(@Param('id') id: string) {
    return this.contactsService.remove(id);
  }
}
