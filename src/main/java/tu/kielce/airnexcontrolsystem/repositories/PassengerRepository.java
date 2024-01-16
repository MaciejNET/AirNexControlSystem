package tu.kielce.airnexcontrolsystem.repositories;

import org.springframework.data.repository.CrudRepository;
import tu.kielce.airnexcontrolsystem.entities.Passenger;
import tu.kielce.airnexcontrolsystem.value_objects.Email;
import java.util.Optional;

public interface PassengerRepository extends CrudRepository<Passenger, Long> {
    Optional<Passenger> findByEmail (Email email);
}
