package views.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Header extends JPanel {

    int xMouse, yMouse;
    private Component comp;

    Tipo tipoBtnVoltar;
    Tipo tipoBtnExit;

    public void onClickBtnVoltar(MouseEvent e) {
    }

    public void onClickBtnExit(MouseEvent e) {
        System.exit(0);
    }

    public Header(Component comp, Tipo btnExitColor) {
        this.comp = comp;
        this.tipoBtnExit = btnExitColor;
        init();
    }

    public Header(Component comp, Tipo btnVoltarColor, Tipo btnExitColor) {
        this.comp = comp;
        this.tipoBtnVoltar = btnVoltarColor;
        this.tipoBtnExit = btnExitColor;
        init();
        add(new BtnAtras(tipoBtnVoltar) {
            @Override
            public void mousePressed_(MouseEvent e) {
                onClickBtnVoltar(e);
            }
        });
    }

    public void init() {
        setLayout(null);
//        setOpaque(false);
        setBackground(new Color(0, 0, 0, 0));
        setBounds(0, 0, comp.getWidth(), 36);
        add(new BtnExit(tipoBtnExit, comp.getWidth() - 53) {
            @Override
            public void mousePressed_(MouseEvent e) {
                onClickBtnExit(e);
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                headerMouseDragged(e);
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setCursor(new Cursor(Cursor.MOVE_CURSOR));
                headerMousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                //setBackground(null);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
    }

    //Código que permite movimentar a janela pela tela seguindo a posição de "x" e "y"
    private void headerMousePressed(java.awt.event.MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        comp.setLocation(x - xMouse, y - yMouse);
    }


    public enum Tipo {
        DARK(Color.white),
        LIGHT(Color.black);

        public Color text;

        Tipo(Color text) {
            this.text = text;
        }
    }

}