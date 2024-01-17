package tu.kielce.airnexcontrolsystem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tu.kielce.airnexcontrolsystem.commends.BuyTicketCommand;
import tu.kielce.airnexcontrolsystem.dto.TicketDto;
import tu.kielce.airnexcontrolsystem.services.TicketService;

import java.util.List;

/**
 * @author Mariusz Ignaciuk, Paweł Dostal, Julia Dziekańska
 */
@RestController
@RequestMapping("/ticket")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDto> getTicket(@PathVariable Long id){
        TicketDto ticket = ticketService.getById(id);
        if(ticket == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ticket);
    }

    @GetMapping
    public ResponseEntity<List<TicketDto>> getAllTickets(){
        return ResponseEntity.ok(ticketService.getAll());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<TicketDto>> getAllUsersTickets(@PathVariable Long id, @RequestParam boolean active){
        return ResponseEntity.ok(ticketService.getUsersTickets(id, active));
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
