package ticketBuyer;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlValidator {
	
	private static final String TICKET_HASH = "#tickets";
	private static final String HOST_NAME = "www.eventbrite.com";
	private static final String EVENTS_PATH = "/e/";
	
	
	public static String addTicketHash(String ticketUrl) {
		return ticketUrl.contains(TICKET_HASH) ? 
				ticketUrl : ticketUrl + TICKET_HASH;
	}
	
	public static boolean isTicketUrl(String ticketUrl) {
		try {
			URL url = new URL(ticketUrl);
			return url.getHost().equals(HOST_NAME)
					&& url.getPath().startsWith(EVENTS_PATH);
		} catch (MalformedURLException e) { }
		return false;
	}

}
