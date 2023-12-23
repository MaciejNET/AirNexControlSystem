package tu.kielce.airnexcontrolsystem.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tu.kielce.airnexcontrolsystem.value_objects.Email;

/**
 * @author Maciej Deroń
 */
@Converter
public class EmailConverter implements AttributeConverter<Email, String> {
    @Override
    public String convertToDatabaseColumn(final Email email) {
        return email.value();
    }

    @Override
    public Email convertToEntityAttribute(final String s) {
        return new Email(s);
    }
}
