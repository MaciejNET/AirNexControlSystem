package tu.kielce.airnexcontrolsystem.repositories;

import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.repository.CrudRepository;
import tu.kielce.airnexcontrolsystem.entities.Airline;
import tu.kielce.airnexcontrolsystem.value_objects.Name;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author Maciej Deroń
 */

public interface AirlineRepository extends CrudRepository<Airline, Long> {
    Optional<Airline> findByName(final Name name);

}
