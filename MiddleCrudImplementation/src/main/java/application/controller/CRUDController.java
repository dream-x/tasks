package application.controller;

import application.dto.in.PersonInDto;
import application.dto.in.UpdatePersonDto;
import application.dto.out.PersonOutDto;
import application.mappers.PersonMapper;
import application.entities.Person;
import application.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CRUDController {

    private final PersonRepository repository;
    private final PersonMapper mapper = Mappers.getMapper(PersonMapper.class);

    @PostMapping("persons")
    public ResponseEntity<PersonOutDto> create(@RequestBody PersonInDto dto) {
        Person person = mapper.map(dto);
        repository.save(person);
        return ResponseEntity.ok(mapper.map(person));
    }

    @GetMapping("persons/{id}")
    public ResponseEntity<PersonOutDto> get(@PathVariable UUID id) {
        Optional<Person> data = repository.findById(id);
        if (data.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Person person = data.get();
        return ResponseEntity.ok(mapper.map(person));
    }

    @GetMapping("persons")
    public ResponseEntity<List<PersonOutDto>> getAll() {
        return ResponseEntity.ok(repository.findAll().stream().map(mapper::map).collect(Collectors.toList()));
    }

    @PutMapping("persons")
    public ResponseEntity<PersonOutDto> update(@RequestBody UpdatePersonDto dto) {
        Optional<Person> person = repository.findById(dto.getId());
        if (person.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Person newValue = mapper.map(dto);
        newValue.setId(person.get().getId());
        repository.save(newValue);
        return ResponseEntity.ok(mapper.map(newValue));
    }

    @DeleteMapping("persons/{id}")
    public ResponseEntity<PersonOutDto> delete(@PathVariable UUID id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
