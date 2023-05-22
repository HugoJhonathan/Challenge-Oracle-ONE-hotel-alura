package views;

import javax.swing.text.MaskFormatter;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Config {

    public static String patternPhone = "(##) #.####-####";
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    public static NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    public static MaskFormatter getMaskPhone() {
        try {
            return new MaskFormatter(patternPhone);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String formatStringWithPhoneMask(String s) {
        try {
            MaskFormatter formatter = getMaskPhone();
            formatter.setValueContainsLiteralCharacters(false);
            return formatter.valueToString(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static boolean validatePhoneMask(String s) {
        try {
            getMaskPhone().valueToString(s);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

}