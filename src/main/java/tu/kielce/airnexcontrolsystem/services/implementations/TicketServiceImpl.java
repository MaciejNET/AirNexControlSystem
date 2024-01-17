package tu.kielce.airnexcontrolsystem.services.implementations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import tu.kielce.airnexcontrolsystem.commends.BuyTicketCommand;
import tu.kielce.airnexcontrolsystem.dto.TicketDto;
import tu.kielce.airnexcontrolsystem.entities.Flight;
import tu.kielce.airnexcontrolsystem.entities.Passenger;
import tu.kielce.airnexcontrolsystem.entities.Seat;
import tu.kielce.airnexcontrolsystem.entities.Ticket;
import tu.kielce.airnexcontrolsystem.exceptions.*;
import tu.kielce.airnexcontrolsystem.repositories.FlightRepository;
import tu.kielce.airnexcontrolsystem.repositories.PassengerRepository;
import tu.kielce.airnexcontrolsystem.repositories.TicketRepository;
import tu.kielce.airnexcontrolsystem.services.TicketService;
import tu.kielce.airnexcontrolsystem.value_objects.Email;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mariusz Ignaciuk, Paweł Dostal, Julia Dziekańska
 */
@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final FlightRepository flightRepository;
    private final PassengerRepository passengerRepository;
    private static final Logger logger = LogManager.getLogger(TicketServiceImpl.class);

    public TicketServiceImpl(TicketRepository ticketRepository, FlightRepository flightRepository, PassengerRepository passengerRepository) {
        this.ticketRepository = ticketRepository;
        this.flightRepository = flightRepository;
        this.passengerRepository = passengerRepository;
    }
    @Override
    public void refundTicket(Long id) {
        logger.info("Refunding ticket");
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new TicketNotExistsException(id));

        if (ticket.getFlight().getDepartureTime().isBefore(LocalDateTime.now())){
            throw new TicketsFlightAlreadyDepartedException(ticket.getFlight().getFlightNumber().value());
        }
        if (LocalDateTime.now().isAfter(ticket.getFlight().getDepartureTime().minusHours(1))) {
            throw new TicketLastMinuteCancellationException();
        }
        ticketRepository.delete(ticket);
        logger.info("Ticket refunded");
    }
    @Override
    public void buyTicket(BuyTicketCommand command) {
        logger.info("Buying ticket");

        Passenger passenger = passengerRepository.findByEmail(new Email(command.email())).orElseThrow(() -> new PassengerNotExistsException(command.email()));
        Flight flight = flightRepository.findById(command.flightId()).orElseThrow(() -> new FlightNotExistsException(command.flightId()));

        if (flight.getDepartureTime().isBefore(LocalDateTime.now())){
            throw new TicketsFlightAlreadyDepartedException(flight.getFlightNumber().value());
        }
        List<Seat> seats = flight.getAvailableSeats();
        if (seats.isEmpty()){
            throw new NoSeatsFlightException();
        }
        Seat seat = seats.get(0);
        Ticket ticket = new Ticket(flight, seat, passenger);
        ticketRepository.save(ticket);
        logger.info("Ticket bought");
    }

    @Override
    public TicketDto getById(Long id){
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new TicketNotExistsException(id));
    }

    @Override
    public List<TicketDto> getUsersTickets(Long userId, boolean isActive){
        List<Ticket> userTickets = ticketRepository.findByUserIdAndActive(userId, isActive);
        return userTickets.stream()
                .map(this::mapTicketToDto)
                .collect(Collectors.toList());
    }
    private TicketDto mapTicketToDto(Ticket ticket) {
        return new TicketDto(ticket.getId(), ticket.getFlight().getId(), ticket.getPassenger().getId(), ticket.isActive());
    }
}
