package in.himtech.boot.learn.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.himtech.boot.learn.model.Person;

public interface PersonRepo extends JpaRepository<Person, Integer> {

}
