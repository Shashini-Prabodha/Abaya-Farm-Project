package lk.abayafarm.pos.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.chart.PieChart;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import lk.abayafarm.pos.bo.BoFactory;
import lk.abayafarm.pos.bo.custom.StoreBO;
import lk.abayafarm.pos.db.DBConnection;
import lk.abayafarm.pos.dto.StoreDTO;
import lk.abayafarm.pos.util.Util;
import lk.abayafarm.pos.util.impl.UtilImpl;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class AllReportForm {
    public AnchorPane parentPane;
    public ImageView imgAdmin;
    public JFXButton btnEggReportDaily, btnEggReportAll, btnbatchReport, btnCageReport, btnStockReport, btnFeedReport, btnSupReport, btnSupOrdReport, btnIncomeDaily, btnPaymentDaily, btnFeedUsage, btnMedicineUsage, btnIncomeAll, btnPaymentAll, btnHome,btnCustomerWIncome,btnMostMovable;
    public BorderPane piechartPane;


    Util util = new UtilImpl();
    StoreBO storeBO = BoFactory.getInstance().getBo(BoFactory.BoType.STORE);


    public void initialize() {
        setPieChart();
    }

    public void btnEggReportDailyOnAction(ActionEvent actionEvent) {
        try {
            InputStream is = this.getClass().getResourceAsStream("/lk/abayafarm/pos/report/AdminReport/EggReport.jrxml");
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

    public void setPieChart() {
        try {
            PieChart pc = new PieChart();
            pc.setTitle("Item Store");
            ArrayList<StoreDTO> allStore = storeBO.getAllStore();

            ObservableList<PieChart.Data> pieChartData =
                    FXCollections.observableArrayList(
                            new PieChart.Data("Egg-S",storeBO.getStore("S1").getQtyOnStore()),
                            new PieChart.Data("Egg-M", storeBO.getStore("S2").getQtyOnStore()),
                            new PieChart.Data("Egg-L", storeBO.getStore("S3").getQtyOnStore()),
                            new PieChart.Data("Manure-5kg", storeBO.getStore("S4").getQtyOnStore()),
                            new PieChart.Data("Manure-10kg", storeBO.getStore("S5").getQtyOnStore()),
                            new PieChart.Data("Manure-25kg", storeBO.getStore("S6").getQtyOnStore()),
                            new PieChart.Data("Manure-50kg", storeBO.getStore("S7").getQtyOnStore())
                    );
            pc.setData(pieChartData);
            piechartPane.setCenter(pc);

            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), pc);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnEggReportAllOnAction(ActionEvent actionEvent) {
        try {
            InputStream is = this.getClass().getResourceAsStream("/lk/abayafarm/pos/report/AdminReport/AllEggReport.jrxml");
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

    public void btnbatchReportOnAction(ActionEvent actionEvent) {
        try {
            InputStream is = this.getClass().getResourceAsStream("/lk/abayafarm/pos/report/AdminReport/BatchReport.jrxml");
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

    public void btnCageReportOnAction(ActionEvent actionEvent) {
        try {
            InputStream is = this.getClass().getResourceAsStream("/lk/abayafarm/pos/report/AdminReport/CageReport.jrxml");
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

    public void btnStockReportOnAction(ActionEvent actionEvent) {
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

    public void btnFeedReportOnAction(ActionEvent actionEvent) {
        try {
            InputStream is = this.getClass().getResourceAsStream("/lk/abayafarm/pos/report/AdminReport/FeedReport.jrxml");
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

    public void btnSupReportOnAction(ActionEvent actionEvent) {
        try {
            InputStream is = this.getClass().getResourceAsStream("/lk/abayafarm/pos/report/AdminReport/SupplierReport.jrxml");
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

    public void btnSupOrdReportOnAction(ActionEvent actionEvent) {
        try {
            InputStream is = this.getClass().getResourceAsStream("/lk/abayafarm/pos/report/AdminReport/SupplierOrderReport.jrxml");
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

    public void btnIncomeDailyOnAction(ActionEvent actionEvent) {
        try {
            InputStream is = this.getClass().getResourceAsStream("/lk/abayafarm/pos/report/Cashier Report/DailyIncome.jrxml");
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

    public void btnPaymentDailyOnAction(ActionEvent actionEvent) {
        try {
            InputStream is = this.getClass().getResourceAsStream("/lk/abayafarm/pos/report/AdminReport/DailyPayment.jrxml");
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

    public void btnCustomerWIncomeOnAction(ActionEvent actionEvent) {
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

    public void btnMostMovableOnAction(ActionEvent actionEvent) {
        try {
            InputStream is = this.getClass().getResourceAsStream("/lk/abayafarm/pos/report/AdminReport/MostMoveble.jrxml");
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

    public void btnFeedUsageOnAction(ActionEvent actionEvent) {
        try {
            InputStream is = this.getClass().getResourceAsStream("/lk/abayafarm/pos/report/AdminReport/FeedUsage.jrxml");
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

    public void btnMedicineUsageOnAction(ActionEvent actionEvent) {
        try {
            InputStream is = this.getClass().getResourceAsStream("/lk/abayafarm/pos/report/AdminReport/MedicineUsage.jrxml");
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

    public void btnIncomeAllOnAction(ActionEvent actionEvent) {
        try {
            InputStream is = this.getClass().getResourceAsStream("/lk/abayafarm/pos/report/AdminReport/All Income.jrxml");
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

    public void btnPaymentAllOnAction(ActionEvent actionEvent) {
        try {
            InputStream is = this.getClass().getResourceAsStream("/lk/abayafarm/pos/report/AdminReport/All Payment.jrxml");
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

    public void btnHomeOnAction(ActionEvent actionEvent) throws Exception {
        util.loadUI("AdminDashBoardForm", btnHome);
    }


}
