package manager;

import model.Slot;
import model.Ticket;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TicketManager {
    private Map<String, Ticket> tickets;

    public TicketManager() {
        tickets = new HashMap<>();
    }

    public Ticket generateTicket(String vehicleId, Slot slot) {
        Ticket ticket = new Ticket(UUID.randomUUID().toString(), LocalDateTime.now(), slot);
        tickets.put(vehicleId, ticket);
        return ticket;
    }

    public Ticket getTicket(String vehicleId) {
        return tickets.get(vehicleId);
    }
}
