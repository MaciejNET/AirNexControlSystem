package tu.kielce.airnexcontrolsystem.repositories;

import org.springframework.data.repository.CrudRepository;
import tu.kielce.airnexcontrolsystem.entities.Plane;

/**
 * @author Maciej Deroń
 */
public interface PlaneRepository extends CrudRepository<Plane, Long> {
}
