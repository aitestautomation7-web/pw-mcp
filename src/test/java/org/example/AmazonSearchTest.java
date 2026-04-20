package org.example;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AmazonSearchTest {
    private Playwright playwright;
    private Browser browser;
    private Page page;

    @BeforeClass
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        page = browser.newPage();
    }

    @Test
    public void searchTShirtsOnAmazon() {
        page.navigate("https://www.amazon.com/");
        page.locator("//input[@placeholder='Search Amazon']").fill("tshirts");
        page.locator("input#nav-search-submit-button").click();
        // Optionally, assert that results are shown
        page.waitForSelector("span.a-color-state.a-text-bold");
    }

    @AfterClass
    public void tearDown() {
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}

