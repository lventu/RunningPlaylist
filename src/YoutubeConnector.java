import java.net.URL;

import com.google.gdata.client.youtube.YouTubeQuery;
import com.google.gdata.client.youtube.YouTubeService;
import com.google.gdata.data.youtube.VideoEntry;
import com.google.gdata.data.youtube.VideoFeed;
import com.google.gdata.data.youtube.YouTubeMediaGroup;

/**
 * @author luca
 *
 */
public class YoutubeConnector {
	
	public YoutubeConnector() throws Exception{
		
	}
	
	public String getYoutubeVideoID(String author, String title) throws Exception {
		YouTubeQuery query = new YouTubeQuery(new URL("http://gdata.youtube.com/feeds/api/videos"));
		query.setOrderBy(YouTubeQuery.OrderBy.RELEVANCE);
		query.setSafeSearch(YouTubeQuery.SafeSearch.NONE);
		query.setFullTextQuery(author+" "+title);
		YouTubeService service = new YouTubeService("luca");
		VideoFeed videoFeed = service.query(query,VideoFeed.class);
		VideoEntry list= videoFeed.getEntries().get(0);
		YouTubeMediaGroup media = list.getMediaGroup();
		System.out.println(title + "-->\t"+ media.getVideoId());
		return media.getVideoId();
	}
	
}
