package edu.eci.arsw.segurity.model;

import java.util.ArrayList;
import java.util.List;

public class Ticket {
	private static Ticket ticket= new Ticket();
	
	private static List<String> tickets;// lista de redis 

    private int numeroTicket;

    public Ticket() {
        tickets = new ArrayList<String>();
        numeroTicket = 0;
    }

    public static Ticket getInstance() {
        return ticket;
    }

    public synchronized Integer getTicket() {
        final Integer numero = numeroTicket++;
        tickets.add(numero.toString());
        return numero;
    }

    public static boolean checkTicket(String ticket) {
		boolean valido = tickets.remove(ticket);
		return valido;
		
	}

    public List<String> getListaTickets() {
        return tickets;
    }
 
    private void eviction(){
        // delete ticket after timout or include this functionality in cheklist
    }

}