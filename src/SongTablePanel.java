import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import song.HtmlAccess;
import song.SongItem;
import types.DefaultSettings;
import types.SortBy;

/** An extension of JPanel that contains a customized JTable
 * @author Luca Venturini
 * @version 1.00
 * 
 */
public class SongTablePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private SongTableModel songTableModel = null;
	private TableSelectionListener selectionListener;
	private JTable table = null;

	public SongTablePanel( ) {
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setPreferredSize(new Dimension(540,400));
        
        // listener for focus event
        if (songTableModel==null) {
        	songTableModel = new SongTableModel(SongItem.columnData);
        }
        table = new JTable(songTableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setPreferredScrollableViewportSize(new Dimension(500, 300));
        table.setFillsViewportHeight(true);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);
        table.setFocusable(true);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        // Adding selections listener
        selectionListener = new TableSelectionListener(table);
        table.getSelectionModel().addListSelectionListener(selectionListener); // row selection
//        table.getColumnModel().getSelectionModel().addListSelectionListener(selectionListener); //column selection
        //Adjust columns width
        TableColumnAdjuster adjuster = new TableColumnAdjuster(table,3);
        adjuster.setColumnDataIncluded(true);
        //adjuster.setColumnHeaderIncluded(true);
        adjuster.setDynamicAdjustment(true);
        adjuster.adjustColumns();
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        
        ClickListener clickListener = new ClickListener(selectionListener);
        // Download Button
        JButton btnOk = new JButton("Scarica");
        btnOk.setName("OK");
        btnOk.setOpaque(true);
        btnOk.setVisible(true);
        btnOk.addActionListener(clickListener);
        
        // Clear Selection Button
        JButton btnUndo = new JButton("Annulla Selezione");
        btnUndo.setName("ANNULLA");
        btnUndo.setOpaque(true);
        btnUndo.setVisible(true);
        btnUndo.addActionListener(clickListener);
        
        //Add the scroll pane to this panel.
        add(scrollPane);
        // Adds the buttons to this panel.
        add(btnUndo);
        add(btnOk);
	}
	
	/** Data Retrieve Function
	 * @param sort
	 * @param pace
	 * @return
	 */
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
	
	public void resetTableState() {
		if (table!=null) {
			table.getSelectionModel().clearSelection();
			table.setEnabled(true);
		}
	}
	
	/** Function to update the table
	 * @param sort PageSorting Method
	 * @param pace Target Pace
	*/ 
	public void updateTableData(SortBy sort, String pace) {
		if (songTableModel!=null) {
			List<SongItem> rows = retrieveData(sort,pace);
			songTableModel.setDataObj(rows);
			table.addNotify(); // table refresh
		}
	}
}
