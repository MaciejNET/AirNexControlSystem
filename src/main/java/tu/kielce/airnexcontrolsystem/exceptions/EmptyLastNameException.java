package tu.kielce.airnexcontrolsystem.exceptions;

/**
 * @author Maciej Deroń
 */
public class EmptyLastNameException extends AirNextControlSystemException{
    public EmptyLastNameException() {
        super("Last name cannot be null or blank");
    }
}
