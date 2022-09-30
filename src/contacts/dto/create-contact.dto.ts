import {
  IsNotEmpty,
  IsAlphanumeric,
  IsEmail,
  IsOptional,
} from 'class-validator';

export class CreateContactDto {
  @IsNotEmpty()
  @IsAlphanumeric()
  first_name: string;

  @IsNotEmpty()
  @IsAlphanumeric()
  last_name: string;

  @IsNotEmpty()
  @IsEmail()
  email: string;

  @IsOptional()
  note: string;
}
