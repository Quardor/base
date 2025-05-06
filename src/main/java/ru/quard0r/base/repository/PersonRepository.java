package ru.quard0r.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.quard0r.base.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

}
