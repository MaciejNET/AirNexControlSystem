package tu.kielce.airnexcontrolsystem.repositories;

import org.springframework.data.repository.CrudRepository;
import tu.kielce.airnexcontrolsystem.entities.Ticket;
/**
 * @author Mariusz Ignaciuk,
 */
public interface TicketRepository extends CrudRepository<Ticket, Long> {
}
