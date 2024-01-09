package tu.kielce.airnexcontrolsystem.exceptions;

/**
 * @author Paweł Dostal
 */
public class AirportAlreadyExistsException extends AirNexControlSystemException {
    public AirportAlreadyExistsException(final String airportName) {
        super("Airport with name " + airportName + " already exists");
    }
}