package tu.kielce.airnexcontrolsystem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tu.kielce.airnexcontrolsystem.commends.CreateAirlineCommand;
import tu.kielce.airnexcontrolsystem.commends.UpdateAirlineNameCommand;
import tu.kielce.airnexcontrolsystem.dto.AirlineDto;
import tu.kielce.airnexcontrolsystem.dto.PlainDto;
import tu.kielce.airnexcontrolsystem.services.AirlineService;


import java.util.List;
/**
 * @author Mariusz Ignaciuk
 */
@RestController
@RequestMapping("/airline")
public class AirlineController {
    private final AirlineService airlineService;

    public AirlineController(AirlineService airlineService) {
        this.airlineService = airlineService;
    }
    @GetMapping
    public ResponseEntity<List<AirlineDto>> getAll(){
        return ResponseEntity.ok(airlineService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<AirlineDto> getById(@PathVariable final Long id) {
        AirlineDto dto = airlineService.getById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }
    @PostMapping
    public ResponseEntity<?> add(@RequestBody CreateAirlineCommand commend) {
        airlineService.add(commend);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable final Long id) {
        airlineService.delete(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateName (@PathVariable final Long id, @RequestBody UpdateAirlineNameCommand command) {
        airlineService.updateName(id, command);
        return ResponseEntity.ok().build();
    }
}
