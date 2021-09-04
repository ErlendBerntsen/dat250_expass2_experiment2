package no.hvl.dat250.jpa.basicexample;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue
    Long id;

    String street;
    Integer number;

    @ManyToMany(mappedBy = "addresses")
    Set<Person> peopleAtAddress = new HashSet<>();

    public Address(){}

    @Override
    public String toString(){
        return ("{id: " + id + ", street: " + street + ", number: " + number +
                ", peopleAtAddress: " + getPeopleAtAdressString());
    }

    private String getPeopleAtAdressString(){
        StringBuilder sb = new StringBuilder();
        if(peopleAtAddress.isEmpty())return "";
        for(Person p : peopleAtAddress){
            sb.append("{id: ");
            sb.append(p.id);
            sb.append(", name: ");
            sb.append(p.name);
            sb.append("}, ");
        }
        return sb.substring(0, sb.length()-2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street);
    }
}
