package tu.kielce.airnexcontrolsystem.exceptions;

public class TicketsFlightAlreadyDepartedException extends AirNexControlSystemException {
    public TicketsFlightAlreadyDepartedException(String flightNumber) {
        super("Cannot refund ticket because flight with number: " + flightNumber + " already departed.");
    }
}
