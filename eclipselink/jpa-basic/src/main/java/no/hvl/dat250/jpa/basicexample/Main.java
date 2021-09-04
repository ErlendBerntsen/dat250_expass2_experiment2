package no.hvl.dat250.jpa.basicexample;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Main {
    private static final String PERSISTENCE_UNIT_NAME = "banksystem";
    private static EntityManagerFactory factory;

    public static void main(String[] args) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        Pincode pincode = new Pincode();
        pincode.setPincode("123");
        pincode.setCount(1);
        em.persist(pincode);
        em.getTransaction().commit();

        em.getTransaction().begin();
        Bank bank = new Bank();
        bank.setName("Pengebank");
        em.persist(bank);
        em.getTransaction().commit();

        em.getTransaction().begin();
        CreditCard creditCard1 = new CreditCard();
        creditCard1.setNumber(12345);
        creditCard1.setBalance(-5000);
        creditCard1.setLimit(-10000);
        creditCard1.setPincode(pincode);
        creditCard1.addBank(bank);
        em.persist(creditCard1);
        em.getTransaction().commit();

        em.getTransaction().begin();
        CreditCard creditCard2 = new CreditCard();
        creditCard2.setNumber(123);
        creditCard2.setBalance(1);
        creditCard2.setLimit(2000);
        creditCard2.setPincode(pincode);
        creditCard2.addBank(bank);
        em.persist(creditCard2);
        em.getTransaction().commit();

        Address address = new Address();
        address.setStreet("Inndalsveien");
        address.setNumber(28);

        em.getTransaction().begin();
        Person person = new Person();
        person.setName("Max Mustermann");
        person.addAddress(address);
        person.setCreditCards(Arrays.asList(creditCard1, creditCard2));
        em.persist(person);
        em.getTransaction().commit();

        Query q = em.createQuery("select p from Person p");
        List<Person> personList = q.getResultList();
        for (Person p : personList) {
            System.out.println(p);
        }

        em.close();
    }
}
