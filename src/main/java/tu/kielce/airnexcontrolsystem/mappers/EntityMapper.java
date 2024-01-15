package tu.kielce.airnexcontrolsystem.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tu.kielce.airnexcontrolsystem.dto.AirlineDto;
import tu.kielce.airnexcontrolsystem.dto.FlightDto;
import tu.kielce.airnexcontrolsystem.dto.PlainDto;
import tu.kielce.airnexcontrolsystem.dto.AirportDto;
import tu.kielce.airnexcontrolsystem.entities.Airline;
import tu.kielce.airnexcontrolsystem.entities.Airport;
import tu.kielce.airnexcontrolsystem.entities.Flight;
import tu.kielce.airnexcontrolsystem.entities.Plain;

@Mapper(componentModel = "spring")
public interface EntityMapper {
    EntityMapper INSTANCE = Mappers.getMapper(EntityMapper.class);

    /**
     * @author Maciej Deroń
     */
    PlainDto toDto(Plain plain);

    /**
     * @author Paweł Dostal
     */
    AirportDto toDto(Airport airport);

    /**
     * @author Mariusz Ignaciuk
     */
    AirlineDto toDto(Airline airline);

    /**
     * @Author Maciej Deroń
     */
    FlightDto toDto(Flight flight);
}
