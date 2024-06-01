package com.aiscoworking.aiscoworking;

import com.aiscoworking.aiscoworking.model.*;
import com.aiscoworking.aiscoworking.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;

@Configuration
public class DatabaseLoader {

    @Bean
    CommandLineRunner init(AisUserGroupRepository aisUserGroupRepository,
                           AisUserRepository aisUserRepository,
                           CoworkingRepository coworkingRepository,
                           PromotionRepository promotionRepository,
                           TimeRepository timeRepository,
                           PlaceRepository placeRepository,
                           PasswordEncoder encoder) {
        java.util.Date date = new java.util.Date();
        System.out.println("Time: ");
        System.out.println(date.getTime());
        // creating user group
        AisUserGroup clientGroup = new AisUserGroup(1, "ROLE_CLIENT");
        AisUserGroup coworkingEmployeeGroup = new AisUserGroup(2, "ROLE_COWORKING_EMPLOYEE");
        AisUserGroup coworkingOwnerGroup = new AisUserGroup(3, "ROLE_COWORKING_OWNER");
        AisUserGroup ownerGroup = new AisUserGroup(4, "ROLE_OWNER");
        AisUserGroup adminGroup = new AisUserGroup(5, "ROLE_ADMIN");
        // creating users
        AisUser aisUser1 = new AisUser(1, "Artem", "Artem", encoder.encode("123"),0);
        aisUser1.getAisUserGroups().add(adminGroup);
        aisUser1.getAisUserGroups().add(ownerGroup);
        AisUser aisUser2 = new AisUser(2, "Nikita","Nikita", encoder.encode("123"), 0);
        aisUser2.getAisUserGroups().add(coworkingOwnerGroup);
        aisUser2.getAisUserGroups().add(coworkingEmployeeGroup);
        AisUser aisUser3 = new AisUser(3, "Anton","Anton", encoder.encode("123"), 0);
        aisUser3.getAisUserGroups().add(clientGroup);
        // creating coworkings
        Coworking coworking1 = new Coworking(1, "coworking1", "Saint Petersburg", 1000.0,
                2000.0, 0);
        Coworking coworking2 = new Coworking(2, "coworking2", "Moscow", 1050.0,
                1950.0, 0);
        // creating time
        Time time1 = new Time(1,
                new Date(123, 11, 10),
                new Date(123, 11, 10),
                new java.sql.Time(12, 30, 0),
                new java.sql.Time(14, 0, 0)
        );
        // creating promotion
        Promotion promotion1 = new Promotion(1, "discount15", 15);
        Promotion promotion2 = new Promotion(2, "discount5", 5);
        // creating places
        Place place1 = new Place(1, 1, 10000, false);
        place1.setCoworking(coworking1);
        place1.setTime(time1);
        Place place2 = new Place(2, 2, 15000, false);
        place2.setCoworking(coworking1);
        place2.setTime(time1);
        Place place3 = new Place(3, 1, 15000, false);
        place3.setCoworking(coworking2);
        place3.setTime(time1);
        Place place4 = new Place(4, 2, 15000, false);
        place4.setCoworking(coworking2);
        place4.setTime(time1);
        return args -> {
//            // adding user group
//            aisUserGroupRepository.save(clientGroup);
//            aisUserGroupRepository.save(coworkingEmployeeGroup);
//            aisUserGroupRepository.save(coworkingOwnerGroup);
//            aisUserGroupRepository.save(ownerGroup);
//            aisUserGroupRepository.save(adminGroup);
//            // adding users
//            aisUserRepository.save(aisUser1);
//            aisUserRepository.save(aisUser2);
//            aisUserRepository.save(aisUser3);
//            // adding coworkings
//            coworkingRepository.save(coworking1);
//            coworkingRepository.save(coworking2);
//            // adding promotion
//            promotionRepository.save(promotion1);
//            promotionRepository.save(promotion2);
//            // adding time
//            timeRepository.save(time1);
//            // adding places
//            placeRepository.save(place1);
//            placeRepository.save(place2);
//            placeRepository.save(place3);
//            placeRepository.save(place4);
        };
    }
}

