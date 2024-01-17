package tu.kielce.airnexcontrolsystem.commends;

/**
 * @author Julia Dziekańska
 */

public class GetAllTicketsCommand {

    private final TicketService ticketService;

    public GetAllTicketsCommand(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public List<TicketDto> execute() {
        List<Ticket> tickets = ticketService.getAllTickets();
        return tickets.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private TicketDto convertToDto(Ticket ticket) {
        TicketDto ticketDto = new TicketDto();
        ticketDto.setId(ticket.getId());
        ticketDto.setTitle(ticket.getTitle());
        return ticketDto;
    }
}