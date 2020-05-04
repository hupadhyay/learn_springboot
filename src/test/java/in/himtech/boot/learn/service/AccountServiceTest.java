package in.himtech.boot.learn.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
//import static org.assertj.core.api.Assertions.*;

import in.himtech.boot.learn.repo.AccountRepo;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {
	
	@InjectMocks
	private AccountService service;
	
	@Mock
	private AccountRepo repo;
	
	private List<String> mockList = mock(List.class);
	
	@Test
	public void testGetTotalAmount() {
		when(repo.getAccountStmt(anyString())).thenReturn(new int[]{4,5,7});
		assertThat(service.getTotalAmount(), is(16));
	}
	
	@Test
	public void testGetTotalAmountSingleElement() {
		when(repo.getAccountStmt(anyString())).thenReturn(new int[]{5});
		Assert.assertEquals(5, service.getTotalAmount());
	}
	
	@Test
	public void testGetTotalAmountEmptyArray() {
		when(repo.getAccountStmt(anyString())).thenReturn(new int[]{});
		Assert.assertEquals(0, service.getTotalAmount());
	}
	
	@Test
	public void basic_list() {
		when(mockList.size()).thenReturn(10);
		assertSame(10, mockList.size());
	}
	
	@Test
	public void basicList_diferentReturn() {
		when(mockList.size()).thenReturn(10).thenReturn(20);
		assertSame(10, mockList.size());
		assertSame(20, mockList.size());
	}
	
	@Test
	public void basicList_parameterReturn() {
		when(mockList.get(0)).thenReturn("Hello world");
		assertSame("Hello world", mockList.get(0));
		assertSame(null, mockList.get(2));
	}
	
	@Test
	public void basicList_genericParameter() {
		when(mockList.get(anyInt())).thenReturn("Hello Dude");
		assertSame("Hello Dude", mockList.get(0));
		assertSame("Hello Dude", mockList.get(2));
	}
	
	@Test
	public void verifyBasic() {
		mockList.get(0);
		mockList.get(1);
		
		verify(mockList).get(0);
		verify(mockList, times(2)).get(anyInt());
		verify(mockList, atLeast(1)).get(anyInt());
		verify(mockList, atMost(2)).get(anyInt());
		verify(mockList, never()).get(2);
	}
	
	/**
	 * Mock object could not contain the status of the object whereas the spy object could contain the status of spy object.
	 */
	@Test
	public void spyingObject() {
		List<String> spyList = spy(ArrayList.class);
		
		System.out.println(spyList.size());
		spyList.add("Hello");
		spyList.add("Namaste");
		System.out.println(spyList.size());
		System.out.println(spyList.get(1));
		System.out.println(spyList.toString());
		
	}

}
