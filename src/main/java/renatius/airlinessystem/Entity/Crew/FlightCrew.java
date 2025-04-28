package renatius.airlinessystem.Entity.Crew;

import jakarta.persistence.*;
import lombok.*;
import renatius.airlinessystem.Entity.AbstractEntity.Flight;
import renatius.airlinessystem.Entity.Enum.CrewStatus;

@Entity
@Table(name = "flight_crew")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class FlightCrew {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int crewID;

    @Column(name = "crew_name")
    private String crewName;


    @Column(name = "crew_status")
    private CrewStatus status;

    @Column(name = "crew_age")
    private int age;

    @Column(name = "crew_sex")
    private String sex;

    @Column(name = "post")
    private String post;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

}
