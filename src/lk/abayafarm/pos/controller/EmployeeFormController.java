package lk.abayafarm.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lk.abayafarm.pos.bo.BoFactory;
import lk.abayafarm.pos.bo.custom.EmployeeBO;
import lk.abayafarm.pos.dto.EmployeeDTO;
import lk.abayafarm.pos.dto.SupplierDTO;
import lk.abayafarm.pos.util.Util;
import lk.abayafarm.pos.util.impl.UtilImpl;
import lk.abayafarm.pos.view.TM.EmployeeTM;
import lk.abayafarm.pos.view.TM.SupplierTM;
import tray.notification.NotificationType;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class EmployeeFormController {
    public Pane logoPane;
    public ImageView imgAdmin;
    public JFXButton btnSave, btnUpdate, btnHome;
    public JFXDatePicker clndrDate;
    public TableView<EmployeeTM> tblEmployee;
    public TableColumn colEmployeeID, colName, colTP, colAddress, colSalary, colDelete;
    public JFXTextField txtEmployeeName, txtTP, txtAddress, txtxSalary;
    public Label lblEmployeeID;
    public AnchorPane parentPane;

    Util util = new UtilImpl();
    EmployeeBO employeeBO = BoFactory.getInstance().getBo(BoFactory.BoType.EMPLOYEE);

    public void initialize() {
        try {
            util.rotateAnimation(imgAdmin);
            util.genarateEmployeeID(lblEmployeeID);

            colEmployeeID.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
            colName.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
            colTP.setCellValueFactory(new PropertyValueFactory<>("employeeTP"));
            colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
            colDelete.setCellValueFactory(new PropertyValueFactory<>("btn"));

            loadAllEmployee();

            tblEmployee.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                setData(newValue);
            });
            util.setFadeTransition(parentPane, 300);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAllEmployee() {
        ObservableList<EmployeeTM> list = FXCollections.observableArrayList();
        try {
            List<EmployeeDTO> dtoList = employeeBO.getAllEmployee();
            for (EmployeeDTO dto : dtoList) {
                JFXButton btn = new JFXButton("Delete");
                EmployeeTM tm = new EmployeeTM(dto.getEmployeeId(), dto.getEmployeeName(), dto.getEmployeeTP(), dto.getAddress(), dto.getSalary(), btn);
                list.add(tm);

                btn.setStyle("-fx-background-color: #81ecec; ");
                btn.setOnAction((e) -> {
                    try {
                        ButtonType ok = new ButtonType("OK",
                                ButtonBar.ButtonData.OK_DONE);
                        ButtonType no = new ButtonType("NO",
                                ButtonBar.ButtonData.CANCEL_CLOSE);

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                                "Are You Sure ?", ok, no);
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.orElse(no) == ok) {
                            if (employeeBO.deleteEmployee(tm.getEmployeeId())) {
                                util.trayNotification("Delete Employee", "Employee Deleted .....! ", NotificationType.SUCCESS);
                                loadAllEmployee();
                                return;
                            } else {
                            }
                            util.trayNotification("Delete Employee", "Delete Failed .....! ", NotificationType.ERROR);

                        } else {

                        }

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                });
            }
            tblEmployee.setItems(list);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setData(EmployeeTM tm) {
        try {
            lblEmployeeID.setText(tm.getEmployeeId());
            txtEmployeeName.setText(tm.getEmployeeName());
            txtTP.setText(String.valueOf("0" + tm.getEmployeeTP()));
            txtAddress.setText(tm.getAddress());
            txtxSalary.setText(String.valueOf(tm.getSalary()));
        } catch (NullPointerException e) {

        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {

        if (Pattern.compile("^[A-z ]{1,}$").matcher(txtEmployeeName.getText()).matches()) {
            if (Pattern.compile("^\\d{10}$").matcher(txtTP.getText()).matches()) {
                if (Pattern.compile("^[A-z, |0-9:./]*\\b$").matcher(txtAddress.getText()).matches()) {
                    if (Pattern.compile("^([0-9.]{1,})$").matcher(txtxSalary.getText()).matches()) {

                        EmployeeDTO dto = new EmployeeDTO(lblEmployeeID.getText(), txtEmployeeName.getText(), Integer.parseInt(txtTP.getText()), txtAddress.getText(), Double.parseDouble(txtxSalary.getText()));
                        try {
                            if (employeeBO.saveEmployee(dto)) {
                                util.trayNotification("Save Employee", "Saved .....! ", NotificationType.SUCCESS);
                                loadAllEmployee();
                                util.genarateEmployeeID(lblEmployeeID);
                            } else {
                                util.trayNotification("Save Employee", "Failed .....! ", NotificationType.ERROR);

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        util.focus(txtxSalary);
                    }
                } else {
                    util.focus(txtAddress);
                }
            } else {
                util.focus(txtTP);
            }
        } else {
            util.focus(txtEmployeeName);
        }
    }

    public void btnHomeOnAction(ActionEvent actionEvent) {
        try {
            util.loadUI("AdminDashBoardForm", btnHome);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        if (Pattern.compile("^[A-z ]{1,}$").matcher(txtEmployeeName.getText()).matches()) {
            if (Pattern.compile("^\\d{10}$").matcher(txtTP.getText()).matches()) {
                if (Pattern.compile("^[A-z, |0-9:./]*\\b$").matcher(txtAddress.getText()).matches()) {
                    if (Pattern.compile("^([0-9.]{1,})$").matcher(txtxSalary.getText()).matches()) {

                        EmployeeDTO dto = new EmployeeDTO(lblEmployeeID.getText(), txtEmployeeName.getText(), Integer.parseInt(txtTP.getText()), txtAddress.getText(), Double.parseDouble(txtxSalary.getText()));
                        try {
                            if (employeeBO.updateEmployee(dto)) {
                                util.trayNotification("Update Employee", "Update Succsess .....! ", NotificationType.SUCCESS);
                                loadAllEmployee();
                                util.genarateEmployeeID(lblEmployeeID);

                            } else {
                                util.trayNotification("Update Employee", "Update Failed .....! ", NotificationType.ERROR);

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        util.focus(txtxSalary);
                    }
                } else {
                    util.focus(txtAddress);
                }
            } else {
                util.focus(txtTP);
            }
        } else {
            util.focus(txtEmployeeName);
        }
    }

    public void txtEmployeeNameOnAction(ActionEvent actionEvent) {
        util.focus(txtTP);
    }

    public void txtTPOnAction(ActionEvent actionEvent) {
        util.focus(txtAddress);
    }

    public void txtAddressOnAction(ActionEvent actionEvent) {
        util.focus(txtxSalary);
    }

    public void txtxSalaryOnAction(ActionEvent actionEvent) {
    }
}
