package hr.fer.tel.rasus.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Beer {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Double alcoholVol;

    public Beer(String name, Double alcoholVol) {
        this.name = name;
        this.alcoholVol = alcoholVol;
    }
}
