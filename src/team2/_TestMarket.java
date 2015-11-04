package team2;

import team2.model.Product;
import team2.view.ShowProductsList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by DNK on 04.11.2015.
 */
public class _TestMarket {
    public static void main(String[] args) {
        Product pt1 = new Product("0000155", "Box1", 150);
        Product pt2 = new Product("0000255", "Box2", 200);
        Product pt3 = new Product("0000355", "Box3", 300);
        //System.out.println(pt.toString());
        final ArrayList<Product> arrayList = new ArrayList<Product>();
        arrayList.add(pt1);
        arrayList.add(pt2);
        arrayList.add(pt3);
        final ShowProductsList spl = new ShowProductsList();

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                spl.showProducts(arrayList); // вывод панели c таблицей
            }
        });

    }
}
