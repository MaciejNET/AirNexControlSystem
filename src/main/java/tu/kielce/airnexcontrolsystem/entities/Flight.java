package tu.kielce.airnexcontrolsystem.entities;

import jakarta.persistence.*;
import lombok.Getter;
import tu.kielce.airnexcontrolsystem.converters.FlightNumberConverter;
import tu.kielce.airnexcontrolsystem.exceptions.AirlineMismatchException;
import tu.kielce.airnexcontrolsystem.exceptions.InvalidArrivalException;
import tu.kielce.airnexcontrolsystem.exceptions.PastDepartureException;
import tu.kielce.airnexcontrolsystem.exceptions.SameAirportException;
import tu.kielce.airnexcontrolsystem.value_objects.FlightNumber;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Maciej Deroń
 */
@Entity
@Table(name = "flights")
@Getter
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "flight_number", unique = true)
    @Convert(converter = FlightNumberConverter.class)
    private FlightNumber flightNumber;

    @Column(name = "departure_time")
    private LocalDateTime departureTime;

    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;

    @ManyToOne
    @JoinColumn(name = "departure_airport_id")
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "arrival_airport_id")
    private Airport arrivalAirport;

    @ManyToOne
    @JoinColumn(name = "plain_id")
    private Plain plain;

    @ManyToOne
    @JoinColumn(name = "airline_id")
    private Airline airline;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "canceled")
    private boolean canceled;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
    private List<Ticket> tickets;

    protected Flight() {
    }

    public Flight(final FlightNumber flightNumber, final LocalDateTime departureTime, final LocalDateTime arrivalTime, final Airport departureAirport, final Airport arrivalAirport, final Plain plain, final Airline airline, final BigDecimal price) {
        if (departureTime.isBefore(LocalDateTime.now())) {
            throw new PastDepartureException();
        }
        if (arrivalTime.isBefore(departureTime)) {
            throw new InvalidArrivalException();
        }
        if (departureAirport.equals(arrivalAirport)) {
            throw new SameAirportException();
        }
        if (!plain.getAirline().equals(airline)) {
            throw new AirlineMismatchException();
        }
        this.flightNumber = flightNumber;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.plain = plain;
        this.airline = airline;
        this.price = price;
        this.canceled = false;
    }

    public LocalDateTime getFlightDuration() {
        return arrivalTime.minusHours(departureTime.getHour()).minusMinutes(departureTime.getMinute());
    }

    public void cancel() {
        this.canceled = true;
    }

    public void changePrice(final BigDecimal price) {
        this.price = price;
    }

    public void changeDepartureTime(final LocalDateTime departureTime) {
        if (departureTime.isBefore(LocalDateTime.now())) {
            throw new PastDepartureException();
        }

        LocalDateTime arrivalTime = this.arrivalTime
                .plusHours(departureTime.getHour())
                .plusMinutes(departureTime.getMinute())
                .minusHours(this.departureTime.getHour())
                .minusMinutes(this.departureTime.getMinute());

        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public List<Seat> getAvailableSeats() {
        return plain.getSeats().stream()
                .filter(seat -> tickets.stream()
                        .noneMatch(ticket -> ticket.getSeat().equals(seat)))
                .toList();
    }
}
