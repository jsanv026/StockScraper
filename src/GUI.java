import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class GUI {

    private JFrame f;
    private JPanel p1, p2;
    private JTextField dataEntry, time;
    private JLabel lblData, timeLbl;
    private JButton btnGo, btnRef;
    private TextGroup[] txtArr = new TextGroup[0];
    private static StockScraper ss = StockScraper.getInstance();
    private int arrCount = 0;
    private DateTimeFormatter dtf;
    private LocalDateTime currentTime;

    public GUI() {
        f = new JFrame("StockScraper v1.0");
        p1 = new JPanel(new GridLayout(3,2));
        dataEntry = new JTextField(10);
        time = new JTextField(10);
        lblData = new JLabel("Enter stock code");
        timeLbl = new JLabel("Time");

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(100,100);
        f.setVisible(true);

        dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        currentTime = LocalDateTime.now();
        time.setText(dtf.format(currentTime));
        time.setEditable(false);

        lblData.setLabelFor(dataEntry);
        timeLbl.setLabelFor(time);
        p1.add(timeLbl);
        p1.add(time);
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
            p2 = new JPanel(new GridLayout(0,3));
        }

        p2.add(tg.getLbl());
        p2.add(tg.getTxt());
        p2.add(tg.getChange());
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

                if (ss.fetchPrice(dataEntry.getText()) != null) {
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
            { refresh(); }
        });
    }

    private void refresh() {

        for (TextGroup elem : txtArr ) { refreshOne(elem); }
        f.remove(p2);
        f.add(p2);
        dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        currentTime = LocalDateTime.now();
        time.setText(dtf.format(currentTime));

    }

    private void printOld() {

        for (TextGroup elem : txtArr) {



        }

    }

    private void refreshOne(TextGroup tg) { tg.setTxt(ss.fetchPrice(tg.getLabelName())); }

}
