package tu.kielce.airnexcontrolsystem.exceptions;

/**
 * @author Paweł Dostal
 */
public class AirportNotExistsException extends AirNexControlSystemException {
    public AirportNotExistsException(final String airportName) {
        super("Airport with name " + airportName + " does not exists");
    }
    public AirportNotExistsException(final Long id) {
        super("Airport with id " + id + " does not exists");
    }

}