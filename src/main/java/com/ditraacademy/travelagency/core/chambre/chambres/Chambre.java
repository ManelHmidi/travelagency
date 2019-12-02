package com.ditraacademy.travelagency.core.chambre.chambres;

import com.ditraacademy.travelagency.core.chambre.categorieChambre.Categorie;
import com.ditraacademy.travelagency.core.chambre.typeChambre.TypeChambre;
import com.ditraacademy.travelagency.core.hotel.Hotel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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
    private Categorie categorieChambre;

    @JsonIgnore
    @ManyToMany(mappedBy = "chambres")
    private List<Hotel> hotels;

}
