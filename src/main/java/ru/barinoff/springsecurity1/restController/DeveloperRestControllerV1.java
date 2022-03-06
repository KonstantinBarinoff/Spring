package ru.barinoff.springsecurity1.restController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
