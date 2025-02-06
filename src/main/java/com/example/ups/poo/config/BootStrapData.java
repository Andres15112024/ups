package com.example.ups.poo.config;

import com.example.ups.poo.repository.PersonRepository;
import com.example.ups.poo.entity.Person;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final PersonRepository personRepository;

    public BootStrapData(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Person p1 = new Person();
        p1.setName("Andres");
        p1.setLastname("Peralta");
        p1.setAge(20);
        p1.setPersonId("0931260905");

        Person p2 = new Person();
        p2.setName("Wilson");
        p2.setLastname("Peralta");
        p2.setAge(65);
        p2.setPersonId("1801541937");

        personRepository.save(p1);
        personRepository.save(p2);

        System.out.println("--- Started BootStrapData ---");
        System.out.println("Number of Person added: " + personRepository.count());
    }
}
