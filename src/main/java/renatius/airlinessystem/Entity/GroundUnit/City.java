package renatius.airlinessystem.Entity.GroundUnit;

import jakarta.persistence.*;
import lombok.*;
import renatius.airlinessystem.Entity.Enum.WeatherStatus;

@Entity
@Table
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private int population;

    @Column
    private boolean airportExists;

    @Column
    private String country;

    @Column
    private WeatherStatus weatherStatus;
}
