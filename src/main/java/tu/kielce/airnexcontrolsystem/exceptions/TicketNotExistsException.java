package tu.kielce.airnexcontrolsystem.exceptions;

public class TicketNotExistsException extends AirNexControlSystemException {
    public TicketNotExistsException(Long id) {
        super("Ticket with id: " + id + " does not exists.");
    }
}
