package tu.kielce.airnexcontrolsystem.repositories;

import org.springframework.data.repository.CrudRepository;
import tu.kielce.airnexcontrolsystem.entities.Passenger;
import tu.kielce.airnexcontrolsystem.entities.Ticket;
import tu.kielce.airnexcontrolsystem.value_objects.Email;

import java.util.List;
import java.util.Optional;

/**
 * @author Mariusz Ignaciuk,
 */
public interface TicketRepository extends CrudRepository<Ticket, Long> {
    List<Ticket> findAllByPassenger(Passenger passenger);
}
