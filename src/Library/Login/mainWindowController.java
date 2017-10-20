package Library.Login;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by techc on 10/7/2017.
 */
public class mainWindowController implements Initializable{
    public Button usersButton;
    public Button addUsersButton;
    public Button addBooksButton;
    public Button manageBooksButton;
    public Button checkoutBooksButton;
    public ImageView userPictureImageView;
    private Image image;
    public Label welcomeLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        image = new Image("Library/Login/user.png");
        userPictureImageView.setImage(image);
        welcomeLabel.setText("Welcome, \n Admin");
    }

    // Switch to User Window
    @FXML
    public void openUserWindow(){
        System.out.println("Switching to User window");
    }

    // Switch to Add User Window
    @FXML
    public void openAddUsersWindow(){
        System.out.println("Switching to Add User window");
    }

    // Switch to Add Books Window
    @FXML
    public void openAddBooksWindow(){
        System.out.println("Switching to Add Books window");
    }

    // Switch to Manage Books Button
    @FXML
    public void openManageBooksWindow(){
        System.out.println("Switching to Manage Books window");
    }
    // Switch to Checkout Books Window
    @FXML
    public void openCheckoutBooksWindow(){
        System.out.println("Switching to Checkout book window");
    }



}
