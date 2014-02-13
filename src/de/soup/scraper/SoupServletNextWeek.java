package de.soup.scraper;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class SoupServletNextWeek extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("application/xml");
		resp.setCharacterEncoding("UTF-8");
		if (SoupDAO.getInstance().getNextWeek().isEmpty()) {
			SoupScraperNextWeek scraper = new SoupScraperNextWeek();
			scraper.cleanHTML();
			scraper.scrape();
		}

		resp.getWriter().print(SoupDAO.getInstance().getNextWeek());
	}

}
