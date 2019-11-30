package com.ditraacademy.travelagency.core.chambre.categorieChambre;

import com.ditraacademy.travelagency.core.chambre.chambres.Chambre;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class CategorieChambre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String categorie;
    private String description;

}
