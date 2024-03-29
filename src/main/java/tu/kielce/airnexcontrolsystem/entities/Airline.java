package tu.kielce.airnexcontrolsystem.entities;

import jakarta.persistence.*;
import lombok.Getter;
import tu.kielce.airnexcontrolsystem.converters.NameConverter;
import tu.kielce.airnexcontrolsystem.value_objects.Name;

import java.util.List;

/**
 * @author Maciej Deroń
 */
@Entity
@Table(name = "airlines")
@Getter
public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    @Convert(converter = NameConverter.class)
    private Name name;

    @OneToMany(mappedBy = "airline", cascade = CascadeType.ALL)
    private List<Plane> planes;

    @OneToMany(mappedBy = "airline", cascade = CascadeType.ALL)
    private List<Flight> flights;

    protected Airline() {
    }

    public Airline(final Name name) {
        this.name = name;
    }

    public void changeName(final Name name) {
        this.name = name;
    }
}
