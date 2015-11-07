package team3.frame;

import com.sun.deploy.panel.JSmartTextArea;
import team3.controller.IManagerController;
import team3.controller.ManagerController;
import team3.model.Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by 1 on 05.11.2015.
 */
public class ManagerFrame extends JFrame {

    private Manager user;
    private IManagerController iManagerController;
    private JPanel northListsPanel;
    private JTextArea adminsListArea;
    private JTextArea sellersListArea;


    public ManagerFrame(Manager user, IManagerController iManagerController) {

        this.user = user;
        this.iManagerController = iManagerController;

        setLocationRelativeTo(null);
        setSize(500, 500);
        setTitle(String.format("Manager: %s",user.getLogin()));
        init();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


    }

    private void init() {

        JPanel northListsPanel = new JPanel(new GridLayout(1,2));

        northListsPanel.add(new JLabel("Admins:"));
        northListsPanel.add(new JLabel("Sellers:"));

        adminsListArea = new JTextArea(iManagerController.getAdminsString());
        adminsListArea.setEditable(false);
        JScrollPane scrollPaneAdmins = new JScrollPane(adminsListArea);

        sellersListArea = new JTextArea(iManagerController.getSellersString());
        sellersListArea.setEditable(false);
        JScrollPane scrollPaneSellers = new JScrollPane(sellersListArea);


        JSplitPane paneWithLists = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        paneWithLists.setAutoscrolls(true);
        paneWithLists.setDividerLocation((int) this.getSize().getWidth() / 2 - 15);
        paneWithLists.add(scrollPaneAdmins);
        paneWithLists.add(scrollPaneSellers);


        JPanel southButtonsPanel = new JPanel(new GridLayout(1,3));

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new AddUserFrame();

            }
        });


        JButton eraseButton = new JButton("Fire");
        eraseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new DeleteUserFrame();

            }
        });


        JButton signOutButton = new JButton("Sign out");
        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                new LoginFrame(((ManagerController) iManagerController).getMarketDB());

            }
        });

        southButtonsPanel.add(addButton);
        southButtonsPanel.add(eraseButton);
        southButtonsPanel.add(signOutButton);

        getContentPane().add(paneWithLists);
        getContentPane().add(northListsPanel, BorderLayout.NORTH);
        getContentPane().add(southButtonsPanel, BorderLayout.SOUTH);



    }

    private void refreshInfo() {

        adminsListArea.setText(iManagerController.getAdminsString());
        sellersListArea.setText(iManagerController.getSellersString());
    }





    private class DeleteUserFrame extends JFrame {

        public DeleteUserFrame(){
            setLocationRelativeTo(ManagerFrame.this);
            setSize(250,100);
            setTitle("User deleting");
            init();
            setVisible(true);
            setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        }

        private void init () {

            JLabel idLog = new JLabel("ID: ");

            JTextField idInput = new JTextField();

            JButton deleteUser = new JButton("Fire");
            deleteUser.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    try {
                        int idInt = Integer.valueOf(idInput.getText());
                        boolean deleted = iManagerController.deleteUser(idInt);

                        if (deleted) {
                            JOptionPane.showConfirmDialog(ManagerFrame.this, "User's account has been deleted.", "SUCCESS", JOptionPane.DEFAULT_OPTION);
                            refreshInfo();
                        } else if (!deleted) {
                            JOptionPane.showMessageDialog(ManagerFrame.this, "This user (ID) doesn't exist in market database.", "ERROR", JOptionPane.ERROR_MESSAGE);

                        }


                    } catch (NumberFormatException e1) {

                        JOptionPane.showMessageDialog(ManagerFrame.this, "Cannot convert ID", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }

                }
            });

            JPanel northInputPanel = new JPanel(new GridLayout(1,2));

            northInputPanel.add(idLog);
            northInputPanel.add(idInput);

            getContentPane().add(northInputPanel, BorderLayout.NORTH);
            getContentPane().add(deleteUser, BorderLayout.SOUTH);


        }
    }


    private class AddUserFrame extends JFrame {


        public AddUserFrame() {
            setLocationRelativeTo(ManagerFrame.this);
            setSize(250, 175);
            setTitle("User adding");
            init();
            setVisible(true);
            setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        }

        private void init() {

            JLabel idLog = new JLabel("ID: ");
            JLabel loginLog = new JLabel("Login: ");
            JLabel passLog = new JLabel("Password: ");

            JTextField id = new JTextField();
            JTextField login = new JTextField();
            JTextField pass = new JTextField();

            JRadioButton isAdminButton = new JRadioButton("Admin");
            JRadioButton isSellerButton = new JRadioButton("Seller");


            isAdminButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    isSellerButton.setSelected(false);
                }
            });

            isSellerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    isAdminButton.setSelected(false);
                }
            });


            JButton addUser = new JButton("Register");
            addUser.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {


                    try {
                        int userID = Integer.valueOf(id.getText());
                        String userLogin = login.getText();
                        String userPass = pass.getText();

                        if (isAdminButton.isSelected()){

                            boolean res = iManagerController.addAdmin(userID, userLogin, userPass);
                            if (res){
                                JOptionPane.showConfirmDialog(ManagerFrame.this, "Registered", "SUCCESS", JOptionPane.DEFAULT_OPTION);
                            } else if (!res) {
                                JOptionPane.showMessageDialog(ManagerFrame.this, "Such user's ID is already used", "ERROR", JOptionPane.ERROR_MESSAGE);
                            }

                        } else if (isSellerButton.isSelected()){

                            boolean res = iManagerController.addSeller(userID, userLogin, userPass);
                            if (res){
                                JOptionPane.showConfirmDialog(ManagerFrame.this, "Registered", "SUCCESS", JOptionPane.DEFAULT_OPTION);
                            } else if (!res) {
                                JOptionPane.showMessageDialog(ManagerFrame.this, "Such user's ID is already used", "ERROR", JOptionPane.ERROR_MESSAGE);
                            }

                        } else {
                            JOptionPane.showMessageDialog(ManagerFrame.this, "Choose type of an account", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }

                        refreshInfo();

                    } catch (NumberFormatException e1) {

                    }



                }
            });

            JPanel centreInputPanel = new JPanel(new GridLayout(3,2));

            centreInputPanel.add(idLog);
            centreInputPanel.add(id);
            centreInputPanel.add(loginLog);
            centreInputPanel.add(login);
            centreInputPanel.add(passLog);
            centreInputPanel.add(pass);

            JPanel northChoicePanel = new JPanel(new GridLayout(1,2));
            northChoicePanel.add(isAdminButton);
            northChoicePanel.add(isSellerButton);

            getContentPane().add(northChoicePanel, BorderLayout.NORTH);
            getContentPane().add(centreInputPanel, BorderLayout.CENTER);
            getContentPane().add(addUser, BorderLayout.SOUTH);
        }
    }
}
