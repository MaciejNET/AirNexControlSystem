package tu.kielce.airnexcontrolsystem.controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tu.kielce.airnexcontrolsystem.commands.ChangeDepartureTimeCommand;
import tu.kielce.airnexcontrolsystem.commands.ChangePriceCommand;
import tu.kielce.airnexcontrolsystem.commands.CreateFlightCommand;
import tu.kielce.airnexcontrolsystem.dto.FlightDto;
import tu.kielce.airnexcontrolsystem.services.FlightService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Maciej Deroń
 */
@Controller
@RequestMapping("/flight")
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable final Long id, Model model){
        FlightDto dto = flightService.getById(id);
        model.addAttribute("flight", dto);
        return "flight";
    }

    @GetMapping
    public String getAll(@RequestParam(required = false) final String departureAirport,
                         @RequestParam(required = false) final String arrivalAirport,
                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime departureTime,
                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime arrivalTime,
                         Model model) {
        String depAirport = departureAirport != null && !departureAirport.isEmpty() ? departureAirport : null;
        String arrAirport = arrivalAirport != null && !arrivalAirport.isEmpty() ? arrivalAirport : null;

        List<FlightDto> dtos = flightService.findFlights(depAirport, arrAirport, departureTime, arrivalTime);
        if (dtos == null) {
            dtos = List.of();
        }
        model.addAttribute("flights", dtos);
        return "flights";
    }

    @PostMapping
    public String add(@ModelAttribute final CreateFlightCommand command) {
        flightService.createFlight(command);
        return "redirect:/flight";
    }

    @PostMapping("/{id}/update-departure-time")
    public String updateDepartureTime (@PathVariable final Long id, @ModelAttribute final ChangeDepartureTimeCommand command) {
        flightService.updateDepartureTime(id, command);
        return "redirect:/flight";
    }

    @PostMapping("/{id}/update-price")
    public String updatePrice (@PathVariable final Long id, @ModelAttribute final ChangePriceCommand command) {
        flightService.updatePrice(id, command);
        return "redirect:/flight";
    }

    @PostMapping("/{id}/delete")
    public String delete (@PathVariable final Long id){
        flightService.deleteFlight(id);
        return "redirect:/flight";
    }
}
