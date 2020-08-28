import javax.swing.*;
import java.awt.*;

public class TextGroup {

    private JTextField txt;
    private JLabel lbl;
    private String s;

    public TextGroup(String s, String dat) {
        this.txt = new JTextField("$" + dat, 10);
        this.txt.setEditable(false);
        lbl = new JLabel(s);
        lbl.setLabelFor(this.txt);
        this.s = s;
    }

    public String getLabelName() { return s; }
    public JTextField getTxt() { return txt; }
    public JLabel getLbl() { return lbl; }

    public void setLabel(String s) { lbl.setText(s); }
    public void setTxt(String dat) { txt.setText("$" + dat); }



}
