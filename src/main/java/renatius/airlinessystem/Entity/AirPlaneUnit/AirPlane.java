package renatius.airlinessystem.Entity.AirPlaneUnit;

import jakarta.persistence.*;
import lombok.*;
import renatius.airlinessystem.Entity.AbstractEntity.Flight;

@Entity
@Table(name = "air_plane")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AirPlane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int airplane_id;
    @Column(name = "plane_name")
    private String planeName;
    @Column(name = "air_plane_model")
    private String airPlaneModel;
    @Column(name = "air_plane_status")
    private String airPlaneStatus;
    @Column(name="long_of_way")
    private int longOfWay;
    @OneToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;
}
