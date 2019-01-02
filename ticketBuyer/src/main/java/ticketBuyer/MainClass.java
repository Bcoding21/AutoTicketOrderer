package ticketBuyer;

public class MainClass {
	
	private static final String CHROME_DRIVER_PATH = "C:\\Users\\Brandon\\Downloads\\chromedriver_win32\\chromedriver.exe";
	private static final String TICKET_URL = "https://www.eventbrite.com/e/alan-walker-w-sam-feldt-tickets-53596210709?aff=ebdshpmoodssection";
	static {
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
	}
	
	public static void main(String[] args) {
		
		if ( !UrlValidator.isTicketUrl(TICKET_URL) ) {
			System.out.println("Brandon");
		}
		else {
			String ticketUrl = UrlValidator.addTicketHash(TICKET_URL);
			TicketOrderer orderer = new TicketOrderer(ticketUrl);
			System.out.println(orderer.getCheapestTicketId());
			
		}
	}
	
}
