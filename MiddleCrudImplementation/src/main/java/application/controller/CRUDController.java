package application.controller;

import application.dto.in.PersonInDto;
import application.dto.in.UpdatePersonDto;
import application.dto.out.PersonOutDto;
import application.mappers.PersonMapper;
import application.entities.Person;
import application.repositories.PersonRepository;
import application.utils.CsvHelper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.hibernate.id.UUIDGenerator;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
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

    @SneakyThrows
    @GetMapping(value = "persons", produces = "application/csv")
    public @ResponseBody
    void getAll(HttpServletResponse response) {
        List<Person> data = repository.findAll();
        String fileName = UUID.randomUUID() + "_data.csv";
        File csv = CsvHelper.toCsv(data, fileName);

        InputStream in = new FileInputStream(csv);
        response.setContentType("application/csv");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        response.setHeader("Content-Length", String.valueOf(csv.length()));
        FileCopyUtils.copy(in, response.getOutputStream());
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
