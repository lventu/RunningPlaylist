import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import song.HtmlAccess;
import song.SongItem;
import types.DefaultSettings;
import types.SortBy;

public class SongTablePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private SongTableModel songTableModel = null;
	JTable table = null;
	 
	public SongTablePanel( ) {
        super(new GridLayout(1,0));
        if (songTableModel==null) {
        	songTableModel = new SongTableModel(retrieveData(DefaultSettings.DEFAULT_SORTING, DefaultSettings.DEFAULT_PACE), SongItem.columnData);
        }
        
        table = new JTable(songTableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setPreferredScrollableViewportSize(new Dimension(500, 500));
        table.setFillsViewportHeight(true);
        //Adjust columns width
        TableColumnAdjuster adjuster = new TableColumnAdjuster(table,3);
        adjuster.setColumnDataIncluded(true);
        //adjuster.setColumnHeaderIncluded(true);
        adjuster.setDynamicAdjustment(true);
        adjuster.adjustColumns();
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
	}
	
	private List<SongItem> retrieveData(SortBy sort, String pace) {
		int currentPage = 1;
		HtmlAccess obj = new HtmlAccess(sort, pace, DefaultSettings.DEFAULT_UNIT);
		List<SongItem> coll = new ArrayList<SongItem>();
		List<SongItem> tempData;
		for(currentPage=1;currentPage<=1;currentPage++) {	
			// goto the page
			obj.goToPageNumber(currentPage);
			// get the song listed
			tempData = obj.getSongs();
			coll.addAll(tempData);
		}
		return coll;
	}
	
	public void updateTableData(SortBy sort, String pace) {
		if (songTableModel!=null) {
			songTableModel.clearRows();
			List<SongItem> rows = retrieveData(sort,pace);
			songTableModel.setRows(rows);
			table.repaint();
		}
	}
}
