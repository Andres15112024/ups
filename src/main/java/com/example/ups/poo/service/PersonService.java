package com.example.ups.poo.service;

import com.example.ups.poo.dto.PersonDTO;
import com.example.ups.poo.entity.Person;
import com.example.ups.poo.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonDTO> fetchAllPeopleRecords() {
        Iterable<Person> personIterable = personRepository.findAll();
        List<PersonDTO> personDTOList = new ArrayList<>();

        for (Person per : personIterable) {
            PersonDTO personDTO = new PersonDTO();
            personDTO.setName(per.getName() + " " + per.getLastname());
            personDTO.setAge(per.getAge());
            personDTO.setId(per.getPersonId());
            personDTOList.add(personDTO);
        }
        return personDTOList;
    }

    public ResponseEntity getAllPeople(){
        List<PersonDTO> personDTOList = fetchAllPeopleRecords();

        if (personDTOList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person List is empty");
        }
        return ResponseEntity.status(HttpStatus.OK).body(personDTOList);
    }

    //method that finds and returns person by id
    public ResponseEntity getPersonById(String id){
        List<PersonDTO> personDTOList = fetchAllPeopleRecords();
        for(PersonDTO personDTO : personDTOList){
            if(id.equalsIgnoreCase(personDTO.getId())) {
                return ResponseEntity.status(HttpStatus.OK).body(personDTO);
            }
         }
         String message = "Person with id: " + id +" not found";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

//    public ResponseEntity getPersonById(String id) {
//        for (PersonDTO personDTO : personDTOList) {
//            if (id.equalsIgnoreCase(personDTO.getId())) {
//                return ResponseEntity.status(HttpStatus.OK).body(personDTO);
//            }
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person with id; " + id + " not found");
//    }
//
//    public ResponseEntity createPerson(PersonDTO personDTO) {
//        if (personDTO.getId() == null || personDTO.getId().isBlank()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: ID is required.");
//        }
//        if (personDTO.getName() == null || personDTO.getName().isBlank()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Name is required.");
//        }
//        if (personDTO.getLastname() == null || personDTO.getLastname().isBlank()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Last Name is required.");
//        }
//        if (personDTO.getAge() <= 0) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Age is required.");
//        }
//
//        for (PersonDTO existingPersonDTO : personDTOList) {
//            if (existingPersonDTO.getId().equalsIgnoreCase(personDTO.getId())) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Person with ID " + personDTO.getId() + " already exists.");
//            }
//        }
//        personDTOList.add(personDTO);
//        return ResponseEntity.status(HttpStatus.OK).body("Person successfully registered.");
//    }
//
//    public ResponseEntity updatePerson(PersonDTO personDTO) {
//        for (PersonDTO per : personDTOList) {
//            if (per.getId().equalsIgnoreCase(personDTO.getId())) {
//                if (personDTO.getName() != null) {
//                    per.setName(personDTO.getName());
//                }
//                if (personDTO.getLastname() != null) {
//                    per.setLastname(personDTO.getLastname());
//                }
//                if (personDTO.getAge() != 0) {
//                    per.setAge(personDTO.getAge());
//                }
//                return ResponseEntity.status(HttpStatus.OK)
//                        .body("Person with id: " + personDTO.getId() + " was successfully updated");
//            }
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                .body("Person with id: " + personDTO.getId() + " not found");
//    }
//
//    public ResponseEntity deletePersonById(String id) {
//        if(id != null && id.length() < 10){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body("id: " + id + " does not have the required length (10 chars min.)");
//        }
//        for (PersonDTO personDTO : personDTOList) {
//            if(id.equalsIgnoreCase(personDTO.getId())) {
//                personDTOList.remove(personDTO);
//                return ResponseEntity.status(HttpStatus.OK)
//                        .body("Person with id: " + id + " was successfully deleted");
//            }
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                .body("Person with id: " + id + " was not found");
//    }
}
