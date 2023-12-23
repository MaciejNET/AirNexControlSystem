package tu.kielce.airnexcontrolsystem.value_objects;

import tu.kielce.airnexcontrolsystem.exceptions.EmptyNameException;

/**
 * @author Maciej Deroń
 */
public record Name(String value) {
    public Name {
        if (value == null || value.isBlank()) {
            throw new EmptyNameException();
        }
    }
}
