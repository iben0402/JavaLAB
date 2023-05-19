package com.iwona.tower;

import com.iwona.mage.Mage;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class Tower {
    @Id
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private int height;

    @Setter
    @Getter
    @OneToMany(mappedBy = "tower")
    private List<Mage> mages;

    public Tower(String name, int height) {
        this.name = name;
        this.height = height;
    }
}
