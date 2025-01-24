package com.example.ups.poo.service;

import com.example.ups.poo.dto.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    public List<Person> getAllPeople(){
        List<Person> personList = new ArrayList<>();
        Person p1 = new Person("Andres", "Peralta", 20, "0931260905");
        Person p2 = new Person("Wilson", "Peralta", 65, "1801541937");
        personList.add(p1);
        personList.add(p2);
        return personList;
    }
}
