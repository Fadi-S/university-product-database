package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Stack;

public class Navigator {

    private static final Stack<Page> pages = new Stack<>();

    public static void goTo(Page page)
    {
        if(! pages.isEmpty()) {
            pages.peek().getFrame().setVisible(false);
        }

        JFrame frame = page.getFrame();
        frame.setVisible(true);
        frame.pack();

        if(page.closable()) {
            frame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    Navigator.back();
                }
            });
        }else {
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }

        pages.push(page);
    }

    public static void back()
    {
        if(pages.isEmpty()) {
            return;
        }

        Page page = pages.pop();

        JFrame frame = page.getFrame();

        frame.dispose();

        if(! pages.isEmpty()) {
            Page parentPage = pages.peek();
            parentPage.getFrame().setVisible(true);

            if(parentPage.shouldSkip()) {
                back();
            }
        }
    }

    public static void exit()
    {
        while(! pages.isEmpty()) {
            pages.pop().getFrame().dispose();
        }
    }
}
