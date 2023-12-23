package tu.kielce.airnexcontrolsystem.value_objects;

import tu.kielce.airnexcontrolsystem.exceptions.EmptyPhoneNumberException;
import tu.kielce.airnexcontrolsystem.exceptions.InvalidPhoneNumberException;

/**
 * @author Maciej Deroń
 */
public record PhoneNumber(String value) {
    public PhoneNumber {
        if (value == null || value.isBlank()) {
            throw new EmptyPhoneNumberException();
        }

        if (!value.matches("\\d{9}")) {
            throw new InvalidPhoneNumberException();
        }
    }
}
