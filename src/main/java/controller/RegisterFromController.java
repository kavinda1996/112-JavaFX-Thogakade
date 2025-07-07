package controller;

import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import model.User;
import org.jasypt.util.text.BasicTextEncryptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterFromController {

    @FXML
    private JFXTextField txtCPassword;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    void btnRegisterOnAction(ActionEvent event) throws SQLException {

        String key = "#5541Asd";

        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();

        basicTextEncryptor.setPassword(key);


        String SQL = "INSERT INTO users (username,email,password) VALUES(?,?,?)";

        if (txtPassword.getText().equals(txtCPassword.getText())){

            Connection connection = DBConnection.getInstance().getConnection();

            ResultSet resultSet = connection
                    .createStatement()
                    .executeQuery("SELECT * FROM users WHERE email=" + "'" + txtEmail.getText() + "'");

            if (!resultSet.next()){
                User user = new User(
                        txtUserName.getText(),
                        txtEmail.getText(),
                        txtPassword.getText()
                );

                PreparedStatement psTm = connection.prepareStatement(SQL);
                psTm.setString(1,user.getUserName());
                psTm.setString(2,user.getEmail());
                psTm.setString(3,basicTextEncryptor.encrypt(user.getPassword()));
                psTm.executeUpdate();

            }else{
                new Alert(Alert.AlertType.ERROR,"user found!").show();
            }
        }else{
            new Alert(Alert.AlertType.ERROR,"Check your password!!").show();
        }
    }

}
