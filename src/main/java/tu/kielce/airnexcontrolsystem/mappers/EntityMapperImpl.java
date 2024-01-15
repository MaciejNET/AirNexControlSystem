package tu.kielce.airnexcontrolsystem.mappers;

import org.springframework.stereotype.Component;
import tu.kielce.airnexcontrolsystem.dto.*;
import tu.kielce.airnexcontrolsystem.entities.*;

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

    /**
     * @Author Maciej Deroń
     */
    @Override
    public FlightDto toDto(final Flight flight) {
        return new FlightDto(flight.getId(),
                flight.getFlightNumber().value(),
                flight.getDepartureAirport().getName().value(),
                flight.getArrivalAirport().getName().value(),
                flight.getDepartureTime().toString(),
                flight.getArrivalTime().toString(),
                flight.getPlain().getModel().value(),
                flight.getAirline().getName().value(),
                flight.getAvailableSeats().size(),
                flight.getFlightDuration(),
                flight.getPrice());

    }

    /**
     * @author Julia Dziekańska
     */

    /**nie wiem czy to jest git
    @Override
        public PassengerDto toDto(final Passenger passenger){
            return new PassengerDto(passenger.getId(),
                    passenger.getFirstName().getName().value(),
                    passenger.getLastName().getLastName().value(),
                    passenger.getDateOfBirth().value(),
                    passenger.getPhoneNumber().value(),
                    passenger.getEmail().getName().value(),
                    );
    */
}
