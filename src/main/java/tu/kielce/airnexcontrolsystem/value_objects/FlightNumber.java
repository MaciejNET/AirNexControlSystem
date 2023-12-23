package tu.kielce.airnexcontrolsystem.value_objects;

import tu.kielce.airnexcontrolsystem.exceptions.EmptyFlightNumberException;
import tu.kielce.airnexcontrolsystem.exceptions.InvalidFlightNumberException;

/**
 * @author Maciej Deroń
 */
public record FlightNumber(String value) {
    public FlightNumber {
        if (value == null || value.isBlank()) {
            throw new EmptyFlightNumberException();
        }

        if (!value.matches("^[A-Z]{2}-\\d{4}$")) {
            throw new InvalidFlightNumberException();
        }
    }
}
