package com.hcl.traning.dao;

import org.springframework.stereotype.Component;

import com.hcl.traning.dto.Ticket;

/**
 * @author Manjeet Kumar
 *
 * Jul 8, 2020
 */
@Component
public class TicketDAOImpl implements TicketDAO {

	/* (non-Javadoc)
	 * @see com.hcl.traning.dao.TicketDAO#createTicket(com.hcl.traning.dto.Ticket)
	 */
	public int createTicket(Ticket ticket) {
		
		return 1;
	}

}
