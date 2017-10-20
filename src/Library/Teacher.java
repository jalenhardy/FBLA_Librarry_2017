package Library;


public class Teacher extends User {
    Teacher(String firstname, String lastname, int maxBorrows, int pk){
        setFirstname(firstname);
        setLastname(lastname);
        setMaxBorrows(maxBorrows);
        setPersonId(pk);
    }

}
