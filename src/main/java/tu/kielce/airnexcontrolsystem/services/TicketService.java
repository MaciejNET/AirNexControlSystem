package tu.kielce.airnexcontrolsystem.services;

import tu.kielce.airnexcontrolsystem.commends.BuyTicketCommand;

/**
 * @author Mariusz Ignaciuk,
 */
public interface TicketService {
    void refundTicket(Long id);
    void buyTicket(BuyTicketCommand command);

    /**
     * @author Julia Dziekańska
     */
    public List<TicketDto> getAllTicketsForUserById(Long userId, boolean onlyActive) {
        List<Ticket> tickets;

        if (onlyActive) {
            tickets = ticketRepository.findByUserIdAndActiveTrue(userId);
        } else {
            tickets = ticketRepository.findByUserId(userId);
        }

        return tickets.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        private TicketDto convertToDto(Ticket ticket) {
            TicketDto ticketDto = new TicketDto();

            ticketDto.setId(ticket.getId());
            ticketDto.setTitle(ticket.getTitle());

            return ticketDto;
        }
    }
}
