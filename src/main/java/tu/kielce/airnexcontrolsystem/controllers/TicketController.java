package tu.kielce.airnexcontrolsystem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tu.kielce.airnexcontrolsystem.services.TicketService;

/**
 * @author Mariusz Ignaciuk
 */
@RestController
@RequestMapping("/ticket")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> refundTicket(@PathVariable Long id){
        ticketService.refundTicket(id);
        return ResponseEntity.ok().build();
    }
}
