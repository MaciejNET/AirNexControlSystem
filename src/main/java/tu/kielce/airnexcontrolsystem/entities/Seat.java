package tu.kielce.airnexcontrolsystem.entities;

import jakarta.persistence.*;
import lombok.Getter;
import tu.kielce.airnexcontrolsystem.enums.SeatPosition;

/**
 * @author Maciej Deroń
 */
@Entity
@Table(name = "seats")
@Getter
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seat_number")
    private int seatNumber;

    @Column(name = "position")
    @Enumerated(EnumType.STRING)
    private SeatPosition position;

    @ManyToOne
    @JoinColumn(name = "plane_id")
    private Plane plane;

    protected Seat() {
    }

    public Seat(final int seatNumber, final SeatPosition position, final Plane plane) {
        this.seatNumber = seatNumber;
        this.position = position;
        this.plane = plane;
    }
}
