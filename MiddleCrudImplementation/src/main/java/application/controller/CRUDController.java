package application.controller;

import application.dto.in.InDataDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Slf4j
public class CRUDController {
    @PostMapping("persons")
    public void create(@RequestBody InDataDto dto) {
        log.info("Created {}", dto);
    }

    @GetMapping("persons/{id}")
    public void get(@PathVariable UUID id) {
        log.info("Get {}", id);
    }

    @GetMapping("persons")
    public void getAll() {
        log.info("Get all");
    }

    @PutMapping("persons")
    public void update(@RequestBody InDataDto dto) {
        log.info("Updated {}", dto);
    }

    @DeleteMapping("persons/{id}")
    public void delete(@PathVariable UUID id) {
        log.info("Deleted {}", id);
    }
}
