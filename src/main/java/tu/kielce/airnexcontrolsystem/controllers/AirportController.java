package tu.kielce.airnexcontrolsystem.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tu.kielce.airnexcontrolsystem.commends.CreateAirportCommand;
import tu.kielce.airnexcontrolsystem.commends.UpdateAirportNameCommand;
import tu.kielce.airnexcontrolsystem.dto.AirportDto;
import tu.kielce.airnexcontrolsystem.services.AirportService;

import java.util.List;

/**
 * @author Paweł Dostal
 */

@RestController
@RequestMapping("/airport")
public class AirportController {

    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping
    public ResponseEntity<List<AirportDto>> getAll() {
        return ResponseEntity.ok(airportService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirportDto> getById(@PathVariable final Long id){
        AirportDto dto = airportService.getById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody CreateAirportCommand command) {
        airportService.add(command);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable final Long id){
        airportService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateName (@PathVariable final Long id, @RequestBody UpdateAirportNameCommand command) {
        airportService.updateName(id, command);
        return ResponseEntity.ok().build();
    }
}




