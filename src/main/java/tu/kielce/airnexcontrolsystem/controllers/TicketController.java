package tu.kielce.airnexcontrolsystem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tu.kielce.airnexcontrolsystem.commands.BuyTicketCommand;
import tu.kielce.airnexcontrolsystem.dto.TicketDto;
import tu.kielce.airnexcontrolsystem.services.TicketService;

import java.util.List;

/**
 * @author Mariusz Ignaciuk, Paweł Dostal, Julia Dziekańska
 */
@Controller
@RequestMapping("/ticket")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/{id}")
    public String getTicket(@PathVariable Long id, Model model){
        TicketDto ticket = ticketService.getById(id);
        model.addAttribute("ticket", ticket);
        return "ticket";
    }

    @GetMapping("/user/{id}")
    public String getAllUsersTickets(@PathVariable Long id, @RequestParam boolean active, Model model){
        List<TicketDto> tickets = ticketService.getUsersTickets(id, active);
        if (tickets == null) {
            tickets = List.of();
        }
        model.addAttribute("tickets", tickets);
        return "passengerTickets";
    }

    @PostMapping("/{id}/delete")
    public String refundTicket(@PathVariable Long id){
        ticketService.refundTicket(id);
        return "redirect:/passenger";
    }

    @PostMapping
    public String add(@ModelAttribute BuyTicketCommand command){
        ticketService.buyTicket(command);
        return "redirect:/passenger";
    }
}
