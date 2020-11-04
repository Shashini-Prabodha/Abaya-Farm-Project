package lk.abayafarm.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.abayafarm.pos.bo.BoFactory;
import lk.abayafarm.pos.bo.custom.*;
import lk.abayafarm.pos.db.DBConnection;
import lk.abayafarm.pos.dto.*;
import lk.abayafarm.pos.util.Util;
import lk.abayafarm.pos.util.impl.UtilImpl;
import lk.abayafarm.pos.view.TM.CasheirTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import org.controlsfx.control.textfield.TextFields;
import tray.notification.NotificationType;

import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Pattern;

public class CashierFormController {

    public JFXButton btnCustomerAdd, btnPlaceOrder, btnAddtoCart, btnStockView, btnDailyReport, btnHome, btnAdd;
    public TextField txtQty;
    public Label lblStock, lblDate, lblTot, lblOrderId, lblUPrice, lblCustID, lblTime;
    public JFXComboBox cmbItemID;
    public TableView<CasheirTM> tblOrderDetail;
    public TableColumn colItemId, colItemName, colQty, colDelete, colUnitPrice;
    public JFXTextField txtCustomerName, txtCustomerTP, txtItemDescription;
    public ImageView imgLogo;
    public static double fullPayment;
    public JFXButton btnUpdate;
    public AnchorPane parentPane;
    public JFXButton btnLogout;
    private ArrayList<Integer> customerTPArr;
    private ArrayList<OrderDetailsDTO> getAllTblData;
    //    private ArrayList<OrderDetailsDTO> getAllTblData;
    private ArrayList<StoreDTO> getAllItemData;

    StoreBO storeBO = BoFactory.getInstance().getBo(BoFactory.BoType.STORE);
    CustomerBO customerBO = BoFactory.getInstance().getBo(BoFactory.BoType.CUSTOMER);
    OrderBO orderBO = BoFactory.getInstance().getBo(BoFactory.BoType.ORDER);
    PlaceOrderBO placeOrderBO = BoFactory.getInstance().getBo(BoFactory.BoType.PLACEORDER);
    IncomeBO incomeBO = BoFactory.getInstance().getBo(BoFactory.BoType.INCOME);
    Util util = new UtilImpl();

    public void initialize() {
        try {
            util.rotateAnimation(imgLogo);
            util.setTime(lblTime);
            loadStoreID();
            lblDate.setText(String.valueOf(LocalDate.now()));
            genarateOrderID();

            loadArryaList();
            autoSearching();

            colItemId.setCellValueFactory(new PropertyValueFactory<>("storeID"));
            colItemName.setCellValueFactory(new PropertyValueFactory<>("type"));
            colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
            colQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnStore"));
            colDelete.setCellValueFactory(new PropertyValueFactory<>("btn"));

            tblOrderDetail.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                setData(newValue);
            });
            util.setFadeTransition(parentPane, 300);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void getAll() {
        try {
            ArrayList<StoreDTO> allStore = storeBO.getAllStore();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //set date to click table
    private void setData(CasheirTM tm) {
        try {
            txtQty.setText(String.valueOf(tm.getQtyOnStore()));
            txtItemDescription.setText(tm.getType());
            cmbItemID.setValue(tm.getStoreID());
        } catch (NullPointerException e) {

        }
    }

    //load customer TP to cmb
    private void loadArryaList() {

        try {
            customerTPArr = new ArrayList<>();
            ArrayList<CustomerDTO> allCustomer = customerBO.getAllCustomer();
            for (CustomerDTO dto : allCustomer) {
                customerTPArr.add(dto.getCustTp());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ObservableList<CasheirTM> list = FXCollections.observableArrayList();


    //Add item to cart
    public void btnAddtoCartOnAtioon(ActionEvent actionEvent) {

        try {
            if (Pattern.compile("^[0-9]{1,}$").matcher(txtQty.getText()).matches()) {
                if (!txtCustomerTP.getText().isEmpty()) {

                    int qtyOnStore = storeBO.getStore(String.valueOf(cmbItemID.getValue())).getQtyOnStore();

                    if (Integer.parseInt(txtQty.getText()) <= qtyOnStore) {
                        JFXButton btn = new JFXButton("Remove");
                        CasheirTM tm = new CasheirTM((String) (cmbItemID.getValue()), txtItemDescription.getText(), Integer.parseInt(txtQty.getText()), Double.parseDouble(lblUPrice.getText()), btn);

                        if (list.isEmpty()) {
                            list.add(tm);
                            btnOnAction(btn);
                            tblOrderDetail.setItems(list);
                            loadArryaList();
                            getItemTotal();
                            tblOrderDetail.refresh();
                            return;

                        } else {
                            for (int i = 0; i < tblOrderDetail.getItems().size(); i++) {
                                String cmb = (String) cmbItemID.getValue();

                                if (list.get(i).getStoreID().equals(cmb)) {
                                    int store = list.get(i).getQtyOnStore();
                                    if ((store + Integer.parseInt(txtQty.getText())) <= store) {
                                        list.get(i).setQtyOnStore(list.get(i).getQtyOnStore() + Integer.parseInt(txtQty.getText()));
                                        getItemTotal();
                                        tblOrderDetail.refresh();
                                    } else {
                                        util.notification("", "Out Of Stock...!");
                                        txtQty.requestFocus();
                                    }
                                    return;
                                }
                            }
                        }
                        list.add(tm);
                        btnOnAction(btn);

                        tblOrderDetail.setItems(list);
                        loadArryaList();
                        getItemTotal();
                        tblOrderDetail.refresh();
                    } else {
                        util.notification("", "Out Of Stock...!");
                    }
                } else {
                    util.focus(txtCustomerTP);
                }
            } else {
                txtQty.requestFocus();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void btnOnAction(JFXButton btn) {
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
                    int index = tblOrderDetail.getSelectionModel().getFocusedIndex();
                    list.remove(index);
                    tblOrderDetail.refresh();
                } else {
                    //no
                }

            } catch (Exception e1) {
                e1.printStackTrace();
            }

        });
    }

    //Add Customer
    public void btnCustomerAddOnAction(ActionEvent actionEvent) {
        try {
            if (Pattern.compile("^\\d{10}$").matcher(txtCustomerTP.getText()).matches()) {
                if (Pattern.compile("^[A-z ]{1,}$").matcher(txtCustomerName.getText()).matches()) {

                    util.genarateCustomerID(lblCustID);

                    boolean b = customerBO.saveCustomer(new CustomerDTO(lblCustID.getText(), txtCustomerName.getText(), Integer.parseInt(txtCustomerTP.getText())));
                    if (b) {
                        util.trayNotification("Add New Customer", "Added New Customer .....! ", NotificationType.SUCCESS);
                        loadArryaList();
                    } else {
                        util.trayNotification("Add New Customer", "Added Failed .....! ", NotificationType.ERROR);
                    }
                } else {
                    util.focus(txtCustomerName);
                }
            } else {
                util.focus(txtCustomerTP);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Store Id on Action
    public void cmbItemIDOnAction(ActionEvent actionEvent) {
        String storeId = (String) cmbItemID.getValue();
        try {
            StoreDTO store = storeBO.getStore(storeId);
            txtItemDescription.setText(store.getType());
            lblStock.setText(String.valueOf(store.getQtyOnStore()));
            lblUPrice.setText(String.valueOf(store.getUnitPrice()));
            txtQty.requestFocus();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //auto searching customer tp
    public void autoSearching() {

        TextFields.bindAutoCompletion(txtCustomerTP, customerTPArr);
    }

    //load All Store id
    private void loadStoreID() {
        try {
            List<StoreDTO> list = storeBO.getAllStore();
            for (StoreDTO s : list) {
                cmbItemID.getItems().add(s.getStoreId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //btn Place Order-transaction
    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
        try {
            if (!cmbItemID.getSelectionModel().isEmpty()) {
                if (tblOrderDetail.getItems().size() > 0) {

                    OrderDTO orderDTO = new OrderDTO(lblCustID.getText(), lblOrderId.getText(), Date.valueOf(lblDate.getText()));
                    IncomeDTO incomeDTO = new IncomeDTO(lblOrderId.getText(), Date.valueOf(lblDate.getText()), Double.parseDouble(lblTot.getText()));
                    boolean b = placeOrderBO.placeOrder(orderDTO, getAllTblOrderDetailData(), getAllTblItemData(), incomeDTO);
                    if (b) {
                        util.trayNotification("Order", "Order Successfull.....! ", NotificationType.SUCCESS);
                        genarateOrderID();
                        genarateBill();
                        list.clear();
                        tblOrderDetail.refresh();
                        reset();
                    } else {
                        util.trayNotification("Order", "Order Failed.....! ", NotificationType.INFORMATION);
                    }
                } else {
                    util.notification("", "Please add items...!");
                }
            } else {
                cmbItemID.setFocusColor(Paint.valueOf("red"));
                cmbItemID.requestFocus();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reset() {
        try {
            txtCustomerTP.clear();
            txtCustomerName.clear();
            txtItemDescription.clear();
        } catch (NullPointerException e) {

        }

    }

    public void genarateBill() {
        try {
            InputStream is = this.getClass().getResourceAsStream("/lk/abayafarm/pos/report/CustomerBill/CustBillT.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(is);

            HashMap hm = new HashMap();
            hm.put("Invoice no", lblOrderId.getText());
            hm.put("noofItem", tblOrderDetail.getItems().size()+"");
            hm.put("total", lblTot.getText());

            JasperPrint jp = JasperFillManager.fillReport(jr, hm, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jp, false);

        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void genarateStoreReport() {
        try {
            InputStream is = this.getClass().getResourceAsStream("/lk/abayafarm/pos/report/Cashier Report/Store.jrxml");
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

    public void genarateDailyIncomeReport() {
        try {
            InputStream is = this.getClass().getResourceAsStream("/lk/abayafarm/pos/report/Cashier Report/DailyIncome.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(is);

            HashMap hm = new HashMap();
            try {
                hm.put("total", incomeBO.getTodayIncome() + "");
            } catch (Exception e) {
                e.printStackTrace();
            }

            JasperPrint jp = JasperFillManager.fillReport(jr, hm, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jp, false);

        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //get All orderDetail
    private ArrayList<OrderDetailsDTO> getAllTblOrderDetailData() {
        getAllTblData = new ArrayList<>();
        int dataFilledrowCount = colItemId.getTableView().getItems().size();

        for (int i = 0; i < dataFilledrowCount; i++) {

            String orderID = lblOrderId.getText();
            String itemcode = (String) colItemId.getCellData(i);
            System.out.println(colQty.getCellData(i));
            int qty = (int) colQty.getCellData(i);

            OrderDetailsDTO ord = new OrderDetailsDTO(orderID, itemcode, qty);

            getAllTblData.add(ord);

        }
        return getAllTblData;
    }

    //get All item details
    private ArrayList<StoreDTO> getAllTblItemData() {
        try {

            getAllItemData = new ArrayList<>();
            int dataFilledrowCount = colItemId.getTableView().getItems().size();

            for (int i = 0; i < dataFilledrowCount; i++) {

                String itemcode = (String) colItemId.getCellData(i);
                String name = (String) colItemName.getCellData(i);
                int orderQty = (int) colQty.getCellData(i);

                int onStock = storeBO.getStore(itemcode).getQtyOnStore();

                double unitPrice = (double) colUnitPrice.getCellData(i);
                StoreDTO storeDTO = new StoreDTO(itemcode, name, (onStock - orderQty), unitPrice);

                getAllItemData.add(storeDTO);
            }

        } catch (Exception e) {
        }
        return getAllItemData;

    }

    //btn View Stock
    public void btnStockViewController(ActionEvent actionEvent) {
        genarateStoreReport();
    }

    //View Daily Report
    public void btnDailyReportOnAction(ActionEvent actionEvent) {
        genarateDailyIncomeReport();
    }

    //btn Home on Action
    public void btnHomeOnAction(ActionEvent actionEvent) throws Exception {
        util.loadUI("Login", btnHome);
    }

    //get Total
    private double getItemTotal() {
        double total = 0;
        int qty = Integer.parseInt(txtQty.getText());
        double uPrice = Double.parseDouble(lblUPrice.getText());
        total = qty * uPrice;
        fullPayment += total;
        lblTot.setText(fullPayment + "");
        return total;
    }

    //Customer Tp on action
    public void txtCustomerTPOnAction(KeyEvent keyEvent) {

        try {
            int tp = Integer.parseInt(txtCustomerTP.getText());
            CustomerDTO customer = customerBO.getCustomerInTP(tp);
            txtCustomerName.setText(customer.getCustName());
            lblCustID.setText(customer.getCustId());
            if (txtCustomerName.getText().isEmpty()) {
                txtCustomerName.setText(null);
                util.genarateCustomerID(lblCustID);
                new Alert(Alert.AlertType.INFORMATION, "Customer TP not found...!/nPlease save this customer...!").show();
            }
        } catch (NullPointerException e) {
        } catch (NumberFormatException e) {
            txtCustomerName.clear();
            lblCustID.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Customer Update
    public void btnUpdateOnAction(ActionEvent actionEvent) {
        if (Pattern.compile("^\\d{10}$").matcher(txtCustomerTP.getText()).matches()) {
            if (Pattern.compile("^[A-z ]{1,}$").matcher(txtCustomerName.getText()).matches()) {
                try {
                    boolean b = customerBO.updateCustomer(new CustomerDTO(lblCustID.getText(), txtCustomerName.getText(), Integer.parseInt(txtCustomerTP.getText())));
                    if (b) {
                        util.trayNotification("Update Customer", "Update Customer .....! ", NotificationType.SUCCESS);
                        loadArryaList();
                    } else {
                        util.trayNotification("Update Customer", "Update Failed .....! ", NotificationType.ERROR);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                util.focus(txtCustomerName);
            }
        } else {
            util.focus(txtCustomerTP);
        }
    }

    public void txtQtyOnAction(ActionEvent actionEvent) {

    }

    public void genarateOrderID() {
        try {

            String ord = orderBO.getLastOrderID();
            if (ord != null) {
                String lastId = ord.split("[A-Z]")[1];

                if (Integer.parseInt(lastId) == 9) {
                    lblOrderId.setText("N010");
                    return;
                } else if (Integer.parseInt(lastId) < 9) {
                    String newID = "N00" + (Integer.parseInt(lastId) + 1);
                    lblOrderId.setText(newID);
                } else if (Integer.parseInt(lastId) == 100) {
                    lblOrderId.setText("N100");
                } else {
                    String newID = "N0" + (Integer.parseInt(lastId) + 1);
                    lblOrderId.setText(newID);
                }
            } else {
                lblOrderId.setText("N001");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnLogoutOnAction(ActionEvent actionEvent) {
        try {
            util.loadUI(btnLogout);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
