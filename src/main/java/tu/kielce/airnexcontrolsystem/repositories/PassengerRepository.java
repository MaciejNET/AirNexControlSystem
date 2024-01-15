package tu.kielce.airnexcontrolsystem.repositories;

import org.springframework.data.repository.CrudRepository;
import tu.kielce.airnexcontrolsystem.entities.Passenger;
import tu.kielce.airnexcontrolsystem.entities.Plain;

/**
 * @author Julia Dziekańska
 */
public interface PassengerRepository extends CrudRepository <Passenger, Long>{
}
