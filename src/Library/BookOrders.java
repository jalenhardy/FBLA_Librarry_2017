package Library;


public class BookOrders {
    private int orderID;
    private int userID;
    private int bookID;

    BookOrders(int OrderID, int UserID, int BookID){
        this.setOrderID(OrderID);
        this.setUserID(UserID);
        this.setBookID(BookID);

    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }
}
