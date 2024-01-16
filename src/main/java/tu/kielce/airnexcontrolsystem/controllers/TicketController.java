package tu.kielce.airnexcontrolsystem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tu.kielce.airnexcontrolsystem.commends.BuyTicketCommand;
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

    @PostMapping
    public ResponseEntity<?> add(@RequestBody BuyTicketCommand command){
        ticketService.buyTicket(command);
        return ResponseEntity.ok().build();
    }
}
