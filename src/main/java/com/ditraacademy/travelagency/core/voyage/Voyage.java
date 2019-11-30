package com.ditraacademy.travelagency.core.voyage;

import com.ditraacademy.travelagency.core.destination.Destination;
import com.ditraacademy.travelagency.utils.Audible;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Where(clause = "deleted= false")
@SQLDelete(sql="update voyage set deleted = true where id = ?")
public class Voyage extends Audible {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titre;
    private String description;
    private Integer nbPlaces;
    private Float prix;
    private Date date;
    private Boolean deleted = false;

    @ManyToOne(cascade ={CascadeType.PERSIST})
    private Destination destination;
}
