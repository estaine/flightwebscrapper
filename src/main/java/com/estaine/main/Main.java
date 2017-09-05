package com.estaine.main;

import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main {

    private final static String[] europeanNodes = {"MOW", "MAD", "BCN", "LIS", "CDG", "IST"};//, "FRA", "MUC", "AMS", "FCO", "CPH", "ARL", "BRU", "TXL", "MIL"};
    //private final static String[] transitNodes = {"BJS", "SHA", "HKG", "SIN", "KUL", "SZX", "CAN", "CKG", "DEL", "CMB", "BKK", "HAN", "SEL"};
    private final static String[] overseasNodes = {"MEX", "CUN", "BOG", "PTY", "SJO", "PUJ", "HAV", "SDQ"};//, "GRU", "BSB", "SCL", "LIM", "AEP", "EZE", "UIO", "MDE", "JNB", "CPT", "PEK", "DXB", "HKG", "PVG", "CAN", "SIN", "ICN", "BKK", "DEL", "CGK", "KUL", "CTU", "BOM", "TPE", "SYD", "KMG", "SZX", "SHA", "MNL", "DOH"};
    //private final static int[] days = {22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};


    public static void main(String[] args) throws Exception {
        URL local = new URL("http://localhost:9515");
        WebDriver driver = new RemoteWebDriver(local, DesiredCapabilities.chrome());

        for (String returnNode : overseasNodes) {
            for (String europeanNode : europeanNodes) {

                for (int outwardOffset = 23; outwardOffset <= 25; outwardOffset++) {
                    for (int returnOffset = 5; returnOffset <= 7; returnOffset++) {
                        //int outwardDate = days[outwardOffset];
                        //int returnDate = days[outwardOffset + 14 + returnOffset];
                        int outwardDate = outwardOffset;
                        int returnDate = returnOffset;
                        String url = "https://search.aviasales.ru/" + europeanNode + outwardDate + "12" + returnNode + String.format("%02d", returnDate) + "011";
                        new Flight(europeanNode, returnNode, outwardDate + ".12.2017", returnDate + ".01.2018", getPrice(driver, url));
                    }
                }
            }
        }
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
