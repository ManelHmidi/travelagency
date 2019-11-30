package com.ditraacademy.travelagency.core.chambre.chambres;

import com.ditraacademy.travelagency.core.chambre.categorieChambre.CategorieChambre;
import com.ditraacademy.travelagency.core.chambre.typeChambre.TypeChambre;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"categorie_chambre_id","type_chambre_id"})
})
public class Chambre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private TypeChambre typeChambre;
    @ManyToOne
    private CategorieChambre categorieChambre;
}
