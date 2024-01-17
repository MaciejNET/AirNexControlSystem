package tu.kielce.airnexcontrolsystem.exceptions;

/**
 * @author Maciej Deroń
 */
public class PlaneNotExistsException extends AirNexControlSystemException {
    public PlaneNotExistsException(final Long planeId) {
        super("Plane with id " + planeId + " does not exists");
    }
}
