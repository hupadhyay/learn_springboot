package in.himtech.boot.learn.ctrl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import in.himtech.boot.learn.model.Student;
import in.himtech.boot.learn.service.StudentService;

/**
 * Integration test...
 * @author himanshu
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StudentControllerITTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@MockBean
	private StudentService service;
	
	@Test
	public void testGreetStudent() {
		String strGreet = restTemplate.getForObject("/students/hello-student", String.class);
		assertEquals("Hello Student", strGreet);
	}
	
	@Test
	public void testGetStudent() throws JSONException {
		Student student = restTemplate.getForObject("/students/45", Student.class);
		assertEquals("Himanshu", student.getName());
		
		String stuString = restTemplate.getForObject("/students/45", String.class);
		String expectedResult = "{\"location\":\"lucknow\",\"name\":\"Himanshu\",\"id\":101}";
		JSONAssert.assertEquals(expectedResult, stuString, true);
	}
//	
//	@Test
//	public void testGetGetStudentService() throws JSONException {
//		Student student = restTemplate.getForObject("/students/service", Student.class);
//		assertEquals("Himanshu", student.getName());
//		
//		String stuString = restTemplate.getForObject("/students/service", String.class);
//		String expectedResult = "{\"location\":\"lucknow\",\"name\":\"Himanshu\",\"id\":101}";
//		JSONAssert.assertEquals(expectedResult, stuString, true);
//	}
	
	@Test
	public void testGetGetStudentMockBeanService() throws JSONException {
		when(service.getStudent()).thenReturn(new Student(201, "Hrishik", "Chicago"));
		Student student = restTemplate.getForObject("/students/service", Student.class);
		assertEquals("Hrishik", student.getName());
		
		String stuString = restTemplate.getForObject("/students/service", String.class);
		String expectedResult = "{location:Chicago, name: Hrishik, id: 201}";
		JSONAssert.assertEquals(expectedResult, stuString, true);
	}

}
