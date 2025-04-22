package renatius.airlinessystem.Entity.AbstractEntity;

import jakarta.persistence.*;
import lombok.*;
import renatius.airlinessystem.Entity.AirPlaneUnit.AirPlane;
import renatius.airlinessystem.Entity.Crew.FlightCrew;

import java.util.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private LocalTime flightTime;

    @Column
    private LocalDate flightDate;

    @OneToOne
    private AirPlane airPlane;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "flight")
    private List<FlightCrew> flightCrewList;
}
