import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import song.SongItem;

/**
 * Class that implement the click listener for the button
 * 
 * @author luca
 */
public class ClickListener implements ActionListener {

	YoutubeConnector connector;
	private TableSelectionListener selectionListener;

	/**
	 * Constructor
	 * 
	 * @param selectionListener
	 */
	public ClickListener(TableSelectionListener selectionListener) {
		this.selectionListener = selectionListener;
		selectionListener.saveRowSelected();
		try {
			connector = new YoutubeConnector();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent asEvent) {
		JButton button = ((JButton) asEvent.getSource());
		String buttonName = button.getName();
		switch (buttonName) {
		case "OK":
			if (!selectionListener.selectedSong.isEmpty()) {
				List<SongItem> list = selectionListener.selectedSong;
				int choiceReturn = JOptionPane.showConfirmDialog(
						button.getParent(),
						"Conferma scaricamento di " + list.size() + " video",
						"Attenzione", JOptionPane.YES_NO_OPTION);

				switch (choiceReturn) {
				case JOptionPane.OK_OPTION:
					System.out.println("OPERAZIONE CONFERMATA");
					String lsVideoId = null;
					for (SongItem song : list) {
						try {
							lsVideoId = connector.getYoutubeVideoID(
									song.getSongArtist(), song.getSongTitle());
							System.out.println(song.getSongTitle() + "\t\t"
									+ lsVideoId);
							YTDWrapper.ytdAddVideoUrl(lsVideoId);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					selectionListener.freezeTableSelection();

					break;
				case JOptionPane.NO_OPTION:
					System.out.println("OPERAZIONE CANCELLATA");
					break;
				}

			}

			break;
		case "ANNULLA":
			selectionListener.resetRowSelection();
			selectionListener.unfreezeTableSelection();
			System.out.println("Selection Resetted");
			break;
		}
	}
}
