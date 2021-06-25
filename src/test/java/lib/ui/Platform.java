package lib.ui;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class Platform {
    private static final String PLATFORM_ANDROID = "android";
    private static String PLATFORM_MOBILE_WEB = "mobile_web";
    private static String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";

    private static Platform instance;

    private Platform() {}

    public static Platform getInstance()
    {
        if (instance == null) {
            instance = new Platform();
        }
        return instance;
    }

    public RemoteWebDriver getDriver() throws Exception
    {
        URL URL = new URL(APPIUM_URL);
        if (this.isAndroid()) {
            return new AndroidDriver(URL, this.getAndroidDesiredCapabilities());
        } else {
            throw new Exception("Cannot detect type of the Driver. Platform value: " + this.getPlatform());
        }
    }

    public boolean isAndroid() { return isPlatform(PLATFORM_ANDROID); }
    public boolean isMW() { return isPlatform(PLATFORM_MOBILE_WEB); }

    private DesiredCapabilities getAndroidDesiredCapabilities()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("app", "/Users/akravtsov/Desktop/AutomationMobileApp/apks/org.wikipedia.apk");
        capabilities.setCapability("orientation", "PORTRAIT");

        return capabilities;
    }

    private boolean isPlatform(String my_platform)
    {
        String platform = this.getPlatform();
        return my_platform.equals(platform);
    }

    private String getPlatform() {
        return System.getenv("PLATFORM");
    }
}
