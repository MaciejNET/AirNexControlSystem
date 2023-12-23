package tu.kielce.airnexcontrolsystem.exceptions;

/**
 * @author Maciej Deroń
 */
public class EmptyPhoneNumberException extends AirNextControlSystemException {
    public EmptyPhoneNumberException() {
        super("Phone number cannot be null or blank");
    }
}
