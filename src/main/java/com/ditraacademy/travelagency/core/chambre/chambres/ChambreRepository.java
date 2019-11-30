package com.ditraacademy.travelagency.core.chambre.chambres;


import com.ditraacademy.travelagency.core.chambre.categorieChambre.CategorieChambre;
import com.ditraacademy.travelagency.core.chambre.typeChambre.TypeChambre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ChambreRepository extends JpaRepository<Chambre, Integer> {
    Optional<Chambre> findByCategorieChambreAndTypeChambre(CategorieChambre c, TypeChambre t);
}
