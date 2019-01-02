package ticketBuyer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.lang.model.element.Element;
import javax.lang.model.util.Elements;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class TicketOrderer {

	private static final String REGISTER_URL = "https://www.eventbrite.com/register";
	private static final String ORDER_START_URL = "https://www.eventbrite.com/orderstart";
	private static final String numTickets = "1";
	private static final String TICKET_BOX_ELEMENT = "ticket-box__header";
	private static final String PRIMARY_TICKET_BOX_ELEMENT = "ticket-box__primary";
	private static final String SECONDARY_TICKET_BOX_ELEMENT = "ticket-box__secondary";
	private static final String TICKET_INFO_ELEMENT = "ticket-box__info";
	private static final String TICKET_LABEL_ELEMENT = "is-hidden-accessible";
	private static final String TICKET_ID_ATTRIBUTE = "for";
	private static Pattern pattern = Pattern.compile("\\d+\\.\\d+");
	private static WebDriver driver;
	
	public TicketOrderer(String ticketUrl) {
		driver = new ChromeDriver();
		driver.get(ticketUrl);;
	}
	
	public String orderTicket(String ticketUrl) {
		String registerUrl = createOrder(ticketUrl);
		String result = registerOrder(registerUrl);
		return "";
	}

	
	private String createOrder(String ticketUrl) {
		List<NameValuePair> orderData = getOrderData(ticketUrl);
		String orderUrl = getOrderUrl(orderData); 
		return getRegisterUrl(orderUrl);
	}
	
	private List<NameValuePair> getOrderData(String ticketUrl) {
		List<NameValuePair> formData = new ArrayList<>();
		formData.add(getEid());
		formData.add(getHasJavascript());
		formData.add(getSourceId());
		formData.add(getPaymentType());
		return formData;
	}
	
	private NameValuePair getPaymentType() {
		// TODO Auto-generated method stub
		return null;
	}

	private NameValuePair getSourceId() {
		// TODO Auto-generated method stub
		return null;
	}

	private NameValuePair getHasJavascript() {
		// TODO Auto-generated method stub
		return null;
	}

	private NameValuePair getEid() {
		
		return null;
	}
	
	
	public String getCheapestTicketId() {
		Map<String, Double> ticketOptions = getTicketOptions();
		return ticketOptions.entrySet().stream()
				.min(Comparator.comparingDouble(Map.Entry::getValue))
				.get().getKey();
	}

	private Map<String, Double> getTicketOptions() {
		return driver.findElements(By.className(TICKET_BOX_ELEMENT))
				.stream().filter(this::isTicketAvailable)
				.collect(Collectors.toMap(this::getTicket, this::getTicketPrice));
	}
	
	private boolean isTicketAvailable(WebElement element) {
		List<WebElement> elements = element.findElement(By.className(SECONDARY_TICKET_BOX_ELEMENT))
				.findElements(By.className(TICKET_LABEL_ELEMENT));
		return !elements.isEmpty();
	}
	
	private double getTicketPrice(WebElement element) {
		String elementText = element.findElement(By.className(PRIMARY_TICKET_BOX_ELEMENT))
				.findElement(By.className(TICKET_INFO_ELEMENT))
				.getText();
		return parseTicketPrice(elementText);
	}
	
	private double parseTicketPrice(String elementText) {
		Matcher matcher = pattern.matcher(elementText);
		if (matcher.find()) {
			String price = matcher.group();
			return Double.parseDouble(price);
		}
		return Integer.MAX_VALUE;
	}

	private String getTicket(WebElement element) {
		return element.findElement(By.className(SECONDARY_TICKET_BOX_ELEMENT))
				.findElement(By.className(TICKET_LABEL_ELEMENT))
				.getAttribute(TICKET_ID_ATTRIBUTE);
	}

	private String getOrderUrl(List<NameValuePair> orderData) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String getRegisterUrl(String orderUrl) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String registerOrder(String registerUrl) {
		// TODO Auto-generated method stub
		return null;
	}

}
