package Library;


public class User {
    private String Firstname;
    private String MiddleInitial;
    private String Lastname;
    private String Username;
    private String Passwd;
    private String Email;
    private int PersonId;
    private int MaxBorrows;


    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getMiddleInitial() {
        return MiddleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        MiddleInitial = middleInitial;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPasswd() {
        return Passwd;
    }

    public void setPasswd(String passwd) {
        Passwd = passwd;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getMaxBorrows() {
        return MaxBorrows;
    }

    public void setMaxBorrows(int maxBorrows) {
        MaxBorrows = maxBorrows;
    }

    public int getPersonId() {
        return PersonId;
    }

    public void setPersonId(int personId) {
        PersonId = personId;
    }

    protected void updateDatabase(){
        Database.updateUsers(this.getFirstname(), this.getLastname(), this.getMaxBorrows(), this.getPersonId());
    }

    public String toString(){
        String name = this.getFirstname()+this.getPersonId();
        return name;
    }
}
