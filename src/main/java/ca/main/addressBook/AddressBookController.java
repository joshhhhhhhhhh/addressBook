package ca.main.addressBook;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;


@RestController
public class AddressBookController {

    @Autowired
    AddressBookRepository repo;

    public AddressBookController(AddressBookRepository repo){
        this.repo = repo;
    }
/*
    @PostMapping("/addressBook")
    public AddressBook createAddressBook(){
        return repo.save(new AddressBook());
    }

    @PostMapping("/addressBook/{id}/buddy")
    public AddressBook createBuddy(@PathVariable Long id ,@RequestBody BuddyInfo buddy){
        AddressBook addressBook = repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "AddressBook ID not found."));
        addressBook.addBuddyInfo(buddy);
        return repo.save(addressBook);
    }
*/
    @DeleteMapping("/addressBook/{bookId}/buddy/{buddyId}")
    public AddressBook deleteBuddy(@PathVariable Long bookId, @PathVariable Long buddyId){
        AddressBook addressBook = repo.findById(bookId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "AddressBook ID not found."));
        if(!addressBook.removeBuddyById(buddyId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Buddy ID not found");
        }
        return repo.save(addressBook);
    }

}
