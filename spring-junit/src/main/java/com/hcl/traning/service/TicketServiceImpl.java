package com.hcl.traning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hcl.traning.dao.TicketDAO;
import com.hcl.traning.dto.Ticket;

/**
 * @author Manjeet Kumar
 *
 * Jul 8, 2020
 */
@Component
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketDAO dao;
	/* (non-Javadoc)
	 * @see com.hcl.traning.service.TicketService#buyTicket(java.lang.String, java.lang.String)
	 */
	@Override
	public int buyTicket(String passangerName, String phone) {
		Ticket ticket =new Ticket();
		ticket.setPassangerName(passangerName);
		ticket.setPhone(phone);
		
		return dao.createTicket(ticket);
	}
	/**
	 * @return the dao
	 */
	public TicketDAO getDao() {
		return dao;
	}
	/**
	 * @param dao the dao to set
	 */
	public void setDao(TicketDAO dao) {
		this.dao = dao;
	}

}
