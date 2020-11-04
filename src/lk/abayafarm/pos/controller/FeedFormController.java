package lk.abayafarm.pos.controller;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import lk.abayafarm.pos.bo.BoFactory;
import lk.abayafarm.pos.bo.custom.*;
import lk.abayafarm.pos.dao.QueryDAO;
import lk.abayafarm.pos.dao.custom.impl.QueryDAOImpl;
import lk.abayafarm.pos.dto.CageDTO;
import lk.abayafarm.pos.dto.FeedDTO;
import lk.abayafarm.pos.dto.SupplierDTO;
import lk.abayafarm.pos.dto.SupplierOrderDTO;
import lk.abayafarm.pos.util.Regex;
import lk.abayafarm.pos.util.Util;
import lk.abayafarm.pos.util.impl.RegexImpl;
import lk.abayafarm.pos.util.impl.UtilImpl;
import lk.abayafarm.pos.view.TM.FeedTM;
import tray.notification.NotificationType;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class FeedFormController {
    public JFXDatePicker clndrDate;
    public JFXTextField txtQtyOnHand, txtSuplierId, txtUnitPrice, txtTotal;
    public ToggleGroup group;
    public JFXTextArea Description;
    public JFXButton btnAdd, btnUpdate, btnClear, btnClose;
    public TableView<FeedTM> tblFeedOrderDetail;
    public TableColumn colFeedID, colFeedType, colQtyOnHand, colPackSize;
    public JFXComboBox cmbSupplierName, cmbPackSize;
    public JFXRadioButton rbtnChick, rbtnGrower, rbtnLayer;
    public Pane logoPane;
    public Label lblSupOrderID;
    public JFXComboBox cmbCageID;
    public AnchorPane parentPane;

    Util util = new UtilImpl();
    CageBO cageBO = BoFactory.getInstance().getBo(BoFactory.BoType.CAGE);
    BatchBO batchBO = BoFactory.getInstance().getBo(BoFactory.BoType.BATCH);
    FeedBO feedBO = BoFactory.getInstance().getBo(BoFactory.BoType.FEED);
    SupplierBO supplierBO = BoFactory.getInstance().getBo(BoFactory.BoType.SUPPLIER);
    QueryDAO queryDAO = new QueryDAOImpl();
    SupplierItemOrderTransactionBo bo = BoFactory.getInstance().getBo(BoFactory.BoType.SUPPLIERITEMORDER);

    public void initialize() {
        try {
            genarateOrderDate();
            loadAllCageID();
            loadAllSupplierName();

            util.genarateSupOrderID(lblSupOrderID);
            colFeedID.setCellValueFactory(new PropertyValueFactory<>("feedId"));
            colFeedType.setCellValueFactory(new PropertyValueFactory<>("type"));
            colPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
            colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

            loadAllDataTable();
            util.setFadeTransition(parentPane, 300);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void loadAllDataTable() {

        try {
            ObservableList<FeedTM> tmlist = FXCollections.observableArrayList();
            List<FeedDTO> dtoArrayList = feedBO.getAllFeed();

            for (FeedDTO dto : dtoArrayList) {
                FeedTM tm = new FeedTM(dto.getFeedId(), dto.getQtyOnHand(), dto.getFeedType(), dto.getPackSize());
                tmlist.add(tm);
            }
            tblFeedOrderDetail.setItems(tmlist);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void genarateOrderDate() {

        clndrDate.setValue(LocalDate.now());
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        try {
            if (!cmbCageID.getSelectionModel().isEmpty()) {
                if (Pattern.compile("^([0-9.]{1,})$").matcher(txtUnitPrice.getText()).matches()) {
                    if (Pattern.compile("^([0-9]{1,})$").matcher(txtQtyOnHand.getText()).matches()) {
                        if (!cmbSupplierName.getSelectionModel().isEmpty()) {
                            if (!cmbPackSize.getSelectionModel().isEmpty()) {
                                LocalDate value = clndrDate.getValue();
                                Date date = Date.valueOf(value);
                                String cageId = (String) cmbCageID.getValue();
                                int packSize = Integer.parseInt((String) cmbPackSize.getValue());

                                CageDTO cageDTO = cageBO.getCage(cageId);
                                FeedDTO dto = feedBO.getFeed(getType(), packSize);
                                SupplierOrderDTO supplierOrderDTO = new SupplierOrderDTO(lblSupOrderID.getText(), txtSuplierId.getText(), cageDTO.getBatchId(), Description.getText(), Integer.parseInt(txtQtyOnHand.getText()), Double.parseDouble(txtUnitPrice.getText()), date);
                                FeedDTO feedDTO = new FeedDTO(dto.getFeedId(), (dto.getQtyOnHand() + Integer.parseInt(txtQtyOnHand.getText())), dto.getFeedType(), dto.getPackSize());

                                ButtonType ok = new ButtonType("Yes");
                                ButtonType no = new ButtonType("No");
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure get new feed ?", ok, no);
                                Optional<ButtonType> result = alert.showAndWait();

                                if (result.orElse(no) == ok) {

                                    if (bo.addBatch(supplierOrderDTO, feedDTO)) {
                                        util.trayNotification("Add New Feed", "Added New Feed .....! ", NotificationType.SUCCESS);
                                        loadAllDataTable();
                                        util.genarateSupOrderID(lblSupOrderID);
                                    } else {
                                        util.trayNotification("Add New Feed", "Feed Added Fail.....! ", NotificationType.ERROR);
                                    }
                                } else {
                                }
                            } else {
                                cmbPackSize.setFocusColor(Paint.valueOf("red"));
                                cmbPackSize.requestFocus();
                            }
                        } else {
                            cmbSupplierName.setFocusColor(Paint.valueOf("red"));
                            cmbSupplierName.requestFocus();
                        }
                    } else {
                        util.notification("Error", "Please use only Numbers");
                        util.focus(txtQtyOnHand);
                    }
                } else {
                    util.notification("Error", "Please use only Numbers");
                    util.focus(txtUnitPrice);
                }
            }
            {
                util.notification("", "Please choose cage...!");
                cmbCageID.setFocusColor(Paint.valueOf("red"));
                cmbCageID.requestFocus();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void loadAllCageID() {
        try {
            List<CageDTO> list = cageBO.getCageIDListIn();
            for (CageDTO cageDTO : list) {
                cmbCageID.getItems().add(cageDTO.getCageId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAllSupplierName() {
        try {
            List<SupplierDTO> list = supplierBO.getAllSupplierWithType("feed");
            for (SupplierDTO supplierDTO : list) {
                cmbSupplierName.getItems().add(supplierDTO.getSupplierName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void cmbSupplierNameOnAction(ActionEvent actionEvent) {
        try {
            String supName = (String) cmbSupplierName.getValue();
            SupplierDTO supplierDTO = supplierBO.getSupplierForName(supName);

            txtSuplierId.setText(supplierDTO.getSupplierId());
            Description.setText("Feed");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cmbCageIDOnAction(ActionEvent actionEvent) {
        try {
            String type = queryDAO.getType(String.valueOf(cmbCageID.getValue()));
            setRadioButton(type);
            txtUnitPrice.requestFocus();
            loadPackSize(type);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadPackSize(String s) {
        cmbPackSize.getItems().clear();
        switch (s) {
            case "chick":
                cmbPackSize.getItems().add("25");
                break;
            case "grower":
                cmbPackSize.getItems().add("50");
                break;
            case "layer":
                cmbPackSize.getItems().add("50");
                break;
            default:


        }
    }

    public void btnCloseOnAction(ActionEvent actionEvent) throws Exception {

        util.loadUI(btnClose);
    }

    //type wise button selected
    private void setRadioButton(String type) {
        switch (type) {
            case "chick":
                rbtnChick.setSelected(true);
                break;
            case "grower":
                rbtnGrower.setSelected(true);
                break;
            case "layer":
                rbtnLayer.setSelected(true);
                break;
            default:
        }
    }

    public String getType() {
        if (rbtnChick.isSelected()) {
            return "chick";
        } else if (rbtnGrower.isSelected()) {
            return "grower";
        } else {
            return "layer";
        }
    }

    public void qtyOnAction(KeyEvent actionEvent) throws Exception {
        try {
            if (Pattern.compile("^([0-9.]{1,})$").matcher(txtUnitPrice.getText()).matches()) {
                if (Pattern.compile("^([0-9]{1,})$").matcher(txtQtyOnHand.getText()).matches()) {
                    txtTotal.setText(String.valueOf(Integer.parseInt(txtQtyOnHand.getText()) * (Double.parseDouble(txtUnitPrice.getText()))));
                } else {
                    util.notification("Error", "Please use only Numbers");
                    util.focus(txtQtyOnHand);
                }
            } else {
                util.notification("Error", "Please use only Numbers");
                util.focus(txtUnitPrice);
            }


        } catch (NumberFormatException w) {
            util.notification("Error", "Please use only Numbers");
            util.focus(txtUnitPrice);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void typeOnActon(MouseEvent mouseEvent) {
    }

    //load packsize wise radio btn selected
    public void rbtnOnAction(MouseEvent mouseEvent) {
        cmbPackSize.getItems().clear();
        loadPackSize(getType());
    }
}


