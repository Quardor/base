package ru.quard0r.base.controller;

import org.springframework.web.bind.annotation.*;
import ru.quard0r.base.dto.PersonDTO;
import ru.quard0r.base.entity.Person;
import ru.quard0r.base.service.PersonService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/list")
    public List<PersonDTO> findAll() {
        List<PersonDTO> persons = new ArrayList<>();
        personService.findAll().forEach(person -> {
            PersonDTO personDTO = new PersonDTO();
            personDTO.setId(person.getId());
            personDTO.setName(person.getName());
            persons.add(personDTO);
        });
        return persons;
    }

    @GetMapping("/get/{id}")
    public PersonDTO findById(@PathVariable long id) {
        PersonDTO personDTO = new PersonDTO();
        personService.findById(id).ifPresent(person -> {
            personDTO.setId(person.getId());
            personDTO.setName(person.getName());
        });
        return personDTO;
    }

    @PostMapping("/save")
    public void save(@RequestBody PersonDTO personDTO) {
        Person person = new Person();
        person.setName(personDTO.getName());
        personService.save(person);
    }

    @PutMapping("/update")
    public void update(@RequestBody PersonDTO personDTO) {
        personService.findById(personDTO.getId()).ifPresent(person -> {
            person.setName(personDTO.getName());
            personService.save(person);
        });
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id) {
        personService.deleteById(id);
    }
}
