package Library;

/**
 * Created by techc on 9/30/2017.
 */
public class Books {
    private String bookTitle;
    private String authorFirstName;
    private String authorLastName;
    private int BookID;
    private boolean Available;

    Books(String title, String fname, String lname, int bookID, boolean available){
        this.setBookTitle(title);
        this.setAuthorFirstName(fname);
        this.setAuthorLastName(lname);
        this.setBookID(bookID);
        this.setAvailable(available);
    }


    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public int getBookID() {
        return BookID;
    }

    public void setBookID(int bookID) {
        BookID = bookID;
    }

    public String toString(){
        return this.getBookTitle() + " " + this.getBookID();
    }

    public boolean isAvailable() {
        return Available;
    }

    public void setAvailable(boolean available) {
        Available = available;
    }
}
