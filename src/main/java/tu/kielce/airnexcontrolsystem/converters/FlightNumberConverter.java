package tu.kielce.airnexcontrolsystem.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import tu.kielce.airnexcontrolsystem.value_objects.City;
import tu.kielce.airnexcontrolsystem.value_objects.FlightNumber;

/**
 * @author Maciej Deroń
 */
@Converter
public class FlightNumberConverter implements AttributeConverter<FlightNumber, String> {
    @Override
    public String convertToDatabaseColumn(final FlightNumber flightNumber) {
        return flightNumber.value();
    }

    @Override
    public FlightNumber convertToEntityAttribute(final String s) {
        return new FlightNumber(s);
    }
}
