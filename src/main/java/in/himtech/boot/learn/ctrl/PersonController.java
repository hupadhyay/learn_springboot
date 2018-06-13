package in.himtech.boot.learn.ctrl;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import in.himtech.boot.learn.model.Person;
import in.himtech.boot.learn.service.PersonService;

@RestController
@RequestMapping("/v1/persons")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@PostMapping
	public ResponseEntity<Object> savePerson(@Valid @RequestBody Person person) {
		Person prsn = personService.savePerson(person);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(prsn.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping
	public List<Person> listPerson(){
		return personService.getAllPerson();
		
	}
	
	@GetMapping(path="{personId}")
	public Person getPersonById(@PathVariable("personId") Integer id){
		return personService.getPerson(id);
		
	}
	
	@GetMapping(path="{personId}/heatos")
	public Resource<Person> getPersonByIdHateos(@PathVariable("personId") Integer id){
		Person person = personService.getPerson(id);
		
		Resource<Person> resource = new Resource(person);
		
		ControllerLinkBuilder ctrlLinkBuilder = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).listPerson());
		
		resource.add(ctrlLinkBuilder.withRel("all-persons"));
		return resource;
		
	}

}
