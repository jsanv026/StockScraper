import javax.swing.*;
import java.text.DecimalFormat;

public class TextGroup {

    private JTextField txt, change;
    private JLabel lbl;
    private String old, s;
    private DecimalFormat df;

    public TextGroup(String s, String dat) {
        old = "-";
        this.txt = new JTextField("$" + dat, 10);
        change = new JTextField(old,10);
        change.setEditable(false);
        this.txt.setEditable(false);
        lbl = new JLabel(s);
        lbl.setLabelFor(this.txt);
        this.s = s;
        old = dat;

    }

    public String getLabelName() { return s; }
    public JTextField getTxt() { return txt; }
    public JLabel getLbl() { return lbl; }
    public JTextField getChange() { return change;}

    public void setLabel(String s) { lbl.setText(s); }
    public void setTxt(String dat) {

        txt.setText("$" + dat);
        double dChange = Double.parseDouble(dat) - Double.parseDouble(old);
        df = new DecimalFormat("##.##");

        change.setText("$" + (df.format(dChange)));
        old = dat;
    }

}
