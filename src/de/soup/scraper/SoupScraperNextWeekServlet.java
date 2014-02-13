package de.soup.scraper;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class SoupScraperNextWeekServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		SoupScraperNextWeek scraper = new SoupScraperNextWeek();
		scraper.cleanHTML();
		scraper.scrape();
		resp.setContentType("text/html");
		resp.getWriter().println("done.");
	}

	public static void main(String[] args) {
		SoupScraper scraper = new SoupScraper();
		scraper.cleanHTML();
		scraper.scrape();

	}

}
