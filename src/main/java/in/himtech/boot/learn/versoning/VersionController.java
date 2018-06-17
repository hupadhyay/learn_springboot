package in.himtech.boot.learn.versoning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class give demo of using versioning.
 * --> url based versioning.
 * --> Header based versioning.
 * --> Mime type (accept, content-type) based versioning
 * --> Parameter based versioning.
 * @author Himanshu
 *
 */
@RestController
@RequestMapping(path="/versioning")
public class VersionController {
	
	/**
	 * URL Based versioning....
	 * Limitation: URI polution
	 * @return
	 */
	@GetMapping(path="/v1/person")
	public PersonV1 getPersonV1() {
		return new PersonV1("Himanshu Kumar Upadhyay");
	}
	
	@GetMapping(path="/v2/person")
	public PersonV2 getPersonV2() {
		Name name = new Name("Himanshu", "Upadhyay");
		return new PersonV2(name);
	}
	
	/**
	 * Query Parameter based versioning
	 * Can not cached
	 */
	@GetMapping(path="person/param", params="version=1")
	public PersonV1 getParamV1() {
		return new PersonV1("Himanshu Kumar Upadhyay");
	}
	
	@GetMapping(path="person/param", params="version=2")
	public PersonV2 getParamV2() {
		Name name = new Name("Himanshu", "Upadhyay");
		return new PersonV2(name);
	}
	
	
	/**
	 * Header based versioning
	 * Can not cached.
	 * Misuse of Header
	 */
	@GetMapping(path="person/header", headers="X-API-Version=1")
	public PersonV1 getHeaderV1() {
		return new PersonV1("Himanshu Kumar Upadhyay");
	}
	
	@GetMapping(path="person/header", headers="X-API-Version=2")
	public PersonV2 getHeaderV2() {
		Name name = new Name("Himanshu", "Upadhyay");
		return new PersonV2(name);
	}
	
	/**
	 * Mime Type based versioning
	 * Limitation: we can not execute request on browser
	 */
	@GetMapping(path="person/mime", produces="application/himtech.in-v1+json")
	public PersonV1 getMimeV1() {
		return new PersonV1("Himanshu Kumar Upadhyay");
	}
	
	@GetMapping(path="person/mime", produces="application/himtech.in-v2+json")
	public PersonV2 getMimeV2() {
		Name name = new Name("Himanshu", "Upadhyay");
		return new PersonV2(name);
	}
}
