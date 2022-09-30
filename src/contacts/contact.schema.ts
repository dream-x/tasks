import { Prop, Schema, SchemaFactory } from '@nestjs/mongoose';
import { Document } from 'mongoose';

export type ContactDocument = Contact & Document;

@Schema()
export class Contact extends Document {
  @Prop({ required: true })
  first_name: string;

  @Prop({ required: true })
  last_name: string;

  @Prop({ required: true })
  email: string;

  @Prop()
  notes: string;
}

export const ContactSchema = SchemaFactory.createForClass(Contact);
