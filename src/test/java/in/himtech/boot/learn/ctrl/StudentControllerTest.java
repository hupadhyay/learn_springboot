package in.himtech.boot.learn.ctrl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import in.himtech.boot.learn.model.Student;
import in.himtech.boot.learn.service.StudentService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;



@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
public class StudentControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private StudentService serviceMock;
	
	@Test
	public void testGreetStudent() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/students/hello-student").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request).andReturn();
		String content = result.getResponse().getContentAsString();
		Assert.assertEquals("Hello Student", content);
	}
	// 
	@Test
	public void testGreetStudentMatcher() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/students/hello-student").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().string("Hello Student")).andReturn();
	}
	
	@Test
	public void testGetStudent() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/students/45").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().json("{\"location\":\"lucknow\",\"name\":\"Himanshu\",\"id\":101}")).andReturn();
	}
	
	@Test
	public void testGetStudent_jsonAssert() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/students/45").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();
		String expectedResult = "{\"location\":\"lucknow\",\"name\":\"Himanshu\",\"id\":101}";
		String expectedResult1 = "{\"name\":\"Himanshu\",\"id\":101}";
		String actualResult = "{location: lucknow,name: Himanshu,id : 101}";
		String actualResult1 = "{location:lucknow,id: 101,name: Himanshu}";
		JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), true);
		JSONAssert.assertEquals(expectedResult1, result.getResponse().getContentAsString(), false);
		JSONAssert.assertEquals(expectedResult, actualResult, true);
		JSONAssert.assertEquals(expectedResult, actualResult1, true);
	}
	
	@Test
	public void testGetStudentFromService() throws Exception {
		when(serviceMock.getStudent()).thenReturn(new Student(201, "Ashok", "Tampa"));
		RequestBuilder request = MockMvcRequestBuilders.get("/students/service").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().json("{\"location\":\"Tampa\",\"name\":\"Ashok\",\"id\":201}")).andReturn();
	}
	
	@Test
	public void testGetStudentFromService1() throws Exception {
		when(serviceMock.getStudent()).thenReturn(new Student(201, "Hrishik", "Lucknow"));
		RequestBuilder request = MockMvcRequestBuilders.get("/students/service").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();
		
		String expectResult = "{location: Lucknow, name: Hrishik, id :201}";
		
		JSONAssert.assertEquals(expectResult, result.getResponse().getContentAsString(), true);
	}
}
