package tu.kielce.airnexcontrolsystem.exceptions;

/**
 * @author Maciej Deroń
 */
public class PlainNotExistsException extends AirNexControlSystemException {
    public PlainNotExistsException(final Long plainId) {
        super("Plain with id " + plainId + " does not exists");
    }
}
