import javax.swing.*;
import java.awt.*;

public class TextGroup {

    private JTextField txt;
    private JLabel lbl;
    private String s;

    public TextGroup(String s) {
        txt = new JTextField(20);
        lbl = new JLabel(s);
        lbl.setLabelFor(txt);
        this.s = s;
    }

    public String getLabelName() { return s; }
    public JTextField getTxt() { return txt; }
    public JLabel getLbl() { return lbl; }

    public void setLabel(String s) { lbl.setText(s); }



}
