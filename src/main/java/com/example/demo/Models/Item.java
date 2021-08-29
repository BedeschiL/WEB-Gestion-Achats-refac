package com.example.demo.Models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Item {
    //region JPA
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEM_SEQ")
    @SequenceGenerator(sequenceName = "item_seq", allocationSize = 1, name = "ITEM_SEQ")
    private Long idItem;
    @Column(nullable = false)
    private String nom;


    @Column(nullable = false)
    private String categorie;
    @Column(nullable = false)
    private float prix;
    @Column(nullable = false)
    private Integer quantite;

    @OneToMany( targetEntity=Panier.class, mappedBy="item" )
    private List<Item> commands = new ArrayList<>();
    //endregion
    //region override
    @Override
    public String toString() {
        return "Item{" +
                "id=" + idItem +
                ", nom='" + nom + '\'' +
                ", categorie='" + categorie + '\'' +
                ", prix=" + prix +
                ", quantite=" + quantite +
                '}';
    }
    //endregion
    //region CONSTRUC
    public Item() {

    }
    public Item(Long id, String nom, String categorie, float prix, Integer quantite) {
        this.idItem = id;
        this.nom = nom;
        this.categorie = categorie;
        this.prix = prix;
        this.quantite = quantite;
    }
    public Item( String nom, String categorie, float prix, Integer quantite) {
        this.nom = nom;
        this.categorie = categorie;
        this.prix = prix;
        this.quantite = quantite;
    }
    //endregion
    //region SETTERS GETTERS
    public Long getId() {
        return idItem;
    }

    public void setId(Long id) {
        this.idItem = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }
    //endregion
}
