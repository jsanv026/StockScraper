import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class GUI {

    private JFrame f;
    private JPanel p1, p2;
    private JTextField dataEntry, time;
    private JLabel lblData, timeLbl;
    private JButton btnGo, btnRef;
    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<TextGroup> txtArr = new ArrayList<>();
    private static StockScraper ss = StockScraper.getInstance();
    private int index = 0;
    private DateTimeFormatter dtf;
    private LocalDateTime currentTime;
    private Data data = new Data();

    public GUI() throws Exception {

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
        this.newTxt("BB.TO");
        this.newTxt("GME");

    }

    public void newTxt(String s) {

        TextGroup tmp = new TextGroup(s, ss.fetchPrice(s));
        txtArr.add(tmp);
        names.add(s);
        addField(tmp);
        try { data.overwrite(names); }
        catch (Exception exception) {}

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

    private void addField(TextGroup tg) {

        JButton btn = new JButton("Delete");
        btn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String s = tg.getLabelName();
                Component c = p1.getComponent(1);
                p2.remove(c);
                System.out.println(index);
                for (int i = 0 ; i < txtArr.size() ; ++i) {
                    if (txtArr.get(i) == null) { return; }
                    else if (s.equals(txtArr.get(i).getLabelName())) {
                        p2.remove(txtArr.get(i).getLbl());
                        p2.remove(txtArr.get(i).getTxt());
                        p2.remove(txtArr.get(i).getChange());
                        p2.remove(btn);
                    }

                }

                //refresh();
                f.remove(p2);
                f.add(p2);
                f.pack();

            }
        });

        index++;
        if (p2 == null) { p2 = new JPanel(new GridLayout(0,4)); }

        p2.add(tg.getLbl());
        p2.add(tg.getTxt());
        p2.add(tg.getChange());
        p2.add(tg.getChange());
        p2.add(btn);
        f.remove(p2);
        f.add(p2);
        f.pack();
    }

    private void btnInit() throws Exception {
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
                    try { newTxt(dataEntry.getText()); }
                    catch (Exception exce) {}

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
