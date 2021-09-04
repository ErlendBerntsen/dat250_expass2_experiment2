package no.hvl.dat250.jpa.basicexample;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
public class CreditCard {
    @Id
    @GeneratedValue
    Long id;

    Integer number;
    Integer limit;
    Integer balance;

    @OneToOne
    Pincode pincode;

    @ManyToOne
    Bank bank;

    public CreditCard(){}



    public void addBank(Bank bank){
        setBank(bank);
        bank.getCreditCards().add(this);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString(){
        return("{id: " + id +
                ", number: " + number +
                ", limit: " + limit +
                ", balance: " + balance +
                ", pincode: " + pincode +
                ", bank: " + getBankString());
    }

    private String getBankString() {
        if (bank == null) return "null";
        return ("{id: " + bank.id +
                ", name: " + bank.name + "}"
        );
    }
}
