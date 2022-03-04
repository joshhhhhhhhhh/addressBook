package ca.main.addressBook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AddressBookViewController {

    @Autowired
    private AddressBookRepository repo;

    @GetMapping("/addressBooks")
    public String showAddressBook(Model model) {
        List<AddressBook> addressBooks = new ArrayList<>();
        repo.findAll().forEach(addressBooks::add);
        model.addAttribute("addressBooks", addressBooks);
        model.addAttribute("buddy", new BuddyInfo());
        return "addressBook";
    }

    @PostMapping("/addressBooks")
    public String createAddressBook(Model model){
        AddressBook a = new AddressBook();
        repo.save(a);
        List<AddressBook> addressBooks = new ArrayList<>();
        repo.findAll().forEach(addressBooks::add);
        model.addAttribute("addressBooks", addressBooks);
        model.addAttribute("buddy", new BuddyInfo());
        return "addressBook";
    }

    @PostMapping("/addressBooks/{id}/buddy")
    public String createBuddy(@PathVariable Long id ,@ModelAttribute BuddyInfo buddy, Model model){
        AddressBook addressBook = repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "AddressBook ID not found."));
        addressBook.addBuddyInfo(buddy);
        repo.save(addressBook);
        return "redirect:/addressBooks";
    }

}
