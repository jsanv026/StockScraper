import org.jsoup.*;
import org.jsoup.helper.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import java.io.*;

public class StockScraper {

    private static StockScraper inst;

    private StockScraper() {
        System.out.println("Instance created");
    }

    public static StockScraper getInstance() {
        if (inst == null) { inst = new StockScraper(); }
        return inst;
    }

    public String fetchPrice(String s) {
        Document doc = null;
        String price = null;
        try {
            doc = Jsoup.connect("https://ca.finance.yahoo.com/quote/" + s).get();
            System.out.println("Connected.");
            price = doc.select("span[data-reactid='32']").first().text();
            System.out.println(s + " - " + price);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to find stock, " + s);
        }

        try {
            double tmp = Double.parseDouble(price);
            System.out.println(tmp);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return price;
    }

}