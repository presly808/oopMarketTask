package team3.other_tests_and_examples;

import team3.IOHelper.IOHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;


public class ExampleFrame extends JFrame {

    private JTextField filePathField;
    private JTextArea fileContentArea;
    private JButton loadButton;
    private JButton saveButton;

    public ExampleFrame(){
        setSize(600,600);
        init();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void init(){

        filePathField = new JTextField("D:/GitRepo/ACO8/home/linux_beginner_steps.txt");


        fileContentArea = new JTextArea();

        JPanel southButtonsPanel = new JPanel(new GridLayout(1,2));

        loadButton = new JButton("Load");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String fileContent = IOHelper.readFrom(filePathField.getText());
                    fileContentArea.setText(fileContent);
                } catch (FileNotFoundException e1) {
                    JOptionPane.showMessageDialog(ExampleFrame.this,e1.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    IOHelper.writeTo(filePathField.getText(), fileContentArea.getText(), false);
                } catch (FileNotFoundException e1) {
                    JOptionPane.showMessageDialog(ExampleFrame.this,e1.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                }

                System.out.println("Saved");
            }
        });

        southButtonsPanel.add(loadButton);
        southButtonsPanel.add(saveButton);

        getContentPane().add(fileContentArea, BorderLayout.CENTER);
        getContentPane().add(filePathField, BorderLayout.NORTH);
        getContentPane().add(southButtonsPanel, BorderLayout.SOUTH);


    }


}
