package no.hvl.dat250.jpa.basicexample;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Pincode {
    @Id
    @GeneratedValue
    Long id;

    String pincode;
    Integer count;

    public Pincode(){}
}
