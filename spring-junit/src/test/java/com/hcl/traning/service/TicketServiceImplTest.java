package com.hcl.traning.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hcl.traning.dao.TicketDAO;
import com.hcl.traning.dto.Ticket;

/**
 * @author Manjeet Kumar
 *
 * Jul 8, 2020
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:application-context.xml")
public class TicketServiceImplTest {

	
	
	private static final int RESULT = 1;
	private static final String PHONE = "9584115079";
	private static final String PASSANGER_NAME = "manjeet";

	@Mock
	TicketDAO dao;
	
	@Autowired
	@InjectMocks
	TicketServiceImpl service;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void butTicket_should_return_validValue() {
		when(dao.createTicket(any(Ticket.class))).thenReturn(1);
		int result = service.buyTicket(PASSANGER_NAME, PHONE);
		assertEquals(result,RESULT );
	}

}
