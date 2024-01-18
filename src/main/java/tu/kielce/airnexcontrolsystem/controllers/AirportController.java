package tu.kielce.airnexcontrolsystem.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tu.kielce.airnexcontrolsystem.commands.CreateAirportCommand;
import tu.kielce.airnexcontrolsystem.commands.UpdateAirportNameCommand;
import tu.kielce.airnexcontrolsystem.dto.AirportDto;
import tu.kielce.airnexcontrolsystem.services.AirportService;

import java.util.List;

/**
 * @author Paweł Dostal
 */

@Controller
@RequestMapping("/airport")
public class AirportController {

    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping
    public String getAll(Model model){
        List<AirportDto> airportDtoList = airportService.getAll();
        if (airportDtoList == null) {
            airportDtoList = List.of();
        }
        model.addAttribute("airports", airportDtoList);
        return "airports";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable final Long id, Model model){
        AirportDto dto = airportService.getById(id);
        model.addAttribute("airport", dto);
        return "airport";
    }

    @PostMapping
    public String add(@ModelAttribute CreateAirportCommand command) {
        airportService.add(command);
        return "redirect:/airport";
    }

    @PostMapping("/{id}/delete")
    public String delete (@PathVariable final Long id){
        airportService.delete(id);
        return "redirect:/airport";
    }

    @PostMapping("/{id}/update")
    public String updateName (@PathVariable final Long id, @ModelAttribute UpdateAirportNameCommand command) {
        airportService.updateName(id, command);
        return "redirect:/airport";
    }
}




