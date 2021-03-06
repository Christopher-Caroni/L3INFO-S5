package fil.coo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class SecondPrinter {

    private Timer timer;

    public SecondPrinter() {
        initTimer();
    }

    private void initTimer() {
        timer = new Timer(1000, new PrintActionListener());
    }

    /**
     * Start the {@link Timer}
     */
    public void start() {
        timer.start();
    }

    /**
     * Print the currently date
     */
    public void printCurrentSecond() {
        System.out.println(Calendar.getInstance().getTime());
    }

    private class PrintActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            printCurrentSecond();
        }
    }
}
