package lk.abayafarm.pos.controller;

import com.jfoenix.controls.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import lk.abayafarm.pos.bo.BoFactory;
import lk.abayafarm.pos.bo.custom.*;
import lk.abayafarm.pos.bo.custom.impl.BatchBOImpl;
import lk.abayafarm.pos.bo.custom.impl.CageBOImpl;
import lk.abayafarm.pos.bo.custom.impl.FeedBOImpl;
import lk.abayafarm.pos.dao.QueryDAO;
import lk.abayafarm.pos.dao.custom.impl.QueryDAOImpl;
import lk.abayafarm.pos.dto.BatchDTO;
import lk.abayafarm.pos.dto.CageDTO;
import lk.abayafarm.pos.dto.FeedDTO;
import lk.abayafarm.pos.dto.FeedDetailsDTO;
import lk.abayafarm.pos.entity.Feed;
import lk.abayafarm.pos.util.Regex;
import lk.abayafarm.pos.util.Util;
import lk.abayafarm.pos.util.impl.RegexImpl;
import lk.abayafarm.pos.util.impl.UtilImpl;
import tray.notification.NotificationType;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FeedDetailFormController {

    public JFXComboBox cmbCageId;
    public JFXDatePicker clndrDate;
    public TextField txtTerm, txtAvalilableChicken, txtApprox, txtAvailableStock, txtDescription;
    public ImageView imgFeedChick, imgFeedGrower, imgFeedLayer;
    public JFXButton btnUseFeed, btnHome, btnAddFeed;
    public JFXTextField txtUse;
    public AnchorPane mainAncorPane;
    public ImageView imgAdmin;
    static int packSize;
    public AnchorPane parentPane;


    Util util = new UtilImpl();
    CageBO cageBO = BoFactory.getInstance().getBo(BoFactory.BoType.CAGE);
    BatchBO batchBO = BoFactory.getInstance().getBo(BoFactory.BoType.BATCH);
    FeedDetailsBO feedDetailsBO = BoFactory.getInstance().getBo(BoFactory.BoType.FEEDDETAILS);
    QueryDAO queryDAO = new QueryDAOImpl();
    FeedServingBO feedServingBO = BoFactory.getInstance().getBo(BoFactory.BoType.FEEDSERVING);

    public void initialize() {
        try {
            getAllCageId();
            util.rotateAnimation(imgAdmin);
            clndrDate.setValue(LocalDate.now());
            cmbCageId.setFocusColor(Paint.valueOf("red"));
            cmbCageId.requestFocus();
            setTerm();
            util.setFadeTransition(parentPane, 300);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


    //load All cage ID to combo
    public void getAllCageId() {

        try {
            List<CageDTO> list = cageBO.getCageIDListIn();
            for (CageDTO i : list) {
                cmbCageId.getItems().addAll(i.getCageId());
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    public void cmbCageIdOnAction(ActionEvent actionEvent) {
        try {
            CageDTO cage = cageBO.getCage((String) cmbCageId.getValue());
            BatchDTO batch = batchBO.getBatch(cage.getBatchId());
            FeedDTO feed = queryDAO.getFeed(batch.getType());

            if (feed.getFeedType().equals("chick")) {
                imageSetting(true, false, false);
                packSize = 25;
            } else if (feed.getFeedType().equals("grower")) {
                imageSetting(false, true, false);
                packSize = 50;
            } else if (feed.getFeedType().equals("layer")) {
                imageSetting(false, false, true);
                packSize = 50;
            } else {
            }
            txtAvailableStock.setText(String.valueOf(calculateFeedStock(feed.getQtyOnHand(), feed.getPackSize())));
            txtAvalilableChicken.setText(String.valueOf(cage.getAvailableQty()));
            txtApprox.setText(String.valueOf(approxFeed(cage.getAvailableQty(), feed.getFeedType())));
            setTerm();
            setDescription(feed.getFeedType());
            util.focus(txtUse);
        } catch (NullPointerException e) {
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private int calculateFeedStock(int qty, int packSize) {
        return qty * packSize;
    }

    private double approxFeed(int avaQty, String feedType) {
        double oneFeed;
        if (feedType.equals("chick")) {
            oneFeed = 0.04;
        } else if (feedType.equals("grower")) {
            oneFeed = 0.05;
        } else {
            oneFeed = 0.06;
        }
        return oneFeed * avaQty;
    }

    private void imageSetting(boolean a, boolean b, boolean c) {
        imgFeedChick.setVisible(a);
        imgFeedGrower.setVisible(b);
        imgFeedLayer.setVisible(c);
    }

    private void setDescription(String feedType) {
        String des;
        if (feedType.equals("chick")) {
            des = "Starter chicken feed";
        } else if (feedType.equals("grower")) {
            des = "Grower chicken feed";
        } else {
            des = "Layer chicken feed";
        }
        txtDescription.setText(des);
    }

    //set term
    public int setTerm() throws Exception {
        Date date = Date.valueOf(clndrDate.getValue());
        int lastTerm = feedDetailsBO.getLastTerm((String) cmbCageId.getValue(), date);
        System.out.println("lastterm" + lastTerm);
        txtTerm.setText((lastTerm + 1) + "");
        return lastTerm;

    }

    public void btnUseFeedOnAction(ActionEvent actionEvent) {
        try {
            if (!cmbCageId.getSelectionModel().isEmpty()) {
                int use = Integer.parseInt(txtUse.getText());
                int available = Integer.parseInt(txtAvailableStock.getText());
                LocalDate value = clndrDate.getValue();
                Date date = Date.valueOf(value);

                CageDTO cage = cageBO.getCage((String) cmbCageId.getValue());
                BatchDTO batch = batchBO.getBatch(cage.getBatchId());

                FeedDTO feedtemp = queryDAO.getFeed(batch.getType());

                FeedDTO feed = new FeedDTO(feedtemp.getFeedId(), feedQty(), feedtemp.getFeedType(), feedtemp.getPackSize());
                FeedDetailsDTO feedDetailsDTO = new FeedDetailsDTO(feedtemp.getFeedId(), (String) cmbCageId.getValue(), Integer.parseInt(txtTerm.getText()), Integer.parseInt(txtUse.getText()), date);

                if (Pattern.compile("^([0-9.]{1,})$").matcher(txtUse.getText()).matches()) {
                    if (available >= use) {
                        if (Integer.parseInt(txtTerm.getText()) <= 2) {

                            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you need " + use + "kg feed? ", yes, no);
                            Optional<ButtonType> result = alert.showAndWait();

                            if (result.orElse(no) == yes) {
                                if (feedServingBO.feedServe(feed, feedDetailsDTO)) {
                                    util.trayNotification("Use Feed", "Use " + txtUse.getText() + " Feed .....! ", NotificationType.SUCCESS);
                                    txtAvailableStock.setText(String.valueOf((Integer.parseInt(txtAvailableStock.getText()) - Integer.parseInt(txtUse.getText()))));
                                    setTerm();
                                } else {
                                    util.loadUI("AlertForm");
                                    util.trayNotification("Use Feed", "Can't Use Feed .....! ", NotificationType.ERROR);
                                }
                            }
                        } else {
                            util.notification("Error", "All Terms Are Over....");
                        }
                    } else {
                        util.notification("", "\"Out Of Stock....\\n Please add new feed\"");
                        btnAddFeed.requestFocus();
                    }
                } else {
                    util.focus(txtUse);
                }
            } else {
                util.notification("", "Please choose cage...!");
                cmbCageId.setFocusColor(Paint.valueOf("red"));
                cmbCageId.requestFocus();
            }

        } catch (NumberFormatException e) {
            util.focus(txtUse);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    private int feedQty() {

        double feed = Double.parseDouble(txtAvailableStock.getText()) - Double.parseDouble(txtUse.getText());
        System.out.println("feed tot " + feed / packSize);
        return (int) (feed / packSize);

    }

    public void btnHomeOnAction(ActionEvent actionEvent) throws Exception {
        util.loadUI("AdminDashBoardForm", btnHome);
    }

    public void btnAddFeedOnAction(ActionEvent actionEvent) throws Exception {
        util.loadUI("FeedForm");

        //addBlurEffect(10,10);

    }

    public void addBlurEffect(int x, int y) {
        BoxBlur boxBlur = new BoxBlur();
        boxBlur.setHeight(x);
        boxBlur.setWidth(y);
        mainAncorPane.setEffect(boxBlur);

    }
}
