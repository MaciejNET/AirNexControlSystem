package tu.kielce.airnexcontrolsystem.exceptions;

/**
 * @author Maciej Deroń
 */
public class InvalidPhoneNumberException extends AirNexControlSystemException {
    public InvalidPhoneNumberException() {
        super("Phone number is invalid");
    }
}
