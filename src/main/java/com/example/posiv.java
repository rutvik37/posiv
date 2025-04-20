package com.example;

import java.util.Random;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

public class posiv {

    public static void main(String[] args) {

        SharedData.title = generateRandomAlphaString(6, 9);
        SharedData.message = generateRandomAlphaString(6, 9);
        SharedData.firstname = generateRandomAlphaString(4, 5);
        SharedData.lastname = generateRandomAlphaString(4, 5);
        SharedData.subject = generateRandomAlphaString(6, 9);
        SharedData.description = generateRandomAlphaString(10, 20);
        SharedData.email = generateRandomEmail();

        try (Playwright playwright = Playwright.create()) {

            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            page.navigate("https://admin.posiv.org.uk/#");

            page.getByPlaceholder("Enter Email").fill("admin@posiv.com");
            page.getByPlaceholder("Enter Password").fill("Admin@111");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Log in")).click();

            Thread.sleep(2000); // Wait for login

            dashboard dashboard = new dashboard();
            dashboard.dashboard(page);

            profile profile = new profile();
            profile.profile(page);

            notification notification = new notification();
            notification.notification(page);

            contact_us contact_us = new contact_us();
            contact_us.contact_us(page);

           

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String generateRandomAlphaString(int minLen, int maxLen) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        int length = random.nextInt(maxLen - minLen + 1) + minLen;

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return sb.toString();
    }

    public static String generateRandomEmail() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        Random random = new Random();

        int nameLength = random.nextInt(5) + 5;
        int domainLength = random.nextInt(5) + 3;

        StringBuilder namePart = new StringBuilder();
        for (int i = 0; i < nameLength; i++) {
            namePart.append(alphabet.charAt(random.nextInt(alphabet.length())));
            if (random.nextBoolean())
                namePart.append(digits.charAt(random.nextInt(digits.length())));
        }

        StringBuilder domainPart = new StringBuilder();
        for (int i = 0; i < domainLength; i++) {
            domainPart.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }

        String[] tlds = { "com", "net", "org", "io", "co.uk" };
        String tld = tlds[random.nextInt(tlds.length)];

        return namePart.toString() + "@" + domainPart.toString() + "." + tld;
    }

}
