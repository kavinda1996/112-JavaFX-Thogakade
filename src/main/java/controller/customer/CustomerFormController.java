package controller.customer;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import service.BoFactory;
import service.custom.CustomerBo;
import service.custom.impl.CustomerBoImpl;
import util.BoType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerFormController {

    @FXML
    private TableColumn colAddress;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colSalary;

    @FXML
    private TableView tblCustomers;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSalary;


    CustomerBo customerBo = BoFactory.getInstance().getBoType(BoType.CUSTOMER);

    @FXML
    void btnAddOnAction(ActionEvent event) {

        boolean isCustomerAdd = customerBo.addCustomer(
                new Customer(
                        txtId.getText(),
                        txtName.getText(),
                        txtAddress.getText(),
                        Double.parseDouble(txtSalary.getText())
                )
        );

        if (isCustomerAdd) {
            new Alert(Alert.AlertType.INFORMATION, "Customer Added!!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Customer Not Added!!").show();

        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        loadTable();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
    }

    private void loadTable() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();
//        List<Customer> all = new CustomerController().getAll();
//        all.forEach(customer -> {
//            customerObservableList.add(customer);
//        });
//
//        tblCustomers.setItems(customerObservableList);
    }

    public void btnGetCustomerReportOnAction(ActionEvent actionEvent) {
        try {
            JasperDesign design = JRXmlLoader.load("src/main/resources/report/customer_report_111_112.jrxml");

            JRDesignQuery jrDesignQuery = new JRDesignQuery();
            jrDesignQuery.setText("SELECT * FROM customer WHERE id='C00097'");
            design.setQuery(jrDesignQuery);

            JasperReport jasperReport = JasperCompileManager.compileReport(design);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());

            JasperExportManager.exportReportToPdfFile(jasperPrint,"customer_report.pdf");

            JasperViewer.viewReport(jasperPrint,false);


        } catch (JRException | SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
