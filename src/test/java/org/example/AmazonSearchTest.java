package org.example;

import com.microsoft.playwright.*;
import org.testng.annotations.*;

public class AmazonSearchTest {

    Playwright playwright;
    Browser browser;
    Page page;

    @BeforeClass
    public void setup() {
        playwright = Playwright.create();

        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(true)
        );

        page = browser.newPage();
    }

    @Test
    public void searchTshirts() {
        // Navigate to Amazon
        page.navigate("https://www.amazon.in");
        page.waitForLoadState();

        page.getByPlaceholder("Search Amazon.in").fill("tshirts");
        page.keyboard().press("Enter");

        page.waitForSelector("div.s-main-slot");

        // Validate title
        String title = page.title();
        System.out.println("Page Title: " + title);

        assert title.toLowerCase().contains("tshirts");
    }

    @AfterClass
    public void tearDown() {
        browser.close();
        playwright.close();
    }
}