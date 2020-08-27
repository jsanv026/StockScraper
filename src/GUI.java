import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI {

    private JFrame f;
    private JPanel p1;
    private JPanel p2;
    private JTextField dataEntry;
    private JLabel lblData;
    private JButton btnGo;
    private JButton btnRef;
    private TextGroup[] txtArr = new TextGroup[0];
    private static StockScraper ss = new StockScraper();
    private int arrCount = 0;

    public GUI() {
        f = new JFrame("StockScraper v0.1");
        p1 = new JPanel(new GridLayout(2,2));
        dataEntry = new JTextField(10);
        lblData = new JLabel("Enter stock code");

        lblData.setLabelFor(dataEntry);
        p1.add(lblData);
        p1.add(dataEntry);
        btnInit();
        p1.add(btnGo);
        p1.add(btnRef);
        f.add(p1);
        f.setLayout(new FlowLayout());
        this.newTxt("AMD");
        this.newTxt("AC.TO");
        this.newTxt("AAPL");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

    }

    public void newTxt(String s) {
        TextGroup tmp = new TextGroup(s, ss.fetchPrice(s));
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
            p2 = new JPanel(new GridLayout(0,2));
        }

        p2.add(tg.getLbl());
        p2.add(tg.getTxt());
        f.remove(p2);
        f.add(p2);
        f.pack();
    }

    private void btnInit() {
        btnGo = new JButton("Go");
        btnRef = new JButton("Refresh");
        btnGo.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (dataEntry.getText().equals("")){
                    JOptionPane d = new JOptionPane();
                    d.showMessageDialog(f, "Error, box cannot be blank");
                    return;
                }

                if (!ss.fetchPrice(dataEntry.getText()).equals(null)) {
                    newTxt(dataEntry.getText());
                } else {
                    JOptionPane d = new JOptionPane();
                    d.showMessageDialog(f , "Unable to find stock, " + dataEntry.getText());
                }

            }
        });

        btnRef.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JDialog d = new JDialog(f, "Hello", true);
                d.setLocationRelativeTo(f);
                d.setVisible(true);
            }
        });
    }

}
