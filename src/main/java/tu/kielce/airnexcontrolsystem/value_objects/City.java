package tu.kielce.airnexcontrolsystem.value_objects;

import tu.kielce.airnexcontrolsystem.exceptions.EmptyCityException;

/**
 * @author Maciej Deroń
 */
public record City(String value) {
    public City {
        if (value == null || value.isBlank()) {
            throw new EmptyCityException();
        }
    }
}
