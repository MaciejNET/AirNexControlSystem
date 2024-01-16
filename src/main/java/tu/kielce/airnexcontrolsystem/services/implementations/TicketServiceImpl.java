package tu.kielce.airnexcontrolsystem.services.implementations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import tu.kielce.airnexcontrolsystem.entities.Ticket;
import tu.kielce.airnexcontrolsystem.exceptions.TicketLastMinuteCancellationException;
import tu.kielce.airnexcontrolsystem.exceptions.TicketNotExistsException;
import tu.kielce.airnexcontrolsystem.exceptions.TicketsFlightAlreadyDepartedException;
import tu.kielce.airnexcontrolsystem.repositories.TicketRepository;
import tu.kielce.airnexcontrolsystem.services.TicketService;

import java.time.LocalDateTime;

/**
 * @author Mariusz Ignaciuk,
 */
@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private static final Logger logger = LogManager.getLogger(TicketServiceImpl.class);

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
    @Override
    public void refundTicket(Long id) {
        logger.info("Refunding ticket");
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new TicketNotExistsException(id));

        if (ticket.getFlight().getDepartureTime().isBefore(LocalDateTime.now())){
            throw new TicketsFlightAlreadyDepartedException(ticket.getFlight().getFlightNumber().value());
        }
        if (LocalDateTime.now().isAfter(ticket.getFlight().getDepartureTime().minusHours(1))) {
            throw new TicketLastMinuteCancellationException();
        }
        ticketRepository.delete(ticket);
        logger.info("Ticket refunded");
    }
}
