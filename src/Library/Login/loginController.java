package Library.Login;

//import Library.Login.Library;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import Library.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by techc on 9/30/2017.
 */
public class loginController implements Initializable{
    public Button loginButton;
    public TreeView<String> loginTree;
    public Label loginBookLabel;
    public TextField usernameField;
    public PasswordField passwordField;
    public AnchorPane mainWindow;
    public Stage stage;
    private Window window;
    private Scene scene1;

    @FXML
    public void login(ActionEvent event) throws IOException {
        if (usernameField.getText().equals("Admin") && passwordField.getText().equals("Admin")) {
            System.out.println("Logged in");
            scene1 = loginButton.getScene();
            window = scene1.getWindow();
            stage = (Stage)  window;

            Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
            stage.setTitle("Main Window");
            stage.setScene(new Scene(root));
            stage.show();

        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TreeItem<String> root = new TreeItem<>("Books");
        root.setExpanded(true);
        TreeItem<String> branch;
        ArrayList<Books> books = Database.getAllBooks();

        String title;
        for (Books book : books){
            title = book.getBookTitle();
            branch = new TreeItem<>(title);
            root.getChildren().add(branch);
        }
        loginTree.setRoot(root);
        loginTree.getSelectionModel().selectedItemProperty()
                .addListener((v, oldValue, newValue) -> {
            if (newValue != null){
                loginBookLabel.setText(newValue.getValue());
            }
        });
    }

}
