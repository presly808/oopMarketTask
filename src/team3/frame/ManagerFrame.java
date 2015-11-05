package team3.frame;

import com.sun.deploy.panel.JSmartTextArea;
import team3.controller.IManagerController;
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
        paneWithLists.setDividerLocation((int) this.getSize().getWidth() / 2);
        paneWithLists.add(adminsListArea);
        paneWithLists.add(sellersListArea);


        JPanel southButtonsPanel = new JPanel(new GridLayout(1,3));

        JButton addButton = new JButton("Add user");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


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
}
