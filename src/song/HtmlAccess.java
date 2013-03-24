package song;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import types.SortBy;
import types.UnitSystem;
import utility.NObject;


/** Song Items Class
 * @author Luca Venturini
 * @version 1.00
 */
public class HtmlAccess extends NObject{
	
	private static final String WEB_URL = "http://jog.fm/";
	private Document docPage = null;
	private Integer latestPageNumber = Integer.valueOf(0);
	private SortBy sortBy;
	private int currentPage = 1;
	private String paceQuery = "";
	
	/** Getter for last page
	 * @return An integer representing last page number
	 */
	public int getLatestPageNumber() {
		if(docPage==null) {
			goToPageNumber(currentPage);
		}
		latestPageNumber = Integer.parseInt(docPage.getElementById("songs").getElementsByAttributeValue("class", "page").last().text());
		return latestPageNumber.intValue();
	}
	
	/** Create access to the document (from static URL)
	 *  Handles the document creation IOException
	 * @param sortBy type Enumeration to sort songs
	 * @param pageNumber 
	 * 
	 */
	
	public HtmlAccess(String sortString) {
		this(SortBy.valueOf(sortString),null,null);
	}
	
	public HtmlAccess(SortBy sortBy) {
		this(sortBy,null,null);
	}
	
	public HtmlAccess(SortBy sortBy, String pace, UnitSystem unit) {
		this.sortBy = sortBy;
		if(pace!=null && unit!=null) {
			paceQuery="&pace="+pace+"&unit_system="+unit.toString();
		}else {
			paceQuery="";
		}
	}
	
	/**Function to navigate to the page number
	 * @param pageNumber the destination page
	 */
	public void goToPageNumber(int pageNumber) {
		this.currentPage  = pageNumber;
		try {
			// Setup a connection
			Connection conn = Jsoup.connect(WEB_URL+sortBy.toString()+"?page="+pageNumber+paceQuery);
			conn.timeout(10000); // timeout value
			// get the document from the connection
			docPage	= conn.get();
		} catch (IOException e) {
			// Error Catching
			e.printStackTrace();
		}
	}
	
	/** Function that get songs from a page
	 * @return Collection<SongItem> A collection of SongItem objects
	 */
	public List<SongItem> getSongs() {
		final String METHODNAME = "getSongs";
		this.fpLog(METHODNAME, INIZIO);
		int liCurrentPos = 0, songBpm;
		String songTargetPace = "";
		List<SongItem> songs = new ArrayList<SongItem>();
		SongItem song;
		//Take the songs informations
		Elements songsInfo =  docPage.getElementById("songs").getElementsByAttributeValue("class", "info");
		for (Element songInfo: songsInfo) {
			// Elements with class=middle in songItem; the first contain the target pace, the second the BPM
			Elements paceAndBpm = songInfo.parent().getElementsByAttributeValue("class", "middle");
			songTargetPace = paceAndBpm.get(1).text();
			songBpm = Integer.valueOf(paceAndBpm.get(0).text()).intValue();
			// Get all artists in top class
			Elements songDetails = songInfo.getElementsByAttributeValue("class", "top");
			liCurrentPos = 0;
			for (Element dett : songDetails) {
				// Get all titles in title class
				String lsTitle = songInfo.getElementsByAttributeValue("class", "title").get(liCurrentPos).text();
				song = new SongItem();
				// #artists == #titles
				song.setSongArtist(dett.text());
				song.setSongTitle(lsTitle);
				song.setSongBPM(songBpm);
				song.setSongTargetPace(songTargetPace);
				// add the element to the Collection
				songs.add(song);
				liCurrentPos++;
			}
		}
		this.fpLog(METHODNAME, FINE);
		return songs;
	}
}
