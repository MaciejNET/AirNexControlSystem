package tu.kielce.airnexcontrolsystem.mappers;

import org.springframework.stereotype.Component;
import tu.kielce.airnexcontrolsystem.dto.AirlineDto;
import tu.kielce.airnexcontrolsystem.dto.AirportDto;
import tu.kielce.airnexcontrolsystem.dto.PlainDto;
import tu.kielce.airnexcontrolsystem.entities.Airline;
import tu.kielce.airnexcontrolsystem.entities.Airport;
import tu.kielce.airnexcontrolsystem.entities.Plain;

@Component
public class EntityMapperImpl implements EntityMapper {
    /**
     * @author Maciej Deroń
     */
    @Override
    public PlainDto toDto(final Plain plain) {
        return new PlainDto(plain.getId(),
                plain.getModel().value(),
                plain.getAirline().getName().value(),
                plain.getSeats().size());
    }

        /**
         * @author Paweł Dostal
         */
        @Override
        public AirportDto toDto(final Airport airport) {
            return new AirportDto(airport.getId(),
                    airport.getName().value(),
                    airport.getCity().value(),
                    airport.getCountry().value());
        }


    /**
     * @author Mariusz Ignaciuk
     */
    @Override
    public AirlineDto toDto(final Airline airline) {
        return new AirlineDto(airline.getId(),
                airline.getName().value());
    }
}
