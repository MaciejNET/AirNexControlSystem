package tu.kielce.airnexcontrolsystem.specifications;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import tu.kielce.airnexcontrolsystem.entities.Flight;

import java.time.LocalDateTime;

/**
 * @author Maciej Deroń
 */
public class FlightSpecification implements Specification<Flight> {
    private final String departureAirport;
    private final String arrivalAirport;
    private final LocalDateTime departureTime;
    private final LocalDateTime arrivalTime;

    public FlightSpecification(String departureAirport, String arrivalAirport, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    @Override
    public Predicate toPredicate(final Root<Flight> root, final CriteriaQuery<?> query, final CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.conjunction();

        if (departureAirport != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("departureAirport").get("name"), departureAirport));
        }

        if (arrivalAirport != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("arrivalAirport").get("name"), arrivalAirport));
        }

        if (departureTime != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("departureTime"), departureTime));
        }

        if (arrivalTime != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("arrivalTime"), arrivalTime));
        }

        return predicate;
    }
}
