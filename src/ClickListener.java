import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;

import song.SongItem;


/** Class that implement the click listener for the button
 * @author luca
 */
public class ClickListener implements ActionListener {
	
	YoutubeConnector connector;
	
	/**
	 * Constructor
	 */
	public ClickListener() {
		try {
			connector = new YoutubeConnector();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent asEvent) {
		String buttonName = ((JButton) asEvent.getSource()).getName();
		switch(buttonName){
		case "OK":
			if (!TableSelectionListener.selectedSong.isEmpty()){
				List<SongItem> list = TableSelectionListener.selectedSong;
				System.out.println("#"+TableSelectionListener.selectedSong.size()+" Songs Selected");
				String asVideoId = null;
				for (SongItem song : list) {
					try {
						asVideoId = connector.getYoutubeVideoID(song.getSongArtist(), song.getSongTitle());
						System.out.println(song.getSongTitle() + "\t\t"+ asVideoId);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			break;
		case "ANNULLA":	
			TableSelectionListener.resetRowSelection();
			System.out.println("Selection Resetted");
			break;
		}
	}

}
