package tu.kielce.airnexcontrolsystem.exceptions;

/**
 * @author Maciej Deroń
 */
public class EmptyNameException extends AirNextControlSystemException{
    public EmptyNameException() {
        super("Name cannot be null or blank");
    }
}
