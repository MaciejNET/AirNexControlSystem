package tu.kielce.airnexcontrolsystem.value_objects;

import tu.kielce.airnexcontrolsystem.exceptions.EmptyCountryException;

/**
 * @author Maciej Deroń
 */
public record Country(String value) {
    public Country {
        if (value == null || value.isBlank()) {
            throw new EmptyCountryException();
        }
    }
}
