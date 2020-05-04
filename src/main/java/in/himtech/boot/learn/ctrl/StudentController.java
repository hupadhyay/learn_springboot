package in.himtech.boot.learn.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.himtech.boot.learn.model.Student;
import in.himtech.boot.learn.service.StudentService;

/**
 * This class is used for demostration of unit test cases using SpringRunner and MVCMock.
 * @author himanshu
 *
 */

@RestController
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private StudentService service;
	
	
	@GetMapping("/hello-student")
	public String greetStudent() {
		return "Hello Student";
	}
	
	@GetMapping(value = "/{studentId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Student getStudent() {
		return new Student(101, "Himanshu", "lucknow");
	}
	
	@GetMapping(value = "/service", produces = MediaType.APPLICATION_JSON_VALUE)
	public Student getStudentFromService() {
		return service.getStudent();
		
	}

}
