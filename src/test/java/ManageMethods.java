import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ManageMethods {

	public static void main(String[] args) {

		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://en-gb.facebook.com/");
		
		int height = driver.manage().window().getSize().getHeight();
		int width = driver.manage().window().getSize().getWidth();
		
		System.out.println("Height: "+height+" Width: "+width);
		
		Dimension d=new Dimension(700, 1200);
		driver.manage().window().setSize(d);
		
		int height1 = driver.manage().window().getSize().getHeight();
		int width1 = driver.manage().window().getSize().getWidth();
		
		System.out.println("Height: "+height1+" Width: "+width1);
		
		
		driver.quit();
	}

}
