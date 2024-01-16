package tu.kielce.airnexcontrolsystem.entities;

import jakarta.persistence.*;
import lombok.Getter;

/**
 * @author Maciej Deroń
 */
@Entity
@Table(name = "tickets")
@Getter
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    protected Ticket() {
    }

    public Ticket(final Flight flight, final Seat seat, final Passenger passenger) {
        this.flight = flight;
        this.seat = seat;
        this.passenger = passenger;
    }

}
