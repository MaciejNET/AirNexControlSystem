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
    public PlaneDto toDto(final Plane plane) {
        return new PlaneDto(plane.getId(),
                plane.getModel().value(),
                plane.getAirline().getName().value(),
                plane.getSeats().size());
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
               flight.getPlane().getModel().value(),
               flight.getAirline().getName().value(),
               flight.getAvailableSeats().size(),
               flight.getFlightDuration(),
               flight.getPrice());
    }

    /**
     * @author Julia Dziekańska
     */
    @Override
    public TicketDto toDto(final Ticket ticket) {
        return new TicketDto(ticket.getId(),
                ticket.getFlight().getFlightNumber().value(),
                ticket.getPassenger().getFirstName().value(),
                ticket.getPassenger().getLastName().value(),
                ticket.getPassenger().getEmail().value(),
                ticket.getSeat().getSeatNumber(),
                ticket.getSeat().getPosition().toString());
    }

    /**
     * @author Julia Dziekańska
     */
    @Override
    public PassengerDto toDto(final Passenger passenger) {
        return new PassengerDto(passenger.getId(),
                passenger.getFirstName().value(),
                passenger.getLastName().value(),
                passenger.getEmail().value(),
                passenger.getPhoneNumber().value(),
                passenger.getDateOfBirth().toString());
    }
}
