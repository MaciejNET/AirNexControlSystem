package tu.kielce.airnexcontrolsystem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tu.kielce.airnexcontrolsystem.commends.CreatePlaneCommand;
import tu.kielce.airnexcontrolsystem.dto.PlaneDto;
import tu.kielce.airnexcontrolsystem.services.PlaneService;
import java.util.List;

/**
 * @author Maciej Deroń
 */
@RestController
@RequestMapping("/plane")
public class PlaneController {
    private final PlaneService planeService;

    public PlaneController(PlaneService planeService) {
        this.planeService = planeService;
    }

    @GetMapping
    public ResponseEntity<List<PlaneDto>> getAll() {
        return ResponseEntity.ok(planeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaneDto> getById(@PathVariable final Long id) {
        PlaneDto dto = planeService.getById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody CreatePlaneCommand command) {
        planeService.add(command);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable final Long id) {
        planeService.delete(id);
        return ResponseEntity.ok().build();
    }
}
