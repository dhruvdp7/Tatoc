package tatoc_automation;


import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

	public class Tatoc {
		static WebDriver driver;

		public static void main(String[] args) {
			
			System.setProperty("webdriver.chrome.driver","/home/qainfotech/chromedriver");		
			driver=new ChromeDriver();
			driver.get("http://10.0.1.86/tatoc/basic/grid/gate");
			gridGate();
			frameDungeon();
			dragAround();
			popupWindows();
			cookieHandling();
		}
		
		static void gridGate(){
			driver.findElement(By.className("greenbox")).click();
		}
		
		static void frameDungeon(){
			driver.switchTo().frame(0);
			WebElement box1=driver.findElement(By.id("answer"));
			String a=box1.getAttribute("class");
			driver.switchTo().frame(0);
			WebElement box2= driver.findElement(By.id("answer"));;
			String b=box2.getAttribute("class");
			driver.switchTo().parentFrame();
			boolean isClassNotEqual=true;
			while (isClassNotEqual){
				driver.findElement(By.linkText("Repaint Box 2")).click();
				driver.switchTo().frame(0);
				box2=driver.findElement(By.id("answer"));
				b=box2.getAttribute("class");
				driver.switchTo().parentFrame();
				if(a.equals(b)){
					isClassNotEqual=false;
				}
			}
			
			driver.findElement(By.linkText("Proceed")).click();
		}
		
		static void dragAround(){
			WebElement from = driver.findElement(By.id("dragbox"));
			WebElement to = driver.findElement(By.id("dropbox"));
			Actions act=new Actions(driver);
			act.dragAndDrop(from, to).build().perform();
			driver.findElement(By.linkText("Proceed")).click();
		}
		
		static void popupWindows(){
			driver.findElement(By.linkText("Launch Popup Window")).click();
			String MainWindow=driver.getWindowHandle();
			Set<String> s1=driver.getWindowHandles();		
	        Iterator<String> i1=s1.iterator();
	        while(i1.hasNext())			
	        {		
	            String ChildWindow=i1.next();		
	            		
	            if(!MainWindow.equalsIgnoreCase(ChildWindow))			
	            {    		
	                 
	                    driver.switchTo().window(ChildWindow);	                                                                                                           
	                    driver.findElement(By.id("name")).sendKeys("Dhruv Pande");                			
	                    driver.findElement(By.id("submit")).click();				
	            }		
	        }		
	            driver.switchTo().window(MainWindow);
	            driver.findElement(By.linkText("Proceed")).click();
		}
		
		static void cookieHandling(){
			driver.findElement(By.linkText("Generate Token")).click();
			WebElement token=driver.findElement(By.id("token"));
			String a = token.getText();
			String cookie=a.substring(a.indexOf(' '));
			String c=cookie.trim();
			Cookie ck = new Cookie("Token", c);
			driver.manage().addCookie(ck);
			driver.findElement(By.linkText("Proceed")).click();
		}
	}
	

