package team3.controller;

import team3.model.Bill;
import team3.model.Seller;

/**
 * Created by serhii on 25.10.15.
 */
public interface ISellerController {

    Bill createBill(Seller seller);
    boolean scanProduct(Bill bill, String barCode);
    boolean eraseProduct(Bill bill, String barCode);
    String scanProductInfo(String barCode);
    boolean getPaid(Bill bill, double money);
    void showBill(Bill bill);


}
