package types;
/**
 * @author Luca Venturini
 * @version 1.00
 *
 */
public enum SortBy {
	
	HOT("workout-songs"),
	MOST_ADDED("popular-workout-songs"),
	BY_BPM("workout-songs-by-bpm"),
	NEWEST("new-workout-song"),
	BY_NAME("workout-songs-by-name");
	
	private final String text;
	private SortBy(final String text) {
        this.text = text;
    }

	@Override
	public String toString() {
		return text;
	}
	
}
