package tu.kielce.airnexcontrolsystem.value_objects;

import tu.kielce.airnexcontrolsystem.exceptions.EmptyFirstNameException;

/**
 * @author Maciej Deroń
 */
public record FirstName(String value) {
    public FirstName {
        if (value == null || value.isBlank()) {
            throw new EmptyFirstNameException();
        }
    }
}
