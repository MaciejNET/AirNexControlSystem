package tu.kielce.airnexcontrolsystem.exceptions;

public class TicketLastMinuteCancellationException extends AirNexControlSystemException {
    public TicketLastMinuteCancellationException() {
        super("Cannot cancel the ticket less than a hour before departure.");
    }
}
