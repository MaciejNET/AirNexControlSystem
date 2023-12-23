package tu.kielce.airnexcontrolsystem.value_objects;

import tu.kielce.airnexcontrolsystem.exceptions.EmptyEmailException;
import tu.kielce.airnexcontrolsystem.exceptions.InvalidEmailException;

/**
 * @author Maciej Deroń
 */
public record Email(String value) {
    public Email {
        if (value == null || value.isBlank()) {
            throw new EmptyEmailException();
        }

        if (!value.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new InvalidEmailException();
        }
    }
}
