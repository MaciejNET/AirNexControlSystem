package tu.kielce.airnexcontrolsystem.exceptions;

public class PassengerWithGivenEmailAlreadyExistsException extends AirNexControlSystemException{
    public PassengerWithGivenEmailAlreadyExistsException(final String email) {
        super("Passenger with given email already exists: " + email);
    }
}
