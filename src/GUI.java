import javax.swing.*;
import java.awt.*;

public class GUI {

    private JFrame f;
    private JPanel p1;
    private JPanel p2;
    private JTextField dataEntry;
    private JLabel lblData;
    private TextGroup[] txtArr = new TextGroup[0];
    private int arrCount = 0;

    public GUI() {
        f = new JFrame("StockScraper v0.1");
        p1 = new JPanel(new FlowLayout());
        dataEntry = new JTextField(20);
        lblData = new JLabel("Enter stock code");
        lblData.setLabelFor(dataEntry);
        p1.add(lblData);
        p1.add(dataEntry);
        f.add(p1);
        f.setLayout(new GridLayout(0,1));
        this.newTxt("test");
        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

    }

    public void newTxt(String s) {
        TextGroup tmp = new TextGroup(s);
        if (arrCount == txtArr.length) {
            arrInc(txtArr);
            txtArr[arrCount++] = tmp;
        } else {
            txtArr[arrCount++] = tmp;
        }
        addField(tmp);
    }

    public boolean editTxt(String o, String n) {
        for (TextGroup elem : txtArr) {
            if (elem.equals(o)) {
                elem.setLabel(n);
                return true;
            }
        }
        return false;
    }

    private void arrInc(TextGroup[] o) {

        TextGroup[] txtArr = new TextGroup[o.length + 1];

        for (int i = 0 ; i < o.length ; i++) { txtArr[i] = o[i]; }

        this.txtArr = txtArr;

    }

    private void addField(TextGroup tg) {

        if (p2 == null) {
            p2 = new JPanel(new FlowLayout());
        }

        p2.add(tg.getLbl());
        p2.add(tg.getTxt());
        f.remove(p2);
        f.add(p2);
    }

}
