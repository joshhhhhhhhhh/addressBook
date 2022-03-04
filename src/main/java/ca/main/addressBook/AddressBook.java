package ca.main.addressBook;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class AddressBook {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<BuddyInfo> buddyInfoList;

    public AddressBook(){
        this.buddyInfoList = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAddressBook(List<BuddyInfo> buddyInfoList){
        this.buddyInfoList = buddyInfoList;
    }

    public void addBuddyInfo(BuddyInfo buddyInfo){
        this.buddyInfoList.add(buddyInfo);
    }

    public void addBuddyInfo(String name, String phoneNumber){
        this.buddyInfoList.add(new BuddyInfo(name, phoneNumber));
    }

    public boolean removeBuddyById(Long id){
        List buddies = this.buddyInfoList.stream().filter(buddyInfo -> buddyInfo.getId() == id).collect(Collectors.toList());
        if(buddies.size() != 1){
            return false;
        } else {
            this.buddyInfoList.remove(buddies.get(0));
            return true;
        }

    }

    public List<BuddyInfo> getAddressBook(){
        return buddyInfoList;
    }


    @Override
    public String toString() {
        String output = "";
        for (BuddyInfo buddy : buddyInfoList) {
            output += String.format("Name: %s, Phone Number: %s\n", buddy.getName(), buddy.getPhoneNumber());
        }
        return output;
    }
}
