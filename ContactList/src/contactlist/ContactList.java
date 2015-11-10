package contactlist;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.*;
import java.text.*;



public class ContactList extends JPanel{

    
    private int age;
    
    
    private JLabel nameLab;
    private JLabel ageLab;
    private JLabel emailLab;
    private JLabel cellLab;
    
    private static String nameString = "Name: ";
    private static String ageString = "Age: ";
    private static String emailString = "Email: ";
    private static String cellString = "Mobile: ";
    
    private JFormattedTextField nameField;
    private JFormattedTextField ageField;
    private JFormattedTextField emailField;
    private JFormattedTextField cellField;
    
    private NumberFormat ageFormat;
    
    
    public ContactList(){
        super(new BorderLayout());
        setUpFormats();
        
        //Creating Labels
        nameLab = new JLabel(nameString);
        ageLab = new JLabel(ageString);
        emailLab = new JLabel(emailString);
        cellLab = new JLabel(cellString);
        
        //Creating Text Fields
        nameField = new JFormattedTextField();
        nameField.setColumns(10);
        
        
        ageField = new JFormattedTextField(ageFormat);
        ageField.setValue(new Integer(age));
        ageField.setColumns(10);
        ageField.setText("");
        
        
        emailField = new JFormattedTextField();
        emailField.setColumns(10);
        
        
        cellField = new JFormattedTextField();
        cellField.setColumns(10);
        
        
        
        // Label & TextField Pairs
        nameLab.setLabelFor(nameField);
        ageLab.setLabelFor(ageField);
        emailLab.setLabelFor(emailField);
        cellLab.setLabelFor(cellField);
        
        //Layout labels in panel
        JPanel labelPane = new JPanel(new GridLayout(0,1));
        labelPane.add(nameLab);
        labelPane.add(ageLab);
        labelPane.add(emailLab);
        labelPane.add(cellLab);
        
        //Layout TextFields in panel
        JPanel fieldPane = new JPanel(new GridLayout(0,1));
        fieldPane.add(nameField);
        fieldPane.add(ageField);
        fieldPane.add(emailField);
        fieldPane.add(cellField);

        JButton save = new JButton("Save");
        save.setSize(200, 200);
        save.setMaximumSize(save.getPreferredSize());
        save.setAlignmentX(Component.CENTER_ALIGNMENT);  
        save.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
        
                String fileName = "out.txt";
                String age1 = ageField.getText();
                int age2 = Integer.parseInt(age1);
                
                
                
                if (age2 < 1 || age2 > 120){
                    JOptionPane.showMessageDialog(fieldPane, "Please enter a valid age between 0 and 120");
                }
                else
                {
                

                    try {
                        PrintWriter newFile = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
                        newFile.println("Name: " + nameField.getText());
                        newFile.println("Age: " + ageField.getText());
                        newFile.println("Email: " + emailField.getText());
                        newFile.println("Mobile Number: " + cellField.getText());
                        newFile.println("");

                        newFile.close();
                        System.out.println("Done");

                        nameField.setText("");
                        ageField.setText("");
                        emailField.setText("");
                        cellField.setText("");

                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                }      
        });
                
        JButton exit = new JButton("Exit");
        exit.setSize(200, 200);
        exit.setMaximumSize(exit.getPreferredSize());
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
        exit.addActionListener (new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                System.exit(0);
            }
        });

        //Layout labels in panel
        JPanel buttonPane = new JPanel(new GridLayout(0,1));
        buttonPane.add(save);
        buttonPane.add(exit);
        
        
        
        
                
        //Mixed Panel Layout
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(labelPane, BorderLayout.CENTER);
        add(fieldPane, BorderLayout.LINE_END);
        add(buttonPane, BorderLayout.PAGE_END);
                
        
        
        
    }
    
    private static void createAndShowGUI(){
    
        JFrame frame = new JFrame("Contact List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.add(new ContactList());
        
        frame.pack();
        frame.setVisible(true);
    

        
    }
    
    

    public static void main(String[] args) {
        
        
        JFrame frame1 = new JFrame("Welcome to contact list");
        frame1.setSize(500, 500);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel p = new JPanel();
        p.setBackground(Color.LIGHT_GRAY);
        p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel p2 = new JPanel();
        p2.setBackground(Color.LIGHT_GRAY);
        p2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        
        JButton newContact = new JButton("New Contact");
        newContact.setSize(200, 200);
        newContact.setMaximumSize(newContact.getPreferredSize());
        newContact.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
        newContact.addActionListener (new ActionListener(){
            public void actionPerformed(ActionEvent ace){
                SwingUtilities.invokeLater(new Runnable(){
                public void run(){
                    UIManager.put("swing.boldMetal", Boolean.FALSE);
                    createAndShowGUI();
                }
                        
                });
            }
        });
        
        JTextArea text = new JTextArea(5,30);
        text.setEditable(false);
        
        JButton viewContact = new JButton("View Contacts");
        viewContact.setSize(200, 200);
        viewContact.setMaximumSize(viewContact.getPreferredSize());
        viewContact.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
        viewContact.addActionListener (new ActionListener(){
            public void actionPerformed(ActionEvent ave){
                try{
                    FileReader reader = new FileReader("out.txt");
                    BufferedReader br = new BufferedReader(reader);
                    text.read(br, null);
                    br.close();
                    text.requestFocus();
                }
                catch(Exception e2){   
                }
                
            }
                        
        });
            
        
        
        
        JButton exit1 = new JButton("Exit");
        exit1.setSize(200, 200);
        exit1.setMaximumSize(exit1.getPreferredSize());
        exit1.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
        exit1.addActionListener (new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                System.exit(0);
            }
        });
        
        

        
        
        p.add(text);
        p2.add(viewContact);
        p2.add(newContact);
        p2.add(exit1);
        
        
        //Mixed Panel Layout
        

        
        
        frame1.getContentPane().add(p, BorderLayout.CENTER);
        frame1.getContentPane().add(p2, BorderLayout.PAGE_END);
        frame1.setVisible(true);

        
    }
    
    
    
    private void setUpFormats(){

       
        
        ageFormat = NumberFormat.getNumberInstance();
        
        
        
    }
    
}
