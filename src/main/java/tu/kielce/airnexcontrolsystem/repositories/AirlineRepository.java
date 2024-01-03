package tu.kielce.airnexcontrolsystem.repositories;

import org.springframework.data.repository.CrudRepository;
import tu.kielce.airnexcontrolsystem.entities.Airline;

import java.util.Optional;

public interface AirlineRepository extends CrudRepository<Airline, Long> {
    Optional<Airline> findByName_Value(String name);
}
