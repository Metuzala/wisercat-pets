package com.metusala.wisercatpets.com.metusala.wisercatpets.controllers;

import com.metusala.wisercatpets.com.metusala.wisercatpets.enums.CountryOfOrigin;
import com.metusala.wisercatpets.com.metusala.wisercatpets.enums.PetFurColor;
import com.metusala.wisercatpets.com.metusala.wisercatpets.enums.PetType;
import com.metusala.wisercatpets.com.metusala.wisercatpets.repositories.PetRepository;
import com.metusala.wisercatpets.com.metusala.wisercatpets.repositories.UserRepository;
import com.metusala.wisercatpets.com.metusala.wisercatpets.models.Pet;

import com.metusala.wisercatpets.com.metusala.wisercatpets.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.util.Map;

@RestController
public class PetController {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private UserRepository userRepository;

    @Value( "${frontend.url}" )
    private String frontendUrl;

    @GetMapping("/api/pets")
    public ResponseEntity<Iterable<Pet>> getUserPets(Principal principal) {
        return ResponseEntity.ok(petRepository.findByUser(new User(principal.getName())));
    }

    @PostMapping("/api/pets")
    public ResponseEntity<Pet> addOrUpdatePet(@RequestBody Pet newPet, Principal principal) {
        // set pet user to current user
        newPet.setUser(new User(principal.getName()));
        return ResponseEntity.ok(petRepository.save(newPet));
    }

    @GetMapping("/api/pets/{id}")
    public ResponseEntity<Pet> getPet(@PathVariable long id) {
        if (!petRepository.existsById(id)) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(petRepository.findById(id).get());
    }

    @DeleteMapping("/api/pets/{id}")
    public void deletePet(@PathVariable long id) {
        petRepository.deleteById(id);
    }

    @GetMapping("/options")
    public Map<String, Map> getOptions() {
        return Map.of(
                "countries", CountryOfOrigin.getOptions(),
                "furColors", PetFurColor.getOptions(),
                "types", PetType.getOptions()
        );
    }

    @RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<Void> toFrontend() {
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(frontendUrl)).build();
    }
}
