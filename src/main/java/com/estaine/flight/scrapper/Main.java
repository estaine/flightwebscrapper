package com.estaine.flight.scrapper;

import com.estaine.flight.Flight;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main {

    //private final static String[] europeanNodes = {"MOW", "WAW", "IEV", "VIE", "MUC", "MAD", "ROM", "BRU", "LIS", "PAR", "FRA"};//{"MOW", "MAD", "BCN", "LIS", "CDG", "IST"};//, "FRA", "MUC", "AMS", "FCO", "CPH", "ARL", "BRU", "TXL", "MIL"};
    private final static String[] europeanNodes = {"MSQ"};
    //private final static String[] europeanNodes = {"MAD"};
    private final static String[] overseasNodes = {
            "GVA", "ZRH", "BRN", "BSL", "ACH", "SIR", "LUG", //SWI
            "MIL", "TRN", "VCE", "VRN", "GOA", "TRS","BZO",      //ITA
            "INN", "SZG", "LNZ", "KLU", "GRZ",         //AUT
            "LYS", "LYN", "GNB", "NCE", "CMF",               //FRA
            "BCN",                              //ESP
            "MUC", "FDH", "FMM",                //GER
            "LJU",                              //SLO

    };
    //private final static String[] overseasNodes = {"MEX", "BOG", "CUN", "LIM", "SCL", "PTY", "BUE", "UIO"};
    //private final static String[] europeanNodes = {"MIL", "TRN", "GVA"};
    //private final static String[] overseasNodes = {"TCI", "LPA", "ACE", "SPC", "FUE", "SMA", "PDL", "TER"};
    //private final static String[] overseasNodes = {"BJS", "SHA", "HKG", "SIN", "KUL", "SZX", "CAN", "CKG", "DEL", "CMB", "BKK", "HAN", "SEL"};
    //private final static String[] overseasNodes = {"MIL", "TRN", "GVA", "VCE", "LYS", "NCE"};
    //private final static String[] europeanNodes = {"MSQ"};
    //private final static String[] overseasNodes = { "BCN", "GVA", "BOD", "CTA", "PMO", "VCE", "PAR", "ROM", "NCE", "MIL", "LIS", "MAD"};
    //private final static int[] days = {22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
//    private final static String[] outwardDays = {"2403", "2503", "2603", "3103", "0104", "0204", "0704", "0804", "0904", "1404", "1504", "1604"};
  //  private final static String[] returnDays = {"0604", "0704", "0804", "1304", "1404", "1504", "2004", "2104", "2204", "2704", "2804", "2904"};
    private final static LocalDate start = LocalDate.parse("2018-12-21");
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("ddMM");
    private final static List<DayOfWeek> ALLOWED_STARTS = Arrays.asList(DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);
    private final static List<DayOfWeek> ALLOWED_ENDS = Arrays.asList(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY);


    public static void main(String[] args) throws Exception {
        URL local = new URL("http://localhost:9515");
        WebDriver driver = new RemoteWebDriver(local, DesiredCapabilities.chrome());

//        int max = europeanNodes.length * overseasNodes.length * 9;
//        int i = 0;
//        long start = (new Date()).getTime();


                        for (int outwardOffset = 0; outwardOffset < 4; outwardOffset++) {
                            LocalDate out = start.plusDays(outwardOffset);
                            if(!ALLOWED_STARTS.contains(out.getDayOfWeek())) {
                                //continue;
                            }
                            for (int returnOffset = 0; returnOffset < 5; returnOffset++) {

                                LocalDate back = start.plusDays(outwardOffset + 7 + returnOffset);
                                if(!ALLOWED_ENDS.contains(back.getDayOfWeek())) {
                                   // continue;
                                }
                                for (String europeanNode : europeanNodes) {
                                    for (String returnNode : overseasNodes) {


                                        //int outwardDate = days[outwardOffset];
                                //int returnDate = days[outwardOffset + 14 + returnOffset];
                                String outwardDate = FORMATTER.format(out);
                                String returnDate = FORMATTER.format(back);
                                String url = "https://search.aviasales.ru/" + europeanNode + outwardDate + returnNode + returnDate + "1";
                                //String url = "https://search.aviasales.ru/" + europeanNode + outwardDate + "12" + returnNode + "1";//String.format("%02d", returnDate) + "011";
                                new Flight(europeanNode, returnNode, outwardDate, returnDate, getPrice(driver, url));
//                                if (++i % 9 == 0) {
//                                    long minutesSpent = ((new Date()).getTime() - start) / 60_000L;
//                                    long estimatedRemainingMinutes = minutesSpent * (max - i) / i;
//                                    String msg = i + " flights processed in " + minutesSpent + " minutes. Estimated remaining time: " + estimatedRemainingMinutes + " minutes.";
//                                    System.out.println(msg);
//                                }
                            }
                        }
                    }
                }
//        for (String overseasNode : overseasNodes) {
//            for (String europeanNode : europeanNodes) {
//                for (int date = 23; date <= 26; date++) {
//                    String url = "https://search.aviasales.ru/" + europeanNode + date + "12" + overseasNode + "1";
//                    new Flight(europeanNode, overseasNode, date + ".12.2017", "", getPrice(driver, url));
//                }
//            }
//        }
    }

    private static Integer getPrice(WebDriver driver, String url) {
        driver.get(url);

            try {
                (new WebDriverWait(driver, 45)).until((ExpectedCondition<Boolean>) d -> !d.findElements(By.className("--animation-finished")).isEmpty());

                return Integer.parseInt(driver.findElements(By.className("sorting__price-wrap")).get(0)
                        .findElement(By.tagName("span")).getText()
                        .replaceAll("[\u2009]", ""));

        } catch (Exception e1) {
            try {
                return Integer.parseInt(driver.findElements(By.className("sorting__price-wrap")).get(0)
                        .findElement(By.tagName("span")).getText()
                        .replaceAll("[\u2009]", ""));

            } catch (Exception e2) {
                return 0;
            }
        }
    }
}
