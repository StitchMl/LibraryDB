package library.utilities;

import library.controller.MainController;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

public class DialogBooking extends JPanel{
    private JTextField textField1;
    private JFrame frame;
    private transient MainController app;
    private transient CountDownLatch modalitySignal;

    /** Creates the instance*/
    public DialogBooking() {}

    /** Creates the GUI shown inside the frame's content pane. */
    public DialogBooking(JFrame fram) {
        this.frame = fram;
        this.frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                app.setClose(true);
                modalitySignal.countDown();
                frame.dispose();
            }
        });
        JPanel frequentPanel = createSimpleDialogBox();
        frequentPanel.setName("dialogPanel");
        Border padding = BorderFactory.createEmptyBorder(20,20,5,20);
        frequentPanel.setBorder(padding);
        add(frequentPanel, BorderLayout.CENTER);
    }

    /** Creates the panel shown by the first tab. */
    private JPanel createSimpleDialogBox() {
        JRadioButton[] radioButtons = new JRadioButton[3];
        ButtonGroup group = new ButtonGroup();
        JButton showItButton = new JButton("Lend");

        JLabel label1 = new JLabel("CF");
        textField1 = new JTextField(10);
        String month1 = "1 mese";
        String month2 = "2 mesi";
        String month3 = "3 mesi";

        radioButtons[0] = new JRadioButton("1 mese");
        radioButtons[0].setName("1 month");
        radioButtons[0].setActionCommand(month1);

        radioButtons[1] = new JRadioButton("2 mesi");
        radioButtons[1].setName("2 month");
        radioButtons[1].setActionCommand(month2);

        radioButtons[2] = new JRadioButton("3 mesi");
        radioButtons[2].setName("3 month");
        radioButtons[2].setActionCommand(month3);

        for (int i = 0; i < 3; i++) {
            group.add(radioButtons[i]);
        }
        radioButtons[0].setSelected(true);

        showItButton.setName("nextButton");
        showItButton.addActionListener(e -> {
            String command = group.getSelection().getActionCommand();
            app.setValue1(textField1.getText());
            if (Objects.equals(command, month1)) {
                app.setValue2(month1);
                modalitySignal.countDown();
                frame.dispose();
            } else if (Objects.equals(command, month2)) {
                app.setValue2(month2);
                modalitySignal.countDown();
                frame.dispose();
            } else if (Objects.equals(command, month3)) {
                app.setValue2(month3);
                modalitySignal.countDown();
                frame.dispose();
            }
        });

        return createPane(label1, textField1, radioButtons, showItButton);
    }

    /** Used to create a pane*/
    private JPanel createPane(JLabel label1, JTextField textField1, JRadioButton[] radioButtons, JButton showButton) {
        JPanel box = new JPanel();
        JLabel label = new JLabel();
        Box h1 = Box.createHorizontalBox();
        h1.add(label1);
        h1.add(textField1);
        Box h2 = Box.createHorizontalBox();

        box.setLayout(new BoxLayout(box, BoxLayout.PAGE_AXIS));
        box.add(label);
        box.add(h1);
        h2.add(radioButtons[0]);
        h2.add(radioButtons[1]);
        h2.add(radioButtons[2]);
        box.add(h2);

        JPanel pane = new JPanel(new BorderLayout());
        pane.add(box, BorderLayout.PAGE_START);
        pane.add(showButton, BorderLayout.PAGE_END);
        return pane;
    }

    /** Create the GUI and show it*/
    public void createAndShowGUI() {
        //Create and set up the window.
        JFrame fr = new JFrame("Search");

        //Create and set up the content pane.
        DialogBooking newContentPane = new DialogBooking(fr);
        newContentPane.setApp(this.app);
        newContentPane.setWait(this.modalitySignal);
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
