import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import types.DefaultSettings;
import types.SortBy;

public class SortChangeListener implements ActionListener {
	
	private static SortBy sbOldValue = null; // DefaultSettings.DEFAULT_SORTING; 
	private SongTablePanel songTable = null;
	
	public SortChangeListener(SongTablePanel songTable) {
		this.songTable = songTable;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox<SortBy> cb = (JComboBox<SortBy>) e.getSource();
		SortBy sortBy = (SortBy)cb.getSelectedItem();
		if (!sortBy.equals(sbOldValue)) {
			sbOldValue = sortBy;
			songTable.updateTableData(sortBy, DefaultSettings.DEFAULT_PACE);
		}
	}

}