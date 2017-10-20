package Library;

public class Student extends User {
    Student(String firstname, String lastname, int maxBorrows, int pk){
        setFirstname(firstname);
        setLastname(lastname);
        setMaxBorrows(maxBorrows);
        setPersonId(pk);
    }
}
