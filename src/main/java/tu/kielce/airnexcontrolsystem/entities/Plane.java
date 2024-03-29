package tu.kielce.airnexcontrolsystem.entities;

import jakarta.persistence.*;
import lombok.Getter;
import tu.kielce.airnexcontrolsystem.converters.ModelConverter;
import tu.kielce.airnexcontrolsystem.enums.SeatPosition;
import tu.kielce.airnexcontrolsystem.value_objects.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Maciej Deroń
 */
@Entity
@Table(name = "planes")
@Getter
public class Plane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model")
    @Convert(converter = ModelConverter.class)
    private Model model;

    @ManyToOne
    @JoinColumn(name = "airline_id")
    private Airline airline;

    @OneToMany(mappedBy = "plane", cascade = CascadeType.ALL)
    private List<Seat> seats;

    protected Plane() {
    }

    public Plane(final Model model, final Airline airline, final int numberOfSeats) {
        this.model = model;
        this.airline = airline;
        seats = new ArrayList<>();
        for (int i = 0; i < numberOfSeats; i++) {
            Seat seat;
            if (i%3 == 0) {
                seat = new Seat(i, SeatPosition.WINDOW, this);
            } else if (i%3 == 1) {
                seat = new Seat(i, SeatPosition.MIDDLE, this);
            } else {
                seat = new Seat(i, SeatPosition.REAR, this);
            }

            seats.add(seat);
        }
    }
}
