import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.UIManager;
import java.util.MissingResourceException;


import javax.swing.JFileChooser;


public class FileChooserDemoMotif extends JFrame {
// public class FileChooserDemoMotif extends ImageWizardPage {
    static private final String newline = "\n";

    public FileChooserDemoMotif() {
        super("FileChooserDemoMotif");

        //Create the log first, because the action listeners
        //need to refer to it.
        final JTextArea log = new JTextArea(5,20);
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);

        //Create a file chooser
        final JFileChooser fc = new JFileChooser();

        //Create the open button
        ImageIcon openIcon = new ImageIcon("images/open.gif");
        JButton openButton = new JButton("Open a File...", openIcon);


        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fc.setMultiSelectionEnabled(true);

                //int returnVal = fc.showOpenDialog(FileChooserDemoMotif.this);
                int returnVal = fc.showOpenDialog(null);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    //this is where a real application would open the file.
                    log.append("Opening: " + file.getName() + "." + newline);
                } else {
                    log.append("Open command cancelled by user." + newline);
                }
            }
        });

        //Create the save button
        ImageIcon saveIcon = new ImageIcon("images/save.gif");
        JButton saveButton = new JButton("Save a File...", saveIcon);
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int returnVal = fc.showSaveDialog(FileChooserDemoMotif.this);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    //this is where a real application would save the file.
                    log.append("Saving: " + file.getName() + "." + newline);
                } else {
                    log.append("Save command cancelled by user." + newline);
                }
            }
        });

        //For layout purposes, put the buttons in a separate panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);

        //Explicitly set the focus sequence.
//        openButton.setNextFocusableComponent(saveButton);
//        saveButton.setNextFocusableComponent(openButton);

        //Add the buttons and the log to the frame
        Container contentPane = getContentPane();
        contentPane.add(buttonPanel, BorderLayout.NORTH);
        contentPane.add(logScrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {

/*** AKM Added here for Motif Look and Feel ******/
        try {
         String look;
         look = UIManager.getSystemLookAndFeelClassName();
         System.out.println(" AKM System LookAndFeel = "+look);
  // Setting up MotifLookAndFeel
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.metal.MetalLookAndFeel");
        }
        catch(Exception ex )
        {
          System.out.println(" Failed to set System Look and Feel loading default");
        }

/*** AKM Added above for Motif Look and Feel ******/

         JFrame frame = new FileChooserDemoMotif();

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        frame.pack();
        frame.setVisible(true);
    }
}
