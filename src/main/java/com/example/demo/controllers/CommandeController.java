/*
 * Copyright (c) Code écrit par Bedeschi Louis.
 */

package com.example.demo.controllers;

import com.example.demo.Models.Commande;
import com.example.demo.Models.Panier;
import com.example.demo.Models.Users;
import com.example.demo.Repo.CommandeRepository;
import com.example.demo.Repo.PanierRepository;
import com.example.demo.Repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Controller
public class CommandeController {

    @Autowired
    UsersRepository clientRepository;

    @Autowired
    com.example.demo.Repo.ItemRepository ItemRepository;

    @Autowired
    CommandeRepository commandeRepository;

    @Autowired
    PanierRepository panierRepository;


    @GetMapping("/commande")
    public String commande(HttpServletRequest request,  ModelMap modelMap)
    {
        Users client = clientRepository.findClientByUsername(request.getRemoteUser());
        Set<Commande> commandes = commandeRepository.findCommandesByUsersAndPaiementEffectue(client,true);
        List<Set<Panier>> listPan = new ArrayList<>();
        List<Panier> finalPan = new ArrayList<>();
        for(Commande val : commandes) {

            listPan.add(panierRepository.findPanierByUsersAndCmd(client,val));
        }

        for(Set<Panier> Lpan : listPan) {
            for(Panier pan : Lpan) {
                finalPan.add(pan);
            }
        }
        int i=0;
        for(Panier pan : finalPan )
        {
            for(Commande val : commandes) {
                    if(pan.getCmd().getId() == val.getId())
                    {
                        val.getPanier().add(pan);
                    }
            }
            i++;
        }
        for(Commande val : commandes) {
            System.out.println(val.getPanier());

        }

        modelMap.addAttribute("commandes",commandes);



        return "commande";
    }
}
