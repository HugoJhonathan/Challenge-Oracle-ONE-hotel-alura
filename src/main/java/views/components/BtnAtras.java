package views.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BtnAtras extends JPanel {

    public void mousePressed_(MouseEvent e) {
    }

    JLabel labelAtras;

    public BtnAtras(Header.Tipo tipo) {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mousePressed_(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setOpaque(true);
                setBackground(new Color(12, 138, 199));
                labelAtras.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setOpaque(false);
                setBackground(new Color(0, 0, 0, 0));
                labelAtras.setForeground(tipo.text);
            }
        });
        setOpaque(false);
        setBackground(new Color(0, 0, 0, 0));
        setBounds(0, 0, 53, 36);
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        labelAtras = new JLabel("<");
        labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
        labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
        labelAtras.setBounds(0, 0, 53, 36);
        labelAtras.setForeground(tipo.text);
        add(labelAtras);
    }

}