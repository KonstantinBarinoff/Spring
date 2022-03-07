package ru.barinoff.springsecurity1.restController;

import org.springframework.web.bind.annotation.*;
import ru.barinoff.springsecurity1.model.Developer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/developers")
public class DeveloperRestControllerV1 {

    List <Developer> developers = Stream.of(
             new Developer(1L, "Иван", "Иванов"),
             new Developer(2L, "Максим", "Петров"),
             new Developer(3L, "Артур", "Пирожков")
    ).collect(Collectors.toList());


    @GetMapping
    public List<Developer> getAll(){
        return developers;
    }

    @GetMapping("/{id}")
    public Developer getById(@PathVariable Long id){
        return developers.stream().filter(developer -> developer.getId().equals(id)).
                findFirst().orElse(null);
    }

    @PostMapping
    public Developer create(@RequestBody Developer developer){
        this.developers.add(developer);
        return developer;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        this.developers.removeIf(developer -> developer.getId().equals(id));

    }
}
