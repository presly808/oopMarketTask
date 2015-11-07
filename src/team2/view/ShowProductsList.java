package team2.view;

import team2.model.Product;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by DNK on 04.11.2015.
 */
public class ShowProductsList {

    public void showProducts(ArrayList<Product> prodList){
        ShowFrameProduct frameProduct = new ShowFrameProduct();
        frameProduct.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] columnNames = {"barCode","model","price"};

        String[][] prod = {
                {"barCode1","model1","price1"},
                {"barCode2","model2","price2"},
                {"barCode3","model3","price3"},
        };

        JTable table = new JTable(prod, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);

        frameProduct.getContentPane().add(scrollPane);
        frameProduct.setPreferredSize(new Dimension(450, 400));
        frameProduct.pack();
        frameProduct.setLocationRelativeTo(null);
        frameProduct.setVisible(true);

    }

    class ShowFrameProduct extends JFrame{
        public ShowFrameProduct(){
            setTitle("List products");
            setSize(1, 1);
            JPanel panel = new JPanel();
            Container pane = getContentPane();
            pane.add(panel);

        }
    }
}
