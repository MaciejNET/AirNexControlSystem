package tu.kielce.airnexcontrolsystem.exceptions;

/**
 * @author Maciej Deroń
 */
public class EmptyPhoneNumberException extends AirNexControlSystemException {
    public EmptyPhoneNumberException() {
        super("Phone number cannot be null or blank");
    }
}
