package in.himtech.boot.learn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.himtech.boot.learn.exp.EntityNotFound;
import in.himtech.boot.learn.model.Person;
import in.himtech.boot.learn.repo.PersonRepo;

@Service
public class PersonService {

	@Autowired
	private PersonRepo personRepo;
	
	public Person savePerson(Person person) {
		return personRepo.save(person);
	}
	
	public Person updatePerson(Person person) {
		return null;
	}
	
	public Person getPerson(Integer personId) {
		Optional<Person> optionalPerson = personRepo.findById(personId);
		
		if(optionalPerson.isPresent()) {
			return optionalPerson.get();
		} else {
			throw new EntityNotFound("Person Id: " + personId);
		}
	}
	
	public List<Person> getAllPerson() {
		return personRepo.findAll();
	}
	
	public List<Person> getPersonByCity(String city) {
		return null;
	}
	
	public Person deletePerson(Integer personId) {
		return null;
	}
}
