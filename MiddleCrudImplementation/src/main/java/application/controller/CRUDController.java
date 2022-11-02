package application.controller;

import application.dto.in.InDataDto;
import application.dto.out.OutDataDto;
import application.mappers.PersonMapper;
import application.repositories.Person;
import application.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
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
    public void create(@RequestBody InDataDto dto) {
        Person person = mapper.map(dto);
        repository.save(person);
    }

    @GetMapping("persons/{id}")
    public OutDataDto get(@PathVariable UUID id) {
        Optional<Person> data = repository.findById(id);
        if (data.isPresent()) {
            Person person = data.get();
            return mapper.map(person);
        }
        return null;
    }

    @GetMapping("persons")
    public List<OutDataDto> getAll() {
        return repository.findAll().stream().map(mapper::map).collect(Collectors.toList());
    }

    @PutMapping("persons")
    public void update(@RequestBody InDataDto dto) {
        log.info("Updated {}", dto);
    }

    @DeleteMapping("persons/{id}")
    public void delete(@PathVariable UUID id) {
        repository.deleteById(id);
    }
}
