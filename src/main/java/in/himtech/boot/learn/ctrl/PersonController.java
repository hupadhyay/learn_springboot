package in.himtech.boot.learn.ctrl;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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
	
	@Autowired
	private MessageSource messageSource;
	
	/**
	 * Demo of how to use the location attribute of header to point the newly created object.
	 * @param person
	 * @return
	 */
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
	
	/**
	 * Demo of how to use Hateos link in the output.
	 * @param id
	 * @return
	 */
	@GetMapping(path="{personId}/heatos")
	public Resource<Person> getPersonByIdHateos(@PathVariable("personId") Integer id){
		Person person = personService.getPerson(id);
		
		Resource<Person> resource = new Resource(person);
		
		// create a link to get all person.
		ControllerLinkBuilder ctrlLinkBuilder = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).listPerson());
		
		// add this link to response with key name "all-persons" and return the resouce.
		resource.add(ctrlLinkBuilder.withRel("all-persons"));
		return resource;
		
	}
	
	/**
	 * This method will give demo to use the internationalization in spring boot application
	 * LocalContextHolder will have information of local which is set to "Accept_language" header properties
	 * @return
	 */
	@GetMapping(path="greet")
	public String greetHello() {
		return messageSource.getMessage("greet.good.morning",null, LocaleContextHolder.getLocale());
	}

}
