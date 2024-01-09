package tu.kielce.airnexcontrolsystem.repositories;

import org.springframework.data.repository.CrudRepository;
import tu.kielce.airnexcontrolsystem.entities.Airport;
import tu.kielce.airnexcontrolsystem.value_objects.Name;

import java.util.Optional;


/**
 * @author Paweł Dostal
 */
public interface AirportRepository extends CrudRepository <Airport, Long> {
    Optional<Airport> findByName(final Name name);

}
