package tu.kielce.airnexcontrolsystem.exceptions;

public class NoSeatsFlightException extends AirNexControlSystemException {
    public NoSeatsFlightException() {
        super("There is no seats for this flight");
    }
}
