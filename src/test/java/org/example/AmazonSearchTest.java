package org.example;

import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.*;

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
    public void testLoginAndVerifySuccess() {
        page.navigate("https://practicetestautomation.com/practice-test-login/");
        page.locator("#username").fill("student");
        page.locator("#password").fill("Password123");
        page.locator("#submit").click();

        // Verify URL contains expected path
        String url = page.url();
        Assert.assertTrue(url.contains("practicetestautomation.com/logged-in-successfully/"), "URL does not contain expected path");

        // Verify page contains expected text
        String pageContent = page.content();
        Assert.assertTrue(pageContent.contains("Congratulations") || pageContent.contains("successfully logged in"), "Expected text not found on page");
    }

    @AfterClass
    public void tearDown() {
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}