package renatius.airlinessystem.Entity.AbstractEntity;

import jakarta.persistence.*;
import lombok.*;
import renatius.airlinessystem.Entity.AirPlaneUnit.AirPlane;
import renatius.airlinessystem.Entity.Crew.FlightCrew;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "flight")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "departureTime")
    private LocalDateTime departureTime;

    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;

    @OneToOne(mappedBy = "flight")
    private AirPlane airPlane;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "flight")
    private List<FlightCrew> flightCrewList;

    @Column(name = "from_airport")
    private String fromAirport;

    @Column(name = "to_airport")
    private String toAirport;
}
