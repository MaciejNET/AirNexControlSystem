package tu.kielce.airnexcontrolsystem.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tu.kielce.airnexcontrolsystem.value_objects.City;
import tu.kielce.airnexcontrolsystem.value_objects.Country;

/**
 * @author Maciej Deroń
 */
@Converter
public class CountryConverter implements AttributeConverter<Country, String> {
    @Override
    public String convertToDatabaseColumn(final Country country) {
        return country.value();
    }

    @Override
    public Country convertToEntityAttribute(final String s) {
        return new Country(s);
    }
}
