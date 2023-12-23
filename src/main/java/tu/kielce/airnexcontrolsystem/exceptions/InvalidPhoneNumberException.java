package tu.kielce.airnexcontrolsystem.exceptions;

/**
 * @author Maciej Deroń
 */
public class InvalidPhoneNumberException extends AirNextControlSystemException {
    public InvalidPhoneNumberException() {
        super("Phone number is invalid");
    }
}
