/*
 * Copyright (c) Code écrit par Bedeschi Louis.
 */

package com.example.demo.Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class TVA {
    //region JPA
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TVA_SEQ")
    @SequenceGenerator(sequenceName = "tva_seq", allocationSize = 1, name = "TVA_SEQ")
    private Long id;
    @Column(nullable = false)
    private String categorie;
    @Column(nullable = false)
    private float pourcentage;
    //endregion
    //region construc
    public TVA() {

    }
    public TVA(Long id, String categorie, float pourcentage) {
        this.id = id;
        this.categorie = categorie;
        this.pourcentage = pourcentage;
    }

    public TVA(String livres, float s) {
        this.categorie = livres;
        this.pourcentage=s;
    }

    //endregion
    //region override
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TVA tva = (TVA) o;
        return Float.compare(tva.pourcentage, pourcentage) == 0 && Objects.equals(id, tva.id) && Objects.equals(categorie, tva.categorie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categorie, pourcentage);
    }

    @Override
    public String toString() {
        return "TVA{" +
                "id=" + id +
                ", categorie='" + categorie + '\'' +
                ", pourcentage=" + pourcentage +
                '}';
    }
    //endregion
    //region setter/getter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public float getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(float pourcentage) {
        this.pourcentage = pourcentage;
    }
    //endregion

}
