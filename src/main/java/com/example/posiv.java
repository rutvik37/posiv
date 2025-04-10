package com.example;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

public class posiv {


    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {

            
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            page.navigate("https://admin.posiv.org.uk/#");
            
            page.getByPlaceholder("Enter Email").fill("admin@posiv.com");
            page.getByPlaceholder("Enter Password").fill("Admin@111");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Log in")).click();

            Thread.sleep(2000); // Wait for login

            // Dashboard
            dashboard dash = new dashboard();
            dash.dashboard(page);

            // Edit Profile
            edit_profile profile = new edit_profile();
            profile.edit_profile(page);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
