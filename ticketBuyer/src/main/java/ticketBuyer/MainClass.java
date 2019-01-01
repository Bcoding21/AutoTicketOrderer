package ticketBuyer;

import java.net.MalformedURLException;
import java.net.URL;

public class MainClass {
	
	private static final String TICKET_HASH = "#tickets";
	private static final String EVENTBRITE_DOMAIN = "www.eventbrite.com";
	private static final String EVENTBRITE_TICKET_PATH = "/e";
	

	public static void main(String[] args) {
		
		String ticketUrl = "https://www.eventbrite.com/e/katsucon-2019-online-registration-registration-43333913903?aff=ebdshpmoodssection"
		
		if (!isTicketUrl(ticketUrl)) {
			System.out.println("Bad ticket format");
			System.exit(0);
		}
		
		ticketUrl = addTicketHash(ticketUrl);
		
		
		

	}
	
	
	private static String addTicketHash(String ticketUrl) {
		return ticketUrl.contains(TICKET_HASH) ? 
				ticketUrl : ticketUrl + TICKET_HASH;
	}
	
	private static boolean isTicketUrl(String ticketUrl) {
		try {
			URL url = new URL(ticketUrl);
			return url.getHost().equals(EVENTBRITE_DOMAIN)
					&& url.getPath().equals(EVENTBRITE_TICKET_PATH);
		} catch (MalformedURLException e) { }
		return false;
	}


}
