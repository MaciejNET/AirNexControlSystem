package tu.kielce.airnexcontrolsystem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tu.kielce.airnexcontrolsystem.commands.CreatePassengerCommand;
import tu.kielce.airnexcontrolsystem.commands.UpdateEmailCommand;
import tu.kielce.airnexcontrolsystem.dto.PassengerDto;
import tu.kielce.airnexcontrolsystem.services.PassengerService;

import java.util.List;

/**
 * @author Julia Dziekańska
 */
@RestController
@RequestMapping("/passenger")
public class PassengerController {
    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping("{id}")
    public ResponseEntity<PassengerDto> getPassenger(@PathVariable Long id) {
        PassengerDto passenger = passengerService.getPassenger(id);
        if (passenger == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(passenger);
    }

    @GetMapping
    public ResponseEntity<List<PassengerDto>> getAllPassengers() {
        return ResponseEntity.ok(passengerService.getAllPassengers());
    }

    @PostMapping
    public ResponseEntity<Void> createPassenger(@RequestBody CreatePassengerCommand command) {
        passengerService.createPassenger(command);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updatePassenger(@PathVariable Long id, @RequestBody UpdateEmailCommand command) {
        passengerService.updatePassenger(id, command);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePassenger(@PathVariable Long id) {
        passengerService.deletePassenger(id);
        return ResponseEntity.ok().build();
    }
}
