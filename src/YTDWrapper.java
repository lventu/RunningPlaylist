import zsk.JFCMainClient;

/**
 * 
 */

/**Wrapper for the YTD2 App
 * @author luca
 * @version 1.00
 */
public final class YTDWrapper {

	private static boolean ibInitialized = false;
//	private static final boolean ibGraphicMode = true;
	
	public static void ytdInitializeInvisible(String downloadDirectory) {
//		JFCMainClient.fpInit(new String[0], ibGraphicMode, downloadDirectory);
		JFCMainClient.fpInit(downloadDirectory);
		ibInitialized = true;
	}
	
	public static void ytdAddVideoUrl(String videoId) {
		if (ibInitialized) {
			JFCMainClient.addYTURLToList("http://www.youtube.com/watch?v="+videoId);
		}
	}
	
	public static void ytdCloseProgram() {
		if (ibInitialized) {
			JFCMainClient.shutdownAppl();
		}
		ibInitialized = false;
	}
	
}
