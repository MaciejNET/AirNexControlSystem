package tu.kielce.airnexcontrolsystem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tu.kielce.airnexcontrolsystem.commands.CreatePlaneCommand;
import tu.kielce.airnexcontrolsystem.dto.PlaneDto;
import tu.kielce.airnexcontrolsystem.services.PlaneService;
import java.util.List;

/**
 * @author Maciej Deroń
 */
@Controller
@RequestMapping("/plane")
public class PlaneController {
    private final PlaneService planeService;

    public PlaneController(PlaneService planeService) {
        this.planeService = planeService;
    }

    @GetMapping
    public String getAll(Model model) {
        List<PlaneDto> dtos = planeService.getAll();
        if (dtos == null) {
            dtos = List.of();
        }
        model.addAttribute("planes", dtos);
        return "planes";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable final Long id, Model model) {
        PlaneDto dto = planeService.getById(id);
        model.addAttribute("plane", dto);
        return "plane";
    }

    @PostMapping
    public String add(@ModelAttribute CreatePlaneCommand command) {
        planeService.add(command);
        return "redirect:/plane";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable final Long id) {
        planeService.delete(id);
        return "redirect:/plane";
    }
}
