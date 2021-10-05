import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PositionRelated {

	public static void main(String[] args) {

		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://en-gb.facebook.com/");
		
		int xStart = driver.manage().window().getPosition().getX();
		int yStart = driver.manage().window().getPosition().getY();
		
		System.out.println("X: "+xStart+" Y: "+yStart);
		
		Point p=new Point(125, 125);
		driver.manage().window().setPosition(p);
		
		System.out.println(driver.manage().window().getPosition());
		
		driver.manage().window().fullscreen();
		
		driver.quit();

	}

}
