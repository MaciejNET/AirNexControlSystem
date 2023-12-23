package tu.kielce.airnexcontrolsystem.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tu.kielce.airnexcontrolsystem.value_objects.FirstName;
import tu.kielce.airnexcontrolsystem.value_objects.Name;

/**
 * @author Maciej Deroń
 */
@Converter
public class FirstNameConverter implements AttributeConverter<FirstName, String> {
    @Override
    public String convertToDatabaseColumn(FirstName firstName) {
        return firstName.value();
    }

    @Override
    public FirstName convertToEntityAttribute(String s) {
        return new FirstName(s);
    }
}
