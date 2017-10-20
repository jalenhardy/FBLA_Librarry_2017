package Library;

import sun.net.ConnectionResetException;

import javax.print.DocFlavor;
import java.sql.*;
import java.util.ArrayList;


public class Database {
    public static Connection connectDatabase() {
        // Connect to database
        String hostName = "onics.database.windows.net";
        String dbName = "onicsfbla";
        String user = "JalenOnics";
        String password = "@lphaLion33";
        String url = "jdbc:sqlserver://onics.database.windows.net:1433;database=onicsfbla;user=JalenOnics@onics;password=@lphaLion33;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }


    /**
     * Users
     **/
    // https://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html
    public static ArrayList<User> iterateUsersTable(String selectSql, Connection connection) {
        ArrayList<User> Users = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSql)) {

            while (resultSet.next()) {
                int pk = resultSet.getInt(1);
                String fname = resultSet.getString(2).trim();
                String lname = resultSet.getString(3).trim();
                String Discriminant = resultSet.getString(4).trim();
                int maxBorrow = resultSet.getInt(5);

                User temp;
                if (Discriminant == "Teacher") {
                    temp = new Teacher(fname, lname, maxBorrow, pk);
                } else {
                    temp = new Student(fname, lname, maxBorrow, pk);
                }
                Users.add(temp);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return Users;
    }

    public static ArrayList<User> getUsers(String fistname, String lastname) {
        ArrayList<User> Users = new ArrayList<>();

        try {
            Connection connection = connectDatabase();
            String schema = connection.getSchema();
            String selectSql = "SELECT t.* FROM onicsfbla.dbo.[Users] t "
                    + String.format("WHERE Firstname='%s' ", fistname)
                    + String.format("AND Lastname='%s'", lastname);

            Users = iterateUsersTable(selectSql, connection);
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Users;
    }

    public static ArrayList<User> getAllUsers() {
        ArrayList<User> Users = new ArrayList<>();

        try {
            Connection connection = connectDatabase();
            String schema = connection.getSchema();
            String selectSql = "SELECT t.* FROM onicsfbla.dbo.[Users] t";
            Users = iterateUsersTable(selectSql, connection);

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Users;
    }

    // https://www.mkyong.com/jdbc/jdbc-statement-example-insert-a-record/
    public static void addUser(String firstName, String lastname, String discriminant, int maxBorrows) {
        Connection connection = connectDatabase();
        try {
            String schema = connection.getSchema();
            String insertSQL = "INSERT INTO Users(Firstnmae, Lastname, Discriminant, MaxBorrows) "
                    + String.format("Values('%s', '%s', '%s', '%o')", firstName, lastname, discriminant, maxBorrows);
            Statement statement = connection.createStatement();
            statement.executeUpdate(insertSQL);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void updateUsers(String firstname, String lastname, int maxBorrows, int userID){
        try{
            Connection connection = connectDatabase();
            Statement statement = connection.createStatement();
            String updateSQL = "Update Users "
                    + String.format("SET Firstname='%s', Lastname='%s', MaxBorrows='%o'", firstname, lastname, maxBorrows)
                    + String.format("WHERE ID=%o", userID);
            statement.executeUpdate(updateSQL);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    /**
     * Books
     **/
    public static ArrayList<Books> iterateBookTable(String SqlStatement, Connection connection) {
        ArrayList<Books> books = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SqlStatement)) {


            while (resultSet.next()) {
                int pk = resultSet.getInt(1);
                String title = resultSet.getString(2).trim();
                String afname = resultSet.getString(3).trim();
                String alname = resultSet.getString(4).trim();
                Byte aval = resultSet.getByte(5);
                Boolean availbale = true;
                if (aval == 0){
                    availbale = false;
                }


                Books temp = new Books(title, afname, alname, pk, availbale);
                books.add(temp);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    public static ArrayList<Books> getAllBooks() {
        ArrayList<Books> books = new ArrayList<>();
        try {
            Connection connection = connectDatabase();
            String schema = connection.getSchema();
            String selectSql = "SELECT t.* FROM onicsfbla.dbo.[Books] t";


            books = iterateBookTable(selectSql, connection);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return books;
    }

    public static ArrayList<Books> getBooks(String title) {
        ArrayList<Books> books = new ArrayList<>();
        try {
            Connection connection = connectDatabase();
            String schema = connection.getSchema();
            String selectSql = "SELECT t.* FROM onicsfbla.dbo.[Books] t "
                    + String.format("WHERE BookTitle='%s'", title);
            books = iterateBookTable(selectSql, connection);
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    public static void addBooks(String title, String fname, String lname){
        try{
            Connection connection = connectDatabase();

            Statement statement = connection.createStatement();
            String insertSQLTable = "INSERT INTO Books(BookTitle, AFirstName, AlastName) "
                    + String.format("Values('%s', '%s', '%s')", title, fname, lname);
            statement.executeUpdate(insertSQLTable);
            connection.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void changeAvailibility(int bookID, byte aval){
        try{
            Connection connection = connectDatabase();
            Statement statement = connection.createStatement();
            String updateSQL = "UPDATE Books "
                    + String.format("set Available=%o", aval)
                    + String.format("WHERE BookID=%o", bookID);
            statement.executeUpdate(updateSQL);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void checkOutBook(int bookID, int userID){
        changeAvailibility(bookID, (byte)0);
        try{
            Connection connection = connectDatabase();
            Statement statement = connection.createStatement();
            String insertSQL = "INSERT INTO BookOrders(UserID, BookID) "
                    + String.format("Values(%o, %o)", userID, bookID);
            statement.executeUpdate(insertSQL);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * Book Orders
     */
    public static ArrayList<BookOrders> iterateBookOrderTable(String selectSql, Connection connection){
        ArrayList<BookOrders> orders = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSql)) {

            while (resultSet.next()) {
                int orderID = resultSet.getInt(1);
                int UserID = resultSet.getInt(2);
                int BookID = resultSet.getInt(3);

                BookOrders temp = new BookOrders(orderID, UserID, BookID);
                orders.add(temp);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }
    public static ArrayList<BookOrders> getAllBookOrders(){
        ArrayList<BookOrders> orders = new ArrayList<>();
        try {
            Connection connection = connectDatabase();
            String schema = connection.getSchema();
            String selectSql = "SELECT t.* FROM onicsfbla.dbo.[BookOrders] t";


            orders = iterateBookOrderTable(selectSql, connection);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }
    public static ArrayList<BookOrders> getBookOrders(int UserID){
        ArrayList<BookOrders> orders = new ArrayList<>();
        try {
            Connection connection = connectDatabase();
            String schema = connection.getSchema();
            String selectSql = "SELECT t.* FROM onicsfbla.dbo.[BookOrders] t "
                   + String.format("WHERE UserID=%o", UserID);


            orders = iterateBookOrderTable(selectSql, connection);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }
}


