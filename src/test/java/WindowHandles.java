import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WindowHandles {

	public static void main(String[] args) {


		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.naukri.com/");
		
		Set<String> windowIds = driver.getWindowHandles();
		windowIds.remove(driver.getWindowHandle());
		
		for(String ele: windowIds) {
		driver.switchTo().window(ele);
//		if(driver.getTitle().contains("Ellie MAE")) {
//			driver.manage().window().maximize();
//			break;
//		}
	
	
		}
		
		driver.quit();

	}

}
