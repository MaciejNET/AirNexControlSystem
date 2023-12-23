package tu.kielce.airnexcontrolsystem.value_objects;

import tu.kielce.airnexcontrolsystem.exceptions.EmptyModelException;

/**
 * @author Maciej Deroń
 */
public record Model(String value) {
    public Model {
        if (value == null || value.isBlank()) {
            throw new EmptyModelException();
        }
    }
}
