package lk.abayafarm.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import lk.abayafarm.pos.bo.BoFactory;
import lk.abayafarm.pos.bo.custom.CustomerBO;
import lk.abayafarm.pos.db.DBConnection;
import lk.abayafarm.pos.dto.CustomerDTO;
import lk.abayafarm.pos.util.Util;
import lk.abayafarm.pos.util.impl.UtilImpl;
import lk.abayafarm.pos.view.TM.CustomerTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import tray.notification.NotificationType;

import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class CustomerFormController {
    public Pane logoPane;
    public ImageView imgAdmin;
    public JFXButton btnSave, btnHome, btnUpdate, btnCustomerwiseIncome;
    public TableView<CustomerTM> tblCustomer;
    public TableColumn colCustID, colName, colTP, colDelete;
    public JFXTextField txtCustomerName, txtCustomerTP;
    public JFXDatePicker clndrDate;
    public Label lblCustomerID;
    public AnchorPane parentPane;

    Util util = new UtilImpl();
    CustomerBO customerBO = BoFactory.getInstance().getBo(BoFactory.BoType.CUSTOMER);

    public void initialize() {
        try {
            clndrDate.setValue(LocalDate.now());
            util.genarateCustomerID(lblCustomerID);
            util.rotateAnimation(imgAdmin);
            txtCustomerName.requestFocus();

            colCustID.setCellValueFactory(new PropertyValueFactory<>("custId"));
            colName.setCellValueFactory(new PropertyValueFactory<>("custName"));
            colTP.setCellValueFactory(new PropertyValueFactory<>("custTp"));
            colDelete.setCellValueFactory(new PropertyValueFactory<>("btn"));

            loadAllCustomer();

            tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                setData(newValue);
            });
            util.setFadeTransition(parentPane, 300);


        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void setData(CustomerTM tm) {
        try {
            lblCustomerID.setText(tm.getCustId());
            txtCustomerName.setText(tm.getCustName());
            txtCustomerTP.setText("0" + tm.getCustTp());
        } catch (NullPointerException e) {
        }
    }

    private void loadAllCustomer() {
        ObservableList<CustomerTM> list = FXCollections.observableArrayList();
        try {
            List<CustomerDTO> dtoList = customerBO.getAllCustomer();

            for (CustomerDTO dto : dtoList) {
                JFXButton button = new JFXButton("Delete");
                CustomerTM tm = new CustomerTM(dto.getCustId(), dto.getCustName(), dto.getCustTp(), button);
                list.add(tm);

                button.setStyle("-fx-background-color: #81ecec; ");
                button.setOnAction((e) -> {
                    try {
                        ButtonType ok = new ButtonType("OK",
                                ButtonBar.ButtonData.OK_DONE);
                        ButtonType no = new ButtonType("NO",
                                ButtonBar.ButtonData.CANCEL_CLOSE);

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                                "Are You Sure ?", ok, no);
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.orElse(no) == ok) {
                            if (customerBO.deleteCustomer(tm.getCustId())) {
                                util.trayNotification("Delete Customer", "Customer Deleted .....! ", NotificationType.SUCCESS);
                                loadAllCustomer();
                                return;
                            } else {
                            }
                            util.trayNotification("Delete Customer", "Delete Failed .....! ", NotificationType.ERROR);

                        } else {

                        }

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                });

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        tblCustomer.setItems(list);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        try {
            if (Pattern.compile("^[A-z ]{1,}$").matcher(txtCustomerName.getText()).matches()) {
                if (Pattern.compile("^\\d{10}$").matcher(txtCustomerTP.getText()).matches()) {
                    CustomerDTO customerDTO = new CustomerDTO(lblCustomerID.getText(), txtCustomerName.getText(), Integer.parseInt(txtCustomerTP.getText()));

                    if (customerBO.saveCustomer(customerDTO)) {
                        util.trayNotification("Add New Customer", "Customer Added .....! ", NotificationType.SUCCESS);
                        util.genarateCustomerID(lblCustomerID);
                        loadAllCustomer();
                        clear();
                    } else {
                        util.trayNotification("Add New Customer", "Added Failed .....! ", NotificationType.ERROR);
                        util.loadUI("AlertForm");
                    }

                } else {
                    util.notification("", "Please enter Telephone no in Numbers...!");
                    util.focus(txtCustomerTP);
                }
            } else {
                util.notification("", "Please enter customer name...!");
                util.focus(txtCustomerName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clear() {
        txtCustomerTP.setText(null);
        txtCustomerName.setText(null);
        lblCustomerID.setText("");
    }


    public void btnHomeOnAction(ActionEvent actionEvent) {
        try {
            util.loadUI("AdminDashBoardForm", btnHome);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            if (Pattern.compile("^[A-z ]{1,}$").matcher(txtCustomerName.getText()).matches()) {
                if (Pattern.compile("^\\d{10}$").matcher(txtCustomerTP.getText()).matches()) {
                    ButtonType ok = new ButtonType("Yes");
                    ButtonType no = new ButtonType("No");
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure update customer ?", ok, no);
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.orElse(no) == ok) {
                        CustomerDTO customerDTO = new CustomerDTO(lblCustomerID.getText(), txtCustomerName.getText(), Integer.parseInt(txtCustomerTP.getText()));

                        if (customerBO.updateCustomer(customerDTO)) {
                            util.trayNotification("Update Customer", "Customer Updated .....! ", NotificationType.SUCCESS);
                            loadAllCustomer();
                            clear();
                        } else {
                            util.trayNotification("Update Customer", "Update Failed .....! ", NotificationType.ERROR);
                            util.loadUI("AlertForm");
                        }
                    }
                } else {
                    util.notification("", "Please enter Telephone no in Numbers...!");
                    util.focus(txtCustomerTP);
                }
            } else {
                util.notification("", "Please enter customer name...!");
                util.focus(txtCustomerName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnCustomerwiseIncomeOnAction(ActionEvent actionEvent) {
        try {
            InputStream is = this.getClass().getResourceAsStream("/lk/abayafarm/pos/report/AdminReport/CustomerWiseIncome.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(is);

            JasperPrint jp = JasperFillManager.fillReport(jr, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jp, false);

        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
