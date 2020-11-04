package lk.abayafarm.pos.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import lk.abayafarm.pos.util.Util;
import lk.abayafarm.pos.util.impl.UtilImpl;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class InfoController {

    public JFXButton imgFB;
    public JFXButton imgWeb;
    public JFXButton imgWhat;
    public JFXButton btnClose;
    Util util = new UtilImpl();


    public void fbHyperlinkOnAction(ActionEvent actionEvent) {
        try {
            Desktop.getDesktop().browse(new URL("https://www.facebook.com/shashiniprabodha.abeygunasekara.7").toURI());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void webHyperlinkOnAction(ActionEvent actionEvent) {
        try {
            Desktop.getDesktop().browse(new URL("https://abaya-farm.business.site/").toURI());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void whatsppMovesOnAction(MouseEvent mouseEvent) {
        util.setToolTip(imgWhat, "077 560 56 99");
    }

    public void imgWhatOnMouse(MouseEvent mouseEvent) {
        util.setToolTip(imgWhat, "077 560 56 99");
    }

    public void btnCloseOnAction(ActionEvent actionEvent) {
        try {
            util.loadUI(btnClose);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
