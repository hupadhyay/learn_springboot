package in.himtech.boot.learn.filtering;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

/**
 * this class demo to create dynamic filtering. Dynamic filtering mean 
 *  --> in one request you are sending some fields but in the another request you are sending different fields.
 * @author Himanshu
 *
 */

@RestController
@RequestMapping("/v1/filtering")
public class FilterController {
	
	/**
	 * This method will send first two field of MyBean
	 */
	// hello1 and hello2
	@GetMapping(path="one")
	public MappingJacksonValue getSomeValue() {
		MyBean bean = new MyBean("hello1", "hello2", "hello3");
		
		SimpleBeanPropertyFilter sbFilter = SimpleBeanPropertyFilter.filterOutAllExcept("feild1", "feild2");
		
		FilterProvider fProvider = new SimpleFilterProvider().addFilter("myBeanFilter", sbFilter);
		
		MappingJacksonValue mjvalue = new MappingJacksonValue(bean);
		mjvalue.setFilters(fProvider);
		
		return mjvalue;
	}
	
	/**
	 * This method will send list of last two filed of MyBean
	 */
	// hello2 and hello3
	@GetMapping(path="list")
	public MappingJacksonValue getListOfValue(){
		List<MyBean> listBean = new ArrayList<>();
		listBean.add(new MyBean("hello1", "hello2", "hello3"));
		listBean.add(new MyBean("hello11", "hello22", "hello33"));
		
		SimpleBeanPropertyFilter sbFilter = SimpleBeanPropertyFilter.filterOutAllExcept("feild2", "feild3");
		
		FilterProvider fProvider = new SimpleFilterProvider().addFilter("myBeanFilter", sbFilter);
		
		MappingJacksonValue mjvalue = new MappingJacksonValue(listBean);
		mjvalue.setFilters(fProvider);
		return mjvalue;
	}

}
