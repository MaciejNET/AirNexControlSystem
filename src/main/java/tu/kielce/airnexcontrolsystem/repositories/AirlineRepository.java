package tu.kielce.airnexcontrolsystem.repositories;

import org.springframework.data.repository.CrudRepository;
import tu.kielce.airnexcontrolsystem.entities.Airline;
import tu.kielce.airnexcontrolsystem.value_objects.Name;

import java.util.Optional;

public interface AirlineRepository extends CrudRepository<Airline, Long> {
    Optional<Airline> findByName(final Name name);
}
