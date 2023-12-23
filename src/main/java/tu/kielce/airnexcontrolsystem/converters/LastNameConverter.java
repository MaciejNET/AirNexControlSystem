package tu.kielce.airnexcontrolsystem.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tu.kielce.airnexcontrolsystem.value_objects.FirstName;
import tu.kielce.airnexcontrolsystem.value_objects.LastName;

/**
 * @author Maciej Deroń
 */
@Converter
public class LastNameConverter implements AttributeConverter<LastName, String> {
    @Override
    public String convertToDatabaseColumn(LastName lastName) {
        return lastName.value();
    }

    @Override
    public LastName convertToEntityAttribute(String s) {
        return new LastName(s);
    }
}
