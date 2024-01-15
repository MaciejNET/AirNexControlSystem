package tu.kielce.airnexcontrolsystem.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import tu.kielce.airnexcontrolsystem.entities.Flight;
import tu.kielce.airnexcontrolsystem.specifications.FlightSpecification;

import java.util.List;

/**
 * @author Maciej Deroń
 */
public interface FlightRepository extends CrudRepository<Flight, Long>, JpaSpecificationExecutor<Flight> {
}
