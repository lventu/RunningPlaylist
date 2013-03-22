import java.util.List;

import javax.swing.table.AbstractTableModel;

import song.SongItem;


/** Table Model to visualize song items
 * @author Luca Venturini
 * @version 1.00
 */
public class SongTableModel extends AbstractTableModel {

	private String[] column;
	private List<SongItem> rows;
	private static final long serialVersionUID = 1L;
	
	public void setRows(List<SongItem> rows) {
		this.rows = rows;
	}

	public void clearRows() {
		this.rows.clear();
	}

	public SongTableModel(List<SongItem> dataRows, String[] columns) {
		this.rows = dataRows;
		this.column = columns;
	}
	
	@Override
	public int getRowCount() {
		return rows.size();
	}

	@Override
	public int getColumnCount() {
		return column.length;
	}

	@Override
	public String getColumnName(int col) {
        return column[col];
    }
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return SongItem.toObjectArray(rows.get(rowIndex))[columnIndex];
	}
	
}
