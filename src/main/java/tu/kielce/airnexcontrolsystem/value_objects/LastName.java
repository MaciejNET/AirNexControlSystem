package tu.kielce.airnexcontrolsystem.value_objects;

import tu.kielce.airnexcontrolsystem.exceptions.EmptyLastNameException;

/**
 * @author Maciej Deroń
 */
public record LastName(String value) {
    public LastName {
        if (value == null || value.isBlank()) {
            throw new EmptyLastNameException();
        }
    }
}
