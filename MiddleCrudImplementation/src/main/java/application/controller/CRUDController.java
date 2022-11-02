package application.controller;

import application.dto.in.InDataDto;
import application.dto.out.OutDataDto;
import application.mappers.PersonMapper;
import application.repositories.Person;
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
    public ResponseEntity<OutDataDto> create(@RequestBody InDataDto dto) {
        Person person = mapper.map(dto);
        repository.save(person);
        return ResponseEntity.ok(mapper.map(person));
    }

    @GetMapping("persons/{id}")
    public ResponseEntity<OutDataDto> get(@PathVariable UUID id) {
        Optional<Person> data = repository.findById(id);
        if (data.isPresent()) {
            Person person = data.get();
            return ResponseEntity.ok(mapper.map(person));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("persons")
    public ResponseEntity<List<OutDataDto>> getAll() {
        return ResponseEntity.ok(repository.findAll().stream().map(mapper::map).collect(Collectors.toList()));
    }

    @PutMapping("persons")
    public void update(@RequestBody InDataDto dto) {
        log.info("Updated {}", dto);
    }

    @DeleteMapping("persons/{id}")
    public ResponseEntity delete(@PathVariable UUID id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
