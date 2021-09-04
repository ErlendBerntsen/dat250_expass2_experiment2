package no.hvl.dat250.jpa.basicexample;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class Bank {
    @Id
    @GeneratedValue
    Long id;

    String name;

    @OneToMany(mappedBy = "bank")
    Set<CreditCard> creditCards = new HashSet<>();


    public Bank(){}

    public void addCreditCard(CreditCard creditCard){
        creditCards.add(creditCard);
        creditCard.setBank(this);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString (){
        return ("{id: " + id +
                ", name: " + name +
                ", creditCards: " + creditCards.toString());
    }
}
