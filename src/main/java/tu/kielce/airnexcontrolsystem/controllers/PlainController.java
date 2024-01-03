package tu.kielce.airnexcontrolsystem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tu.kielce.airnexcontrolsystem.commends.CreatePlainCommand;
import tu.kielce.airnexcontrolsystem.dto.PlainDto;
import tu.kielce.airnexcontrolsystem.services.PlainService;

import java.util.List;

/**
 * @author Maciej Deroń
 */
@RestController
@RequestMapping("/plain")
public class PlainController {
    private final PlainService plainService;

    public PlainController(PlainService plainService) {
        this.plainService = plainService;
    }

    @GetMapping
    public ResponseEntity<List<PlainDto>> getAll() {
        return ResponseEntity.ok(plainService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlainDto> getById(@PathVariable final Long id) {
        PlainDto dto = plainService.getById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody CreatePlainCommand command) {
        plainService.add(command);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable final Long id) {
        plainService.delete(id);
        return ResponseEntity.ok().build();
    }
}
