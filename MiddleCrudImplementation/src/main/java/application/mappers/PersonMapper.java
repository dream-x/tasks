package application.mappers;

import application.dto.in.PersonInDto;
import application.dto.in.UpdatePersonDto;
import application.dto.out.PersonOutDto;
import application.entities.Person;
import org.mapstruct.Mapper;

@Mapper
public interface PersonMapper {
    PersonOutDto map(Person person);
    Person map(PersonInDto dto);
    Person map(UpdatePersonDto data);
}
