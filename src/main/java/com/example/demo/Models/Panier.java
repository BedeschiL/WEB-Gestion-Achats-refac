/*
 * Copyright (c) Code écrit par Bedeschi Louis.
 */

package com.example.demo.Models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Panier implements Serializable {
    //region JPA
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PANIER_SEQ")
    @SequenceGenerator(sequenceName = "panier_seq", allocationSize = 1, name = "PANIER_SEQ")
    private Long id;

    @Column(nullable = false)
    private Integer quantite;

    @ManyToOne
    @JoinColumn(name="idItem", nullable=false)
    private Item item;


    @ManyToOne(optional = false)
    private Commande cmd;



    @ManyToOne(optional = false)
    private Users users;

    public Commande getCmd() {
        return cmd;
    }

    public void setCmd(Commande cmd) {
        this.cmd = cmd;
    }

    //endregion
    //region override
    @Override
    public String toString() {
        return "Nom : " + this.getItem().getNom() +"\n"
                + "Prix : " + this.getItem().getPrix() +"\n"
                + "Quantite : " + this.getQuantite() +"\n";
    }
    //endregion
    //region Constructeur
    protected Panier() {

    }
    public Panier(Long id, Integer quantite) {
        this.id = id;
        this.quantite = quantite;
    }
    public Panier(Integer quantite, Item item) {
        this.quantite = quantite;
        this.item=item;
    }

    public Panier(int parseInt, Item article, Users users, Commande cmd) {
        this.quantite = parseInt;
        this.item=article;
        this.users = users;
        this.cmd = cmd;
    }
    //endregion
    //region SET/GET
    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }
    //endregion
}
