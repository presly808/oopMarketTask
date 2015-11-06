package team2.view;

import team2.controller.AdminController;

/**
 * Created by dima on 06.11.2015.
 */
public class AdministratorView {
    private AdminController adminController;

    public AdministratorView(AdminController ac) {
        this.adminController = ac;

        startAdminView();
    }

    public void startAdminView() {
        System.out.println("Admin view started!!!");
    }
}
