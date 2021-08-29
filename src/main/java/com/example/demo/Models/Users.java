package com.example.demo.Models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
public class Users {
    //region JPA
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUST_SEQ")
    @SequenceGenerator(sequenceName = "customer_seq", allocationSize = 1, name = "CUST_SEQ")
    private long id;



    private String username;
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private boolean enabled;

    @OneToMany(mappedBy = "users")
    private Set<Commande> commandes;

    @OneToMany( mappedBy = "id")
    private List<Panier> paniers = new ArrayList<>();
    //endregion
    //region override
    @Override
    public String toString() {
        return "Users{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
    //endregion
    //region setter/getter
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    //endregion
    //region construc
    public Users( String username, String password, String email) {

        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled=true;
    }

    public Users() {

    }
//endregion
}