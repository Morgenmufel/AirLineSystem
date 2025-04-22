package renatius.airlinessystem.Entity.Crew;

import jakarta.persistence.*;
import lombok.*;
import renatius.airlinessystem.Entity.AbstractEntity.Flight;
import renatius.airlinessystem.Entity.Enum.CrewStatus;

@Entity
@Table
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlightCrew {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int crewID;

    @Column
    private String crewName;

    @Column
    private String crewSurname;

    @Column
    private String crewLastname;

    @Column
    private int crewAge;

    @Column
    private String crewSex;

    @Column
    private CrewStatus status;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;
}
