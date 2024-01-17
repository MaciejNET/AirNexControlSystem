package tu.kielce.airnexcontrolsystem.services;

import tu.kielce.airnexcontrolsystem.commends.BuyTicketCommand;
import tu.kielce.airnexcontrolsystem.dto.TicketDto;

import java.util.List;

/**
 * @author Mariusz Ignaciuk,
 */
public interface TicketService {
    void refundTicket(Long id);

    void buyTicket(BuyTicketCommand command);

    /**
     * @author Julia Dziekańska
     */
    TicketDto getById(Long id);
    List<TicketDto>getAll();
    List<TicketDto>getUsersTickets(Long userId, boolean isActive);
}