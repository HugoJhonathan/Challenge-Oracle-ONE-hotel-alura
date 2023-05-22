package views.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BtnExit extends JPanel {

    private final JLabel labelExit;

    public void mousePressed_(MouseEvent e) {
    }

    public BtnExit(Header.Tipo tipo, int x) {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mousePressed_(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setOpaque(true);
                setBackground(Color.red);
                labelExit.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setOpaque(false);
                setBackground(new Color(0, 0, 0, 0));
                labelExit.setForeground(tipo.text);
            }
        });
        setOpaque(false);
        setLayout(null);
        setBackground(new Color(0, 0, 0, 0));
        setBounds(x, 0, 53, 36);
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        labelExit = new JLabel("X");
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);
        labelExit.setForeground(tipo.text);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelExit.setBounds(0, 0, 53, 36);
        add(labelExit);
    }

}