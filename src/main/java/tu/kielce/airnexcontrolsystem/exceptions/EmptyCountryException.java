package tu.kielce.airnexcontrolsystem.exceptions;

/**
 * @author Maciej Deroń
 */
public class EmptyCountryException extends AirNexControlSystemException {
    public EmptyCountryException() {
        super("Country cannot be null or blank");
    }
}
