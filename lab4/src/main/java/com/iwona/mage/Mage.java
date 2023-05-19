package com.iwona.mage;

import com.iwona.tower.Tower;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Mage {
    @Id
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private int level;

    @Getter
    @Setter
    @ManyToOne
    private Tower tower;


    public Mage(String name, int level, Tower tower) {
        this.name = name;
        this.level = level;
        this.tower = tower;
    }
}
