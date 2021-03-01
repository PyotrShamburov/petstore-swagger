package by.tms.home.resource;

import by.tms.home.model.Pet;
import by.tms.home.model.enums.PetStatusEnum;
import by.tms.home.service.pet.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/pet")
public class PetResource {
    @Autowired
    private PetService petService;

    @PostMapping
    public ResponseEntity<Pet> addNewPet(@Valid @RequestBody Pet pet) {
        if (petService.addPetToStorage(pet)) {
            return new ResponseEntity<>(pet, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @PutMapping
    public ResponseEntity<String> updatePet(@Valid @RequestBody Pet newPet) {
        if (petService.updatePet(newPet)) {
            return new ResponseEntity<>("Pet with ID :"+newPet.getId()+" - UPDATED!",HttpStatus.OK);
        }
        return new ResponseEntity<>("Pet not found!",HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/findByStatus")
    public ResponseEntity<List<Pet>> findByStatus(String status) {
        List<Pet> byStatus = (List<Pet>) petService.getByStatus(status);
        if (byStatus.size()!=0) {
            return new ResponseEntity<>(byStatus, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/{petId}")
    public ResponseEntity<Pet> findById(@PathVariable long petId) {
        Pet byId = (Pet) petService.getById(petId);
        if (byId != null) {
            return new ResponseEntity<>(byId, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(path = "/{petId}")
    public ResponseEntity<String> deleteById(@PathVariable long petId) {
        if (petService.deleteById(petId)) {
            return new ResponseEntity<>("Pet with ID: "+petId+" - DELETED!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Pet not Found!", HttpStatus.BAD_REQUEST);
    }






}
