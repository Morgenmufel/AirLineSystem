package renatius.airlinessystem.Entity.GroundUnit;

import jakarta.persistence.*;
import lombok.*;
import renatius.airlinessystem.Entity.Enum.WeatherStatus;

@Entity
@Table(name = "airport")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "airport_name")
    private String name;


    @Column(name = "country")
    private String country;

    @Column(name = "weather_status")
    private WeatherStatus weatherStatus;
}
