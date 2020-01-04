package com.ditraacademy.travelagency.core.hotel;

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
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    private Integer etoiles;
    private String description;
    private String adresse;
    private Integer telephone;

    @ManyToMany
    @JoinTable(name = "hotel_chambre",
               joinColumns = {@JoinColumn(name = "hotel_id")},
               inverseJoinColumns = {@JoinColumn(name = "chambre_id")})
    private List<Chambre> chambres;

    void addChambre(Chambre chambre) {
        chambres.add(chambre);
    }
}
