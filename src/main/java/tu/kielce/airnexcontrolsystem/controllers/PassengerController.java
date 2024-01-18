package tu.kielce.airnexcontrolsystem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tu.kielce.airnexcontrolsystem.commands.CreatePassengerCommand;
import tu.kielce.airnexcontrolsystem.commands.UpdateEmailCommand;
import tu.kielce.airnexcontrolsystem.dto.PassengerDto;
import tu.kielce.airnexcontrolsystem.services.PassengerService;

import java.util.List;

/**
 * @author Julia Dziekańska
 */
@Controller
@RequestMapping("/passenger")
public class PassengerController {
    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping("{id}")
    public String getPassenger(@PathVariable Long id, Model model) {
        PassengerDto passenger = passengerService.getPassenger(id);
        model.addAttribute("passenger", passenger);
        return "passenger";
    }

    @GetMapping
    public String getAllPassengers(Model model) {
        List<PassengerDto> passengers = passengerService.getAllPassengers();
        if (passengers == null) {
            passengers = List.of();
        }
        model.addAttribute("passengers", passengers);
        return "passengers";
    }

    @PostMapping
    public String createPassenger(@ModelAttribute CreatePassengerCommand command) {
        passengerService.createPassenger(command);
        return "redirect:/passenger";
    }

    @PostMapping("{id}/update")
    public String updatePassenger(@PathVariable Long id, @ModelAttribute UpdateEmailCommand command) {
        passengerService.updatePassenger(id, command);
        return "redirect:/passenger";
    }

    @PostMapping("{id}/delete")
    public String deletePassenger(@PathVariable Long id) {
        passengerService.deletePassenger(id);
        return "redirect:/passenger";
    }
}
