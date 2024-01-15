package tu.kielce.airnexcontrolsystem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tu.kielce.airnexcontrolsystem.commends.ChangeDepartureTimeCommand;
import tu.kielce.airnexcontrolsystem.commends.ChangePriceCommand;
import tu.kielce.airnexcontrolsystem.commends.CreateFlightCommand;
import tu.kielce.airnexcontrolsystem.dto.FlightDto;
import tu.kielce.airnexcontrolsystem.services.FlightService;

import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightDto> getById(@PathVariable final Long id){
        FlightDto dto = flightService.getById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<FlightDto>> getAll(@RequestParam(required = false) final String departureAirport,
                                                  @RequestParam(required = false) final String arrivalAirport,
                                                  @RequestParam(required = false) final String departureTime,
                                                  @RequestParam(required = false) final String arrivalTime) {
        return ResponseEntity.ok(flightService.findFlights(departureAirport, arrivalAirport, departureTime, arrivalTime));
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody final CreateFlightCommand command) {
        flightService.createFlight(command);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/departure-time")
    public ResponseEntity<?> updateDepartureTime (@PathVariable final Long id, @RequestBody final ChangeDepartureTimeCommand command) {
        flightService.updateDepartureTime(id, command);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/price")
    public ResponseEntity<?> updatePrice (@PathVariable final Long id, @RequestBody final ChangePriceCommand command) {
        flightService.updatePrice(id, command);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable final Long id){
        flightService.deleteFlight(id);
        return ResponseEntity.ok().build();
    }
}
