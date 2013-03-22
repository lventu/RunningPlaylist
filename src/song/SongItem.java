package song;
/** Song Object
 * @author Luca Venturini
 * @version 1.00
 *
 */
public class SongItem {
	
	private String songTitle;
	private String songArtist;
	private String songGenere;
	private int songBPM;
	private String songTargetPace;
	
	public static final String[] columnData = {"ARTISTA","TITOLO","GENERE","BPM","TARGET PACE"};
	
	/**
	 * Empty Constructor
	 */
	public SongItem() {
		
	}
	
	/** Song Constructor
	 * @param artist
	 * @param title
	 * @param genere
	 * @param bpm
	 * @param songTargetPace
	 */
	public SongItem(String artist, String title, String genere, int bpm, String songTargetPace) {
		this.songArtist = artist;
		this.songTitle = title;
		this.songGenere = genere;
		this.songBPM = bpm;
		this.songTargetPace = songTargetPace;
	}
	
	// Getters / Setters
	public String getSongTitle() {
		return songTitle;
	}
	public void setSongTitle(String songTitle) {
		this.songTitle = songTitle;
	}
	public String getSongArtist() {
		return songArtist;
	}
	public void setSongArtist(String songArtist) {
		this.songArtist = songArtist;
	}
	public String getSongGenere() {
		return songGenere;
	}
	public void setSongGenere(String songGenere) {
		this.songGenere = songGenere;
	}
	public int getSongBPM() {
		return songBPM;
	}
	public void setSongBPM(int songBPM) {
		this.songBPM = songBPM;
	}
	public String getSongTargetPace() {
		return songTargetPace;
	}
	public void setSongTargetPace(String songTargetPace) {
		this.songTargetPace = songTargetPace;
	}

	/** Convert Function
	 * @return an object that contains the data
	 */
	public static Object[] toObjectArray(SongItem song) {
		return new Object[]{song.getSongArtist(),song.getSongTitle(),song.getSongGenere(),song.getSongBPM(),song.getSongTargetPace()};
	}
}
