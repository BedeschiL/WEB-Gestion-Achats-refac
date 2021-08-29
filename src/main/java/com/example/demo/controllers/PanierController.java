package com.example.demo.controllers;


import com.example.demo.Models.Commande;
import com.example.demo.Models.Item;
import com.example.demo.Models.Panier;
import com.example.demo.Models.Users;
import com.example.demo.Repo.CommandeRepository;
import com.example.demo.Repo.ItemRepository;
import com.example.demo.Repo.PanierRepository;
import com.example.demo.Repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;


@Controller
public class PanierController {
    public static Commande   cmd=null;

    @Autowired
    UsersRepository clientRepository;

    @Autowired
    PanierRepository panierRepository;

    @Autowired
    CommandeRepository commandeRepository;

    @Autowired
    ItemRepository itemRepository;
    //region PANIER
    @GetMapping("/panier")
    public String home(HttpServletRequest request, @RequestParam(value = "id", defaultValue = "idDefault") String id, @RequestParam(value = "quantite", defaultValue = "QuantiteDefault") String quantite, Model model) {
        System.out.println("-------------------");
        System.out.println("PANIER");
        Users client = clientRepository.findClientByUsername(request.getRemoteUser());



        Set<Commande> commandes = commandeRepository.findCommandesByUsersAndPaiementEffectue(client, false);
        if(commandes.isEmpty())
        {
            System.out.println("Empty");
            cmd = new Commande(client);
            commandeRepository.save(cmd);
        }
        else
        {
            System.out.println("Cmd");
            System.out.println("Cmd Id " + cmd.getId());
            model.addAttribute("cmd", cmd);
        }
        System.out.println("END PANIER");
        System.out.println("-------------------");
        model.addAttribute("panier", panierRepository.findPanierByUsersAndCmd(client,cmd));
        return "panier";
    }
    //endregion
    //region ADDITEM
    @GetMapping("/additem")
    public String additem(HttpServletRequest request, @RequestParam(value = "id", defaultValue = "fail") String id, @RequestParam(value = "quantite", defaultValue = "uninit") String quantite, Model model)
    {
        System.out.println("-------------------");
        System.out.println("ADD ITEM");
        //SI LA COMMANDE EST VIDE ON CREE UNE COMMANDE SINON ON AJOUTE A L'ANCIENNE
        Users client = clientRepository.findClientByUsername(request.getRemoteUser());
        System.out.println(client.toString());

        Set<Commande> commandes = commandeRepository.findCommandesByUsersAndPaiementEffectue(client, false);
        if(commandes.isEmpty()) {
            System.out.println("ADD ITEM VIDE");
         cmd = new Commande(client);
            commandeRepository.save(cmd);
            Item item = itemRepository.findItemByidItem(Long.valueOf(id));
            item.setQuantite((item.getQuantite())-Integer.parseInt(quantite));
            itemRepository.save(item);
            Panier pan = new Panier(Integer.parseInt(quantite),item,client,cmd);
            panierRepository.save(pan);
        }
        else
        {
            System.out.println("ELSE ADD ITEM");
            Item item = itemRepository.findItemByidItem(Long.valueOf(id));
            item.setQuantite((item.getQuantite())-Integer.parseInt(quantite));
            itemRepository.save(item);
            Panier pan = new Panier(Integer.parseInt(quantite),item,client,cmd);
            panierRepository.save(pan);

            System.out.println("ARTICLE AJOUTER AU PANIER" + item.toString() + " : " +quantite );
        }

        model.addAttribute("items", itemRepository.findAll());
        System.out.println("FIN ADD ITEM");
        System.out.println("-------------------");
        return "index";
    }
    //endregion
    //region REMOVEITEM
    @GetMapping("/removeItem")
    public String removeItem(HttpServletRequest request, @RequestParam(value = "idPan", defaultValue = "idDefault") String idPan,@RequestParam(value = "idItem", defaultValue = "idDefault") String idItem ,@RequestParam(value = "quantite", defaultValue = "quantiteDefault") String quantite, Model model)
    {
        System.out.println("-------------------");
        Users client = clientRepository.findClientByUsername(request.getRemoteUser());
        Set<Commande> commandes = commandeRepository.findCommandesByUsersAndPaiementEffectue(client, false);
        if(commandes.isEmpty())
        {
            System.out.println("Empty");
            cmd = new Commande(client);
            commandeRepository.save(cmd);
        }
        else
        {
            System.out.println("Cmd");
            System.out.println("Cmd Id " + cmd.getId());
            model.addAttribute("cmd", cmd);
        }



        //On recup l'item ciblé par le remove
        Item item = itemRepository.findItemByidItem(Long.valueOf(idItem));
        //on ajoute le montant de quantite qu'on a supp
        item.setQuantite((item.getQuantite())+Integer.parseInt(quantite));
        itemRepository.save(item);

        //On recup l'user co pour chercher le panier en fonction de l'utilisateur et du l'item selec
        Users usrLoged = clientRepository.findClientByUsername(request.getRemoteUser());
        Panier pUser = panierRepository.findPanierByIdAndUsersAndItem(Long.valueOf(idPan),usrLoged,item);
        panierRepository.delete(pUser);



        model.addAttribute("panier", panierRepository.findPanierByUsers(usrLoged));
        System.out.println("-------------------");
        return "panier";
    }
    //endregion


}
