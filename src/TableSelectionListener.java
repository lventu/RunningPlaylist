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

	private JTable table;
	private List<SongItem> selectedSong;
	
	public List<SongItem> getSelectedSong() {
		return selectedSong;
	}

	public TableSelectionListener(JTable table) {
		super();
		this.table = table;
		selectedSong = new ArrayList<SongItem>();
	}

	private void resetRowSelection(){
		selectedSong.clear();
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting() && e.getSource().equals(table.getSelectionModel()) && table.getRowSelectionAllowed()){
			int[] rowsNum = table.getSelectedRows();
			SongTableModel tableModel = (SongTableModel) table.getModel();
			// Each changing the list is re-builded
			resetRowSelection();
			for (int idx : rowsNum){
				selectedSong.add(tableModel.getRow(idx));
			}
		}
		
	}

}
