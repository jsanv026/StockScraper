import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.lang.Exception;

public class Data {

    ArrayList<String> names;

    public Data() throws Exception {

        load();

    }

    public void overwrite(ArrayList<String> al) throws Exception {

        try {
            names = al;
            save();
        } catch (Exception e) {}

    }

    public void save() throws Exception {

        File f = new File("stockNames.txt");
        Scanner s = new Scanner(f);
        FileWriter fw = new FileWriter(f);

        fw.write(names.size() +"\n");

        for (String str : names) { fw.write(str + "\n"); }

        fw.close();
    }

    public boolean load() throws Exception {

        File f = new File("stockNames.txt");

        if (f.exists()) {

            Scanner s = new Scanner(f);
            int size = Integer.parseInt(s.nextLine());

            for (int i = 0 ; i < size ; ++i) { names.add(s.nextLine()); }

        } else { return false; }

        return true;

    }

}
