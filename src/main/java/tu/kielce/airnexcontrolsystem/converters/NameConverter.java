package tu.kielce.airnexcontrolsystem.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tu.kielce.airnexcontrolsystem.value_objects.Name;

/**
 * @author Maciej Deroń
 */
@Converter
public class NameConverter implements AttributeConverter<Name, String> {
    @Override
    public String convertToDatabaseColumn(Name name) {
        return name.value();
    }

    @Override
    public Name convertToEntityAttribute(String s) {
        return new Name(s);
    }
}
