package renatius.airlinessystem.Entity.Crew;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AirborneSensorOperator extends FlightCrew{
}
