package team3.controller;

import com.sun.java.util.jar.pack.*;
import team3.model.Admin;
import team3.model.Seller;

import java.util.ArrayList;

/**
 * Created by 1 on 05.11.2015.
 */
public interface IManagerController {


    ArrayList<Admin> getAdmins();

    ArrayList<Seller> getSellers();

    String getAdminsString();

    String getSellersString();

    boolean addAdmin(int id, String login, String pass);

    boolean addSeller(int id, String login, String pass);

    boolean deleteUser(int id);

}
