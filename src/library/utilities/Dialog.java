package library.utilities;

import library.controller.MainController;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

public class Dialog extends JPanel{
    private JTextField textField1;
    private JTextField textField2;
    private JFrame frame;
    private transient MainController app;
    private transient CountDownLatch modalitySignal;

    /** Creates the instance*/
    public Dialog() {}

    /** Creates the GUI shown inside the frame's content pane. */
    public Dialog(JFrame fram, String value1, String value2) {
        this.frame = fram;
        this.frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                app.setClose(true);
                modalitySignal.countDown();
                frame.dispose();
            }
        });
        JPanel frequentPanel = createSimpleDialogBox(value1, value2);
        frequentPanel.setName("dialogPanel");
        Border padding = BorderFactory.createEmptyBorder(20,20,5,20);
        frequentPanel.setBorder(padding);
        add(frequentPanel, BorderLayout.CENTER);
    }

    /** Creates the panel shown by the first tab. */
    private JPanel createSimpleDialogBox(String value1, String value2) {

        JButton showItButton = new JButton("Search");

        JLabel label1 = new JLabel(value1);
        JLabel label2 = new JLabel(value2);
        textField1 = new JTextField(10);
        textField2 = new JTextField(10);

        showItButton.setName("nextButton");
        showItButton.addActionListener(e -> {
            //title
            if (!Objects.equals(textField1.getText(), null)) {
                app.setValue1(textField1.getText());
                modalitySignal.countDown();
                frame.dispose();

            //isbn
            } else if (!Objects.equals(textField1.getText(), null)) {
                app.setValue2(textField2.getText());
                modalitySignal.countDown();
                frame.dispose();
            }
        });

        return createPane(label1, label2, textField1, textField2, showItButton);
    }

    /** Used to create a pane*/
    private JPanel createPane(JLabel label1, JLabel label2, JTextField textField1, JTextField textField2, JButton showButton) {
        JPanel box = new JPanel();
        JLabel label = new JLabel();
        Box h1 = Box.createHorizontalBox();
        h1.add(label1);
        h1.add(textField1);
        Box h2 = Box.createHorizontalBox();
        h2.add(label2);
        h2.add(textField2);

        box.setLayout(new BoxLayout(box, BoxLayout.PAGE_AXIS));
        box.add(label);
        box.add(h1);
        box.add(h2);

        JPanel pane = new JPanel(new BorderLayout());
        pane.add(box, BorderLayout.PAGE_START);
        pane.add(showButton, BorderLayout.PAGE_END);
        return pane;
    }

    /** Create the GUI and show it*/
    public void createAndShowGUI(String value1, String value2) {
        //Create and set up the window.
        JFrame fr = new JFrame("Search");

        //Create and set up the content pane.
        Dialog newContentPane = new Dialog(fr, value1, value2);
        newContentPane.setWait(this.modalitySignal);
        newContentPane.setApp(this.app);
        newContentPane.setPreferredSize(new Dimension(220,100));
        fr.setContentPane(newContentPane);

        //Display the window.
        fr.setResizable(false);
        fr.pack();
        fr.setVisible(true);
    }

    /** Is called to set mainApp*/
    public void setApp(MainController app){
        this.app = app;
    }

    /** Is called to set wait variable*/
    public void setWait(CountDownLatch modalitySignal) {
        this.modalitySignal = modalitySignal;
    }
}
