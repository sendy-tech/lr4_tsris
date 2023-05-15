package sport.competitions.db.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "COMPETITION")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Competition implements Serializable {

    public Competition(Integer number, String name, String date, String place) {
        this.number = number;
        this.name = name;
        this.date = date;
        this.place = place;
    }

    @Id
    @Column(name = "COMPETITION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "COMPETITION_NUMBER", unique = true)
    private Integer number;

    @Column(name = "COMPETITION_NAME")
    private String name;

    @Column(name = "COMPETITION_DATE")
    private String date;

    @Column(name = "COMPETITION_PLACE")
    private String place;
}
