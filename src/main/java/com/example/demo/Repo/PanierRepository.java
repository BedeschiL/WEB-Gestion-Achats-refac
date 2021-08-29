package com.example.demo.Repo;

import com.example.demo.Models.Commande;
import com.example.demo.Models.Item;
import com.example.demo.Models.Panier;
import com.example.demo.Models.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;


public interface PanierRepository extends CrudRepository<Panier, Long> {
    Panier findPanierById(Long id);

    Set<Panier> findPanierByUsers(Users users);
    Set<Panier> findPanierByUsersAndCmd(Users users, Commande cmd);
    Set<Panier> findPanierByUsersAndCmdId(Users users, Long id);

    Set<Panier> findPanierByCmd(Commande commande);
    Panier findPanierByIdAndUsersAndItem(Long id,Users users, Item item);


}
