package de.soup.scraper;

public class SoupDAO {

	private static SoupDAO instance;

	private String thisWeek = "";
	
	private String nextWeek = "";

	private SoupDAO() {

	}

	public static SoupDAO getInstance() {
		if (instance == null)
			instance = new SoupDAO();
		return instance;
	}

	public String getThisWeek() {
		return thisWeek;
	}

	public void setThisWeek(String thisWeek) {
		this.thisWeek = thisWeek;
	}

	public String getNextWeek() {
		return nextWeek;
	}

	public void setNextWeek(String nextWeek) {
		this.nextWeek = nextWeek;
	}
}
