package tu.kielce.airnexcontrolsystem.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tu.kielce.airnexcontrolsystem.value_objects.PhoneNumber;

/**
 * @author Maciej Deroń
 */
@Converter
public class PhoneNumberConverter implements AttributeConverter<PhoneNumber, String> {
    @Override
    public String convertToDatabaseColumn(final PhoneNumber phoneNumber) {
        return phoneNumber.value();
    }

    @Override
    public PhoneNumber convertToEntityAttribute(final String s) {
        return new PhoneNumber(s);
    }
}
