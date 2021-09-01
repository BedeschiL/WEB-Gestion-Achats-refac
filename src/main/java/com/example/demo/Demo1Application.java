package com.example.demo;

import com.example.demo.Models.Item;
import com.example.demo.Models.TVA;
import com.example.demo.Models.Users;
import com.example.demo.RabbitMQ.recv;
import com.example.demo.RabbitMQ.send;
import com.example.demo.Repo.ItemRepository;
import com.example.demo.Repo.TVARepository;
import com.example.demo.Repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;
import java.util.Optional;

@SpringBootApplication
public class Demo1Application {

    @Autowired
    DataSource dataSource;

    @Autowired
    UsersRepository customerRepository;

    @Autowired
    TVARepository TVARepository;

    public static void main(String[] args) {
        SpringApplication.run(Demo1Application.class, args);
    }



    @Bean
    public CommandLineRunner demo(UsersRepository usrRep, ItemRepository itemRep) {

        //Optional<Users> customer = customerRepository.findById(1L);
        return (args) -> {

            //region Users
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String result = encoder.encode("password");
            String LouisPw = encoder.encode("Test");
            Users usr = new Users("Louis",LouisPw,"xeiinon@gmail.com");
            Users usr2 = new Users("Test", result,"xeiinon@gmail.com");
            TVA tva = new TVA("LIVRES",5f);
            TVA tva2 = new TVA("FILMS",10f);
            TVARepository.save(tva);
            TVARepository.save(tva2);



            usrRep.save(usr);
            usrRep.save(usr2);
            //endregion
            //region Articles
            // public Item( String nom, String categorie, float prix, Integer quantite)
            Item item0 = new Item("Star wars Legacy","Livres",14.5f,15);
            Item item1 = new Item("Oui oui a la ferme","Livres",5f,5);
            itemRep.save(item0);
            itemRep.save(item1);
            recv recv = new recv("reponse");
            recv.run();




            //endregion


        };


    }
}
