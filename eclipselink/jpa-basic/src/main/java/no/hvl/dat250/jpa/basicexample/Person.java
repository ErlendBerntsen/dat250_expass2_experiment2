package no.hvl.dat250.jpa.basicexample;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class Person {
    @Id
    @GeneratedValue
    Long id;

    String name;

     @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
     @JoinTable(
             name = "PERSON_ADDRESS",
             joinColumns = @JoinColumn(name = "ADDRESSES_ID"),
             inverseJoinColumns = @JoinColumn(name = "PEOPLEATADDRESS_ID"))
     Set<Address> addresses = new HashSet<>();

     @OneToMany
     List<CreditCard> creditCards;

    public Person(){

    }

    public void addAddress(Address address){
        addresses.add(address);
        address.getPeopleAtAddress().add(this);
    }

    public void removeAddress(Address address){
        addresses.remove(address);
        address.getPeopleAtAddress().remove(this);
    }

    @Override
    public String toString(){
        return ("{id: " + id +
                ", name: " + name +
                ", addresses: " + addresses.toString() +
                ", creditCards: " + creditCards.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
