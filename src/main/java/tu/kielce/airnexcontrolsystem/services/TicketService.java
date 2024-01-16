package tu.kielce.airnexcontrolsystem.services;

import tu.kielce.airnexcontrolsystem.commends.BuyTicketCommand;

/**
 * @author Mariusz Ignaciuk,
 */
public interface TicketService {
    void refundTicket(Long id);
    void buyTicket(BuyTicketCommand command);
}
