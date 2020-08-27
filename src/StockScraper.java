import org.jsoup.*;
import org.jsoup.helper.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import java.io.*;

public class StockScraper {

    public StockScraper() {

        this.fetchPrice("AMD");
        this.fetchPrice("AC.TOa");

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
            System.out.println("Unable to find stock, " + s);
        }

        return price;
    }

}