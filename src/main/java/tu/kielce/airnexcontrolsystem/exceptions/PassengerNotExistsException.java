package tu.kielce.airnexcontrolsystem.exceptions;


public class PassengerNotExistsException extends AirNexControlSystemException {
    public PassengerNotExistsException(final String email) {
        super("Passenger with email " + email + " does not exists");
    }
    public PassengerNotExistsException(final Long id) {
        super("Passenger with id " + id + " does not exists");
    }

}