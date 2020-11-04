package lk.abayafarm.pos.controller;

import animatefx.animation.Bounce;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import lk.abayafarm.pos.bo.BoFactory;
import lk.abayafarm.pos.bo.custom.*;
import lk.abayafarm.pos.dto.StoreDTO;
import lk.abayafarm.pos.util.Util;
import lk.abayafarm.pos.util.impl.UtilImpl;

import java.util.List;

public class BussinessReportDashBoardViewController {
    public Label lblTodaySale, lblYestSale, lblQtyEgg, lblMonthSell,lblQtySEgg,lblEggMPrice,lblQtyMEgg,lblQtyLEgg,lblEggLPrice,lblChickQty,lblPulletQty,lblAdultQty,lblChickFeed;
    public BorderPane border;
    public LineChart<?, ?> linechart;
    public Circle cir1,cir2,cir3,cir4;
    public Label lblEggSPrice,lblGrowerFeed,lblLayerFeed;
    public JFXButton btnDashBoard,btnLogout;
    public ImageView imgStock,imgYesSale,imgTodaySale,imgMonthSale,imgEgg,imgChick,imgFeed;
    public AnchorPane parentPane;

    Util util=new UtilImpl();
    EggBO eggBO= BoFactory.getInstance().getBo(BoFactory.BoType.EGG);
    IncomeBO incomeBO = BoFactory.getInstance().getBo(BoFactory.BoType.INCOME);
    StoreBO storeBO = BoFactory.getInstance().getBo(BoFactory.BoType.STORE);
    BatchBO batchBO = BoFactory.getInstance().getBo(BoFactory.BoType.BATCH);
    FeedBO feedBO = BoFactory.getInstance().getBo(BoFactory.BoType.FEED);

    public void initialize() {
        try {
            new Bounce(cir1).setCycleDuration(4).setCycleCount(10).setDelay(Duration.millis(500)).play();
            new Bounce(cir2).setCycleDuration(4).setCycleCount(10).setDelay(Duration.millis(1000)).play();
            new Bounce(cir3).setCycleDuration(4).setCycleCount(10).setDelay(Duration.millis(1500)).play();
            new Bounce(cir4).setCycleDuration(4).setCycleCount(10).setDelay(Duration.millis(2000)).play();

            util.rotateAnimation(imgChick);
            util.rotateAnimation(imgEgg);
            util.rotateAnimation(imgFeed);
            util.rotateAnimation(imgMonthSale);
            util.rotateAnimation(imgStock);
            util.rotateAnimation(imgTodaySale);
            util.rotateAnimation(imgYesSale);
            setLineChartLastWeek();
            setData();
            util.setFadeTransition(parentPane,300);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setLineChartLastWeek() {

        XYChart.Series s1 = new XYChart.Series<>();
        s1.setName("Egg-S");
        try {
            List<Integer> S1 = eggBO.getEggsinWeek("S1");
            int i=0;
            for (Integer qty:S1) {
                s1.getData().add(new XYChart.Data<>(""+(i++), qty));
            }

            XYChart.Series s2 = new XYChart.Series<>();
            s2.setName("Egg-M");

            List<Integer> S2 = eggBO.getEggsinWeek("S2");
            int j=0;
            for (Integer qty:S2) {
                s2.getData().add(new XYChart.Data<>(""+(j++), qty));
            }

            XYChart.Series s3 = new XYChart.Series<>();
            s3.setName("Egg-L");

            List<Integer> S3 = eggBO.getEggsinWeek("S3");
            int k=0;
            for (Integer qty:S3) {
                s3.getData().add(new XYChart.Data<>(""+(k++), qty));

            }
            linechart.getData().addAll(s1,s2,s3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnDashBoardOnAction(ActionEvent actionEvent) {
        try {
            util.loadUI("AdminDashBoardForm",btnDashBoard);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setData(){
        try {
            StoreDTO s1 = storeBO.getStore("S1");
            StoreDTO s2 = storeBO.getStore("S2");
            StoreDTO s3 = storeBO.getStore("S3");

            lblTodaySale.setText(String.valueOf(incomeBO.getTodayIncome()));
            lblYestSale.setText(String.valueOf(incomeBO.getYesterdayIncome()));
            lblQtyEgg.setText(String.valueOf(s1.getQtyOnStore()+s2.getQtyOnStore()+s3.getQtyOnStore()));
            lblMonthSell.setText(String.valueOf(incomeBO.getLastMonthIncome()));

            lblQtySEgg.setText(s1.getQtyOnStore()+"");
            lblQtyMEgg.setText(s2.getQtyOnStore()+"");
            lblQtyLEgg.setText(s3.getQtyOnStore()+"");

            lblEggSPrice.setText(String.valueOf(s1.getUnitPrice()));
            lblEggMPrice.setText(String.valueOf(s2.getUnitPrice()));
            lblEggLPrice.setText(String.valueOf(s3.getUnitPrice()));

            lblChickQty.setText(String.valueOf(batchBO.getQtyWiseBatch("chick")));
            lblPulletQty.setText(String.valueOf(batchBO.getQtyWiseBatch("grower")));
            lblAdultQty.setText(String.valueOf(batchBO.getQtyWiseBatch("layer")));

            lblChickFeed.setText(String.valueOf(feedBO.getFeed("F1").getQtyOnHand()));
            lblGrowerFeed.setText(String.valueOf(feedBO.getFeed("F2").getQtyOnHand()));
            lblLayerFeed.setText(String.valueOf(feedBO.getFeed("F3").getQtyOnHand()));


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
