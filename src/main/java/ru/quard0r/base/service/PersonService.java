package ru.quard0r.base.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.quard0r.base.entity.Person;
import ru.quard0r.base.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Optional<Person> findId(int id) {
        return personRepository.findById(id);
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public void save(Person person) {
        personRepository.save(person);
    }

    public void delete(Person person) {
        personRepository.delete(person);
    }
}
