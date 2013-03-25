import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;

import song.SongItem;


public class ClickListener implements ActionListener {

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
						YoutubeConnector a = new YoutubeConnector();
						asVideoId = a.getYoutubeVideoID(song.getSongArtist(), song.getSongTitle());
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
