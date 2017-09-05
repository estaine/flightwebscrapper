package com.estaine.main;

import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlightSearcher extends Thread {

    private final static String[] overseasNodes = {"GRU", "BOG", "CGH", "GIG", "BSB", "SCL", "LIM", "CNF", "AEP", "EZE", "UIO", "MDE", "JNB", "CPT", "PEK", "DXB", "HKG", "PVG", "CAN", "SIN", "ICN", "BKK", "DEL", "CGK", "KUL", "CTU", "BOM", "TPE", "SYD", "KMG", "SZX", "MEX", "SHA", "MNL", "DOH"};

    private URL localURL;
    private String europeanNode;

    public FlightSearcher(URL localURL, String europeanNode) {
        this.localURL = localURL;
        this.europeanNode = europeanNode;
    }

//    @Override
//    public void run() {
//        WebDriver driver = new RemoteWebDriver(localURL, DesiredCapabilities.chrome());
//
//        for (int outwardOffset = 23; outwardOffset <= 25; outwardOffset++) {
//            for (int returnOffset = 5; returnOffset <= 7; returnOffset++) {
//                //int outwardDate = days[outwardOffset];
//                //int returnDate = days[outwardOffset + 14 + returnOffset];
//                int outwardDate = outwardOffset;
//                int returnDate = returnOffset;
//
//                for (String returnNode : overseasNodes) {
//
//                    String url = "https://search.aviasales.ru/" + europeanNode + outwardDate + "12" + returnNode + String.format("%02d", returnDate) + "011";
//                    new Flight(europeanNode, returnNode, outwardDate + ".12.2017", returnDate + ".01.2018", getPrice(driver, url));
//                }
//            }
//        }
//    }
//
//    private static Integer getPrice(WebDriver driver, String url) {
//        driver.get(url);
//
//        try {
//            (new WebDriverWait(driver, 45)).until((ExpectedCondition<Boolean>) d -> !d.findElements(By.className("--animation-finished")).isEmpty());
//
//            return Integer.parseInt(driver.findElements(By.className("sorting__price-wrap")).get(0)
//                    .findElement(By.tagName("span")).getText()
//                    .replaceAll("[\u2009]", ""));
//
//        } catch (Exception e1) {
//            try {
//                return Integer.parseInt(driver.findElements(By.className("sorting__price-wrap")).get(0)
//                        .findElement(By.tagName("span")).getText()
//                        .replaceAll("[\u2009]", ""));
//
//            } catch (Exception e2) {
//                return 0;
//            }
//        }
//    }

}
