package tu.kielce.airnexcontrolsystem.entities;

import jakarta.persistence.*;
import lombok.Getter;
import tu.kielce.airnexcontrolsystem.converters.CityConverter;
import tu.kielce.airnexcontrolsystem.converters.CountryConverter;
import tu.kielce.airnexcontrolsystem.converters.NameConverter;
import tu.kielce.airnexcontrolsystem.value_objects.City;
import tu.kielce.airnexcontrolsystem.value_objects.Country;
import tu.kielce.airnexcontrolsystem.value_objects.Name;

/**
 * @author Maciej Deroń
 */
@Entity
@Table(name = "airports")
@Getter
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    @Convert(converter = NameConverter.class)
    private Name name;

    @Column(name = "city")
    @Convert(converter = CityConverter.class)
    private City city;

    @Column(name = "country")
    @Convert(converter = CountryConverter.class)
    private Country country;

    protected Airport() {
    }

    public Airport(final Name name, final City city, final Country country) {
        this.name = name;
        this.city = city;
        this.country = country;
    }

    public void changeName(final Name name) {
        this.name = name;
    }
}
