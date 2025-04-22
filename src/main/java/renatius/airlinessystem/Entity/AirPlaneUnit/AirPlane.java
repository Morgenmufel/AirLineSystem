package renatius.airlinessystem.Entity.AirPlaneUnit;

import jakarta.persistence.*;
import lombok.*;
import renatius.airlinessystem.Entity.AbstractEntity.Flight;
import renatius.airlinessystem.Entity.Enum.AirPlaneStatus;

@Entity
@Table
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AirPlane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String planeName;
    @Column
    private String airPlaneModel;
    @Column
    private AirPlaneStatus airPlaneStatus;
    @OneToOne
    private Flight flight;
}
