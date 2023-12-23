package tu.kielce.airnexcontrolsystem.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Maciej Deroń
 */
@RestController
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public String home() {
        return "Air Nex Control System API";
    }
}
