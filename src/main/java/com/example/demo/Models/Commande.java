package com.example.demo.Models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Commande {
    //region JPA
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMANDE_SEQ")
    @SequenceGenerator(sequenceName = "commande_seq", allocationSize = 1, name = "COMMANDE_SEQ")
    private Long id;

    @Column(nullable = false)
    private String statut;

    @Column(nullable = false)
    private float montant;

    @Column(nullable = false)
    private Boolean paiementEffectue;

    @ManyToOne(optional = false)
    private Users users;



    @OneToMany(mappedBy = "id")
    private Set<Panier> Panier;
    //endregion
    //region override
    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", statut='" + statut + '\'' +
                ", montant=" + montant +
                ", paiementEffectue=" + paiementEffectue +
                ", users=" + users +
                ", PanierSize=" + Panier.size() +
                '}';
    }
    //endregion
    //region Construc
    public Commande() {

    }
    public Commande(Users client) {
        this.users = client;
        this.statut = "EN PREPARATION";
        this.montant = 0L;
        this.paiementEffectue = false;


    }
    //endregion
    //region GET/SET
    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Set<com.example.demo.Models.Panier> getPanier() {
        return Panier;
    }

    public void setPanier(Set<com.example.demo.Models.Panier> panier) {
        Panier = panier;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getClient() {
        return users;
    }

    public void setClient(Users client) {
        this.users = client;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public Boolean getPaiementEffectue() {
        return paiementEffectue;
    }

    public void setPaiementEffectue(Boolean paiementEffectue) {
        this.paiementEffectue = paiementEffectue;
    }
    //endregion
}
