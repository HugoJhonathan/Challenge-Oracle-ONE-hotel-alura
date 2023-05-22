package views;

import javax.swing.*;
import java.awt.*;

public class InterfaceUtil {

    public static void showMessageDialog(Component component, String message) {
        JOptionPane.showMessageDialog(component, message, "Aviso!", JOptionPane.WARNING_MESSAGE);
    }

}