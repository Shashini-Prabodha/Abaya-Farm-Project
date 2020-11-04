package lk.abayafarm.pos.util.impl;

import lk.abayafarm.pos.bo.BoFactory;
import lk.abayafarm.pos.bo.custom.SupplierOrderBO;

import java.awt.*;

public class GeanarateID {
    //set Supplier Order ID
    SupplierOrderBO supplierOrderBO= BoFactory.getInstance().getBo(BoFactory.BoType.SUPPLIERORDER);
    private void setSupOrderID(Label label) {
        try {
            String orderId = supplierOrderBO.getLastOrderID();
            String temp[] = orderId.split("O", 7);
            Integer number = Integer.parseInt(temp[1]);
            String newBatchID = "O" + (number + 1);
            label.setText(newBatchID);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
