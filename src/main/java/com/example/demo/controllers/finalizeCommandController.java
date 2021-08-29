package com.example.demo.controllers;

import com.example.demo.Models.Commande;
import com.example.demo.Models.Panier;
import com.example.demo.Repo.CommandeRepository;
import com.example.demo.Repo.ItemRepository;
import com.example.demo.Repo.PanierRepository;
import eu.dattri.jsonbodyhandler.JsonBodyHandler;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Controller
public class finalizeCommandController {

    @Value("${spring.datasource.pathApi}")
    String pathApi;

    @Autowired
    com.example.demo.Repo.ItemRepository ItemRepository;

    @Autowired
    CommandeRepository commandeRepository;

    @Autowired
    PanierRepository panierRepository;

    @GetMapping("/finalizeCommand")
    public String home(HttpServletRequest request, @RequestParam(value = "idComm", defaultValue = "IdCommDefault") String idComm, ModelMap modelMap)
    {

        System.out.println("----------");
        System.out.println("FINALIZE COMMAND");
        System.out.println("Id Comm " + idComm);
        Commande cmd = commandeRepository.findCommandeById(Long.valueOf(idComm));
        Set<Panier> pnSet = panierRepository.findPanierByCmd(cmd);
        float total=0f;






        ///////CALL API TAX
        System.out.printf("PATH API" + pathApi);
            HttpGet requestHttp = new HttpGet(pathApi);

            // add request headers
        requestHttp.addHeader("custom-key", "mkyong");
        requestHttp.addHeader(HttpHeaders.USER_AGENT, "Googlebot");
             CloseableHttpClient httpClient = HttpClients.createDefault();
            try (CloseableHttpResponse response = httpClient.execute(requestHttp)) {
                String result = null;
                String[] parse = new String[0];
                String[] parseCat = new String[0];
                List<String> listCate = new ArrayList<>();
                List<String> listCateFinal = new ArrayList<>();


                List<String> listPourcentage = new ArrayList<>();
                List<String> listPourcentageFinal = new ArrayList<>();
                // Get HttpResponse Status
                System.out.println(response.getStatusLine().toString());

                HttpEntity entity = response.getEntity();
                Header headers = entity.getContentType();
                if (entity != null) {
                    // return it as a String
                    result = EntityUtils.toString(entity);
                    System.out.println(result);
                }
                if(result!=null)
                parse=result.split(",");
                int i=0;
                int j=0;
                while(i<(parse.length/3))
                {
                    listCate.add(parse[j+1]);
                    listPourcentage.add(parse[j+2]);
                    j=j+3;
                    i++;
                }


                int y=0;
                while(y<listPourcentage.size())
                {
                   listCateFinal.add(listCate.get(y).substring(listCate.get(y).indexOf(":") + 2,listCate.get(y).lastIndexOf("\"")));
                    listPourcentageFinal.add(listPourcentage.get(y).substring(listPourcentage.get(y).indexOf(":") + 1,listPourcentage.get(y).indexOf("}")));
                    y++;
                }

                float pourc=0;
                for(Panier val : pnSet)
                {
                    total = total + val.getItem().getPrix() * val.getQuantite();
                    for(int u=0;u<listCate.size();u++)
                        {

                            System.out.println(val.getItem().getCategorie().toUpperCase()  +"   :   " + listCateFinal.get(u).toUpperCase());
                            if(val.getItem().getCategorie().toUpperCase().contains(listCateFinal.get(u).toUpperCase()))
                            {
                                System.out.println("purc" + pourc);
                                pourc = (total * Float.parseFloat(listPourcentageFinal.get(u)) / 100);
                                total = total + pourc;
                            }
                            else
                            {
                                System.out.println("NOPE");
                            }
                        }
                }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
            }
        System.out.println("TOTAL" + total);
            ///
        modelMap.addAttribute("total",total);
        modelMap.addAttribute("panier",pnSet);
        modelMap.addAttribute("idComm",cmd);
        System.out.println("FINALIZE COMMAND");
        System.out.println("----------");
        return "finalizeCommand";


    }
    @GetMapping("/finalFinalize")
    public String finalFinalize(HttpServletRequest request, @RequestParam(value = "idComm", defaultValue = "IdCommDefault") String idComm, ModelMap modelMap)
    {

        System.out.println("----------");
        System.out.println("FINALIZE END");
        Commande cmd = commandeRepository.findCommandeById(Long.valueOf(idComm));
        cmd.setPaiementEffectue(true);
        commandeRepository.save(cmd);
        /*Set<Panier> pnSet = panierRepository.findPanierByCmd(cmd);
        for(Panier val : pnSet)
        {
             panierRepository.delete(val);
        }*/



        System.out.println("FINALIZE END");
        System.out.println("----------");
        modelMap.addAttribute("items", ItemRepository.findAll());
        return "index";


    }
}
