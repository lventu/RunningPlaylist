import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import song.SongItem;


/**Listener for row (or column) selection in song table
 * @author Luca Venturini
 *
 */
public class TableSelectionListener implements ListSelectionListener {

	private JTable table;
	public List<SongItem> selectedSong;
	private int[] oldSelectedRows=null;
	
	public List<SongItem> getSelectedSong() {
		return selectedSong;
	}

	public TableSelectionListener(JTable asTable) {
		super();
		table = asTable;
		selectedSong = new ArrayList<SongItem>();
	}

	public void resetRowSelection(){
		table.getSelectionModel().clearSelection();
		selectedSong.clear();
	}
	
	public void saveRowSelected() {
		oldSelectedRows = table.getSelectedRows();
	}
	
	public void freezeTableSelection() {
		if (oldSelectedRows!=null && oldSelectedRows.length>0) {
			for(int row : oldSelectedRows) {
				table.setRowSelectionInterval(row, row);
			}	
		}
		table.setEnabled(false);
	}
	
	public void unfreezeTableSelection() {
		table.setEnabled(true);
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
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
