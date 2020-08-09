package flightprotal.StepDefiniation;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.flight.protal.base.BaseClass;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class stepdef extends BaseClass {

	WebElement datePicker;
	List<WebElement> noOfColumns;
	List<WebElement> price;
	List<WebElement> travelduration;
	String date1 = "12";
	String[] arrsplit;
	int lowprice;
    
	@Given("^user navigate flight protal like spicejet.com")
	public void user_navigate_flight_protal_like_spicejet_com() {
		// Write code here that turns the phrase above into concrete actions
		System.out.println("Cucumber executed Given statement");
		try {
			browserinitialization();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@When("Enter the source {string} and destination {string}")

	public void enter_the_source_hyderabad_and_destination_trivandrum(String originstation, String destinationstation)
			throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		driver.findElement(By.xpath("//input[contains(@id,'ctl00_mainContent_ddl_originStation1')]")).clear();
		driver.findElement(By.xpath("//input[contains(@id,'ctl00_mainContent_ddl_originStation1')]"))
				.sendKeys(originstation);

		driver.findElement(By.xpath("//input[contains(@id,'ctl00_mainContent_ddl_destinationStation1')]")).clear();
		driver.findElement(By.xpath("//input[contains(@id,'ctl00_mainContent_ddl_destinationStation1')]"))
				.sendKeys(destinationstation);

		driver.findElement(By.id("ctl00_mainContent_view_date1")).click();

		datePicker = driver.findElement(By.id("ui-datepicker-div"));
		noOfColumns = datePicker.findElements(By.tagName("td"));

		for (WebElement cell : noOfColumns) {
			if (cell.getText().equals(date1)) {
				cell.findElement(By.linkText(date1)).click();
				break;
			}

		}

		driver.findElement(By.xpath("//input[@type='submit'] [@name='ctl00$mainContent$btn_FindFlights']")).click();
		Thread.sleep(3000);
		 
		System.out.println("radio button state-----" +driver.findElement(By.xpath("//input[@type='radio']")).isEnabled());
		driver.findElement(By.xpath("//*[contains(@class,'flightfare')][contains(text() ,'6,283 INR')]//preceding::input[@type='radio'][1]")).click();
		Thread.sleep(5000);
		System.out.println("cleared radio button");
	}
	

	@Then("Select the best itinerary based on the fastest and cheapest travel")
	public void select_the_best_itinerary_based_on_the_fastest_and_cheapest_travel() throws ParseException {
		
		//xpath for get price for spice sever column 
		price = driver.findElements(By.xpath("//table[@id='availabilityTable0']/tbody/tr/td[3]"));
		price.size();
		
		//xpath for get travel duration from  depart column
		travelduration = driver
				.findElements(By.xpath("//table[@id='availabilityTable0']/tbody/tr/td/span[@class='travel-duration']"));
		travelduration.size();
		
		//to store the all the prices and duration values from spicesever and depart column 
		ArrayList<Integer> prices = new ArrayList<Integer>();
		ArrayList<Date> travlduration = new ArrayList<Date>();

		for (WebElement travldurationElement : travelduration) {
			String duration = travldurationElement.getText();
			String str1, str2;
			str1 = duration.substring(0, duration.indexOf('h'));
			str2 = duration.substring(duration.indexOf('h') + 2, duration.indexOf('m'));
			str1 = str1 + ":" + str2;
			DateFormat dateFormat = new SimpleDateFormat("hh:mm");
			Date time = dateFormat.parse((str1));

			travlduration.add(time);
		}
		
		//Store the minium duration time from Depart column
		Date fastduration = Collections.min(travlduration);
		System.out.println("getting minium duration" + fastduration);

		for (WebElement priceElement : price) {
			String priceamount = priceElement.getText();
			arrsplit = priceamount.split(" " + "INR");
			for (String arr : arrsplit) {
				prices.add(Integer.parseInt(arr.replace(",", "")));

			}

		}
		//xpath for get price for spice sever column 
		lowprice = Collections.min(prices);

		for (int i = 0; i < price.size(); i++) {

				for (WebElement priceElement : price) {
					String priceText = priceElement.getText();
					String[] splitprice = priceText.split(" " + "INR");

					for (String intprice : splitprice) {
						int nextpriceval = Integer.parseInt(intprice.replace(",", ""));

						for (WebElement travldurationElement : travelduration) {
							String duration = travldurationElement.getText();
							String str1, str2;
							str1 = duration.substring(0, duration.indexOf('h'));
							str2 = duration.substring(duration.indexOf('h') + 2, duration.indexOf('m'));
							str1 = str1 + ":" + str2;
							DateFormat dateFormat = new SimpleDateFormat("hh:mm");
							Date time = dateFormat.parse((str1));
							

							if (lowprice==nextpriceval && fastduration.equals(time)) {
								/* 000000000000000000000000 */
//								System.out.println("lllllllow------------"+ lowprice);
//								System.out.println("nextprice-------"+nextpriceval);
//								System.out.println("fastduratio----------"+fastduration);
//								System.out.println("Time--------"+time);
								/*8888888888888888888888888*/
								driver.findElement(By.xpath("//*[contains(@class,'flightfare')][contains(text() ,'"
										+ priceElement.getText() + "')]//preceding::input[@type='radio'][1]")).click();

								break;
								
						}
							
					}
				}

			}

		}
	}
}

//}
