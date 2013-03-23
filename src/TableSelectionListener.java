import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import song.SongItem;


/**Listener for row (or column) selection in song table
 * @author Luca Venturini
 *
 */
public class TableSelectionListener implements ListSelectionListener {

	private static JTable table;
	public static List<SongItem> selectedSong;
	
	public List<SongItem> getSelectedSong() {
		return selectedSong;
	}

	public TableSelectionListener(JTable asTable) {
		super();
		table = asTable;
		selectedSong = new ArrayList<SongItem>();
	}

	public static void resetRowSelection(){
		table.getSelectionModel().clearSelection();
		selectedSong.clear();
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting() && e.getSource().equals(table.getSelectionModel()) && table.getRowSelectionAllowed()){
			int[] rowsNum = table.getSelectedRows();
			SongTableModel tableModel = (SongTableModel) table.getModel();
			// Each changing the list is re-builded
			selectedSong.clear();
			for (int idx : rowsNum){
				selectedSong.add(tableModel.getRow(idx));
			}
		}
		
	}

}
