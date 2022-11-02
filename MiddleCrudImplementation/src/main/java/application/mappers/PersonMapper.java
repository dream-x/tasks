package application.mappers;

import application.dto.in.InDataDto;
import application.dto.out.OutDataDto;
import application.repositories.Person;
import org.mapstruct.Mapper;

@Mapper
public interface PersonMapper {
    OutDataDto map(Person person);
    Person map(InDataDto dto);
}
