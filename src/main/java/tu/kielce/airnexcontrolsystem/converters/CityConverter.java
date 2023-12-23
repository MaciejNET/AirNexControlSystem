package tu.kielce.airnexcontrolsystem.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tu.kielce.airnexcontrolsystem.value_objects.City;
import tu.kielce.airnexcontrolsystem.value_objects.Email;

/**
 * @author Maciej Deroń
 */
@Converter
public class CityConverter implements AttributeConverter<City, String> {
    @Override
    public String convertToDatabaseColumn(final City city) {
        return city.value();
    }

    @Override
    public City convertToEntityAttribute(final String s) {
        return new City(s);
    }
}
