import java.net.URL;

import com.google.gdata.client.youtube.YouTubeQuery;
import com.google.gdata.client.youtube.YouTubeService;
import com.google.gdata.data.youtube.VideoEntry;
import com.google.gdata.data.youtube.VideoFeed;
import com.google.gdata.data.youtube.YouTubeMediaGroup;

/** Class to query youtube site
 * @author luca
 *
 */
public class YoutubeConnector {
	
	private YouTubeQuery query;
	private YouTubeService service;
	
	/**Constructor
	 * @throws Exception on creation failure
	 */
	public YoutubeConnector() throws Exception{
		query = new YouTubeQuery(new URL("http://gdata.youtube.com/feeds/api/videos"));
		query.setOrderBy(YouTubeQuery.OrderBy.RELEVANCE);
		query.setSafeSearch(YouTubeQuery.SafeSearch.NONE);
		query.setStrict(true);
		service = new YouTubeService("luca");		
	}
	
	/**Function to get video ID from a artist/title of a track
	 * @param author song author
	 * @param title song title
	 * @return video ID
	 * @throws Exception on query execution error
	 */
	public String getYoutubeVideoID(String author, String title) throws Exception {
		query.setFullTextQuery(author+" "+title);
		VideoFeed videoFeed = service.query(query,VideoFeed.class);
		VideoEntry list= videoFeed.getEntries().get(0);
		YouTubeMediaGroup media = list.getMediaGroup();
		return media.getVideoId();
	}
	
}
