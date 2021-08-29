package com.example.demo.Repo;

import com.example.demo.Models.Commande;
import com.example.demo.Models.Item;
import com.example.demo.Models.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface CommandeRepository extends CrudRepository<Commande, Long> {
    Set<Commande> findCommandesByUsersAndPaiementEffectue(Users client, Boolean paiementEffectue);
    Commande findCommandeById(Long id);

}
