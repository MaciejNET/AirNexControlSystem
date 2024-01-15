package tu.kielce.airnexcontrolsystem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tu.kielce.airnexcontrolsystem.commends.CreatePassengerCommand;
import tu.kielce.airnexcontrolsystem.dto.PassengerDto;
import tu.kielce.airnexcontrolsystem.services.PassengerService;

import java.util.List;

/**
 * @author Julia Dziekańska
 */
@RestController
@RequestMapping("/passengers")
public class PassengerController {
    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping
    public ResponseEntity<List<PassengerDto>> getAllPassengers() {
        List<PassengerDto> passengers = passengerService.getAllPassengers();
        return ResponseEntity.ok(passengers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PassengerDto> getPassengerById(@PathVariable final Long id) {
        PassengerDto passengerDTO = passengerService.getPassengerById(id);
        if (passengerDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(passengerDTO);
    }

    @PostMapping
    public ResponseEntity<PassengerDto> createPassenger(@RequestBody CreatePassengerCommand command) {
        PassengerDto createdPassenger = passengerService.createPassenger(command);
        return ResponseEntity.ok(createdPassenger);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PassengerDto> updatePassenger(@PathVariable final Long id, @RequestBody CreatePassengerCommand command) {
        PassengerDto updatedPassenger = passengerService.updatePassenger(id, command);
        if (updatedPassenger == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedPassenger);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePassenger(@PathVariable final Long id) {
        passengerService.deletePassenger(id);
        return ResponseEntity.ok().build();
    }
}
