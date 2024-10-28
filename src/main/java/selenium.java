import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.Arrays;
import java.util.List;
public class selenium {
    public static final int ARTISTNUM = 5;
    public static String[][] seleniumRun(String[] ArtistData){
        System.out.println("a");
        System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.chosic.com/music-genre-finder/");

        //setting search to artist
        driver.findElement(By.id("suggestion-options")).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(By.id("suggestion-options")).sendKeys(Keys.ENTER);

        String[][] rankGenres = new String[ARTISTNUM][];

        for (int i = 0; i < ARTISTNUM; i++) {
            //interacting and searching the artist
            try {
                Thread.sleep(100);
                driver.findElement(By.id("search-word")).sendKeys(ArtistData[i]);
                Thread.sleep(2500);
                driver.findElement(By.id("hh1")).click();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            //finding number of genres
            WebElement loopFindDiv = driver.findElement(By.xpath("//*[@id=\'spotify-tags\']/div/div[2]"));
            List<WebElement> loopFindA = loopFindDiv.findElements(By.tagName("a"));
            System.out.println(loopFindA.size());
            int genreNum = loopFindA.size();

            //finding genre names and storing in array
            String[] justWord = new String[genreNum];
            WebElement temp = null;
            for (int k = 1; k <= genreNum; k++) {
                temp = driver.findElement(By.xpath("//*[@id=\'spotify-tags\']/div/div[2]/a[" + k + "]"));
                justWord[k - 1] = temp.getText();
            }
            System.out.println(Arrays.toString(justWord));
            rankGenres[i] = justWord;
        }
        driver.quit();
        return rankGenres;
    }
}
