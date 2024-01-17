package tu.kielce.airnexcontrolsystem.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tu.kielce.airnexcontrolsystem.dto.*;
import tu.kielce.airnexcontrolsystem.entities.*;

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

    /**
     * @author Julia Dziekańska
     */
    TicketDto toDto(Ticket ticket);
    PassengerDto toDto(Passenger passenger);
}
