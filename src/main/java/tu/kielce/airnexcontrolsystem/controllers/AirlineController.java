package tu.kielce.airnexcontrolsystem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tu.kielce.airnexcontrolsystem.commands.CreateAirlineCommand;
import tu.kielce.airnexcontrolsystem.commands.UpdateAirlineNameCommand;
import tu.kielce.airnexcontrolsystem.dto.AirlineDto;
import tu.kielce.airnexcontrolsystem.services.AirlineService;


import java.util.ArrayList;
import java.util.List;
/**
 * @author Mariusz Ignaciuk
 */
@Controller
@RequestMapping("/airline")
public class AirlineController {
    private final AirlineService airlineService;

    public AirlineController(AirlineService airlineService) {
        this.airlineService = airlineService;
    }
    @GetMapping
    public String getAll(Model model){
        List<AirlineDto> airlineDtoList = airlineService.getAll();
        if (airlineDtoList == null) {
            airlineDtoList = new ArrayList<>();
        }
        model.addAttribute("airlines", airlineDtoList);
        return "airlines";
    }
    @GetMapping("/{id}")
    public String getById(@PathVariable final Long id, Model model) {
        AirlineDto dto = airlineService.getById(id);
        model.addAttribute("airline", dto);
        return "airline";
    }
    @PostMapping
    public String add(@ModelAttribute CreateAirlineCommand commend) {
        airlineService.add(commend);
        return "redirect:/airline";
    }
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable final Long id) {
        airlineService.delete(id);
        return "redirect:/airline";
    }
    @PostMapping("/{id}/update")
    public String updateName (@PathVariable final Long id, @ModelAttribute UpdateAirlineNameCommand command) {
        airlineService.updateName(id, command);
        return "redirect:/airline";
    }
}
