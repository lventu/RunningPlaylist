import java.util.ArrayList;
import java.util.Arrays;
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
	public void setColumn(String[] column) {
		this.column = column;
	}
	
	private void clearRows() {
		this.rows.clear();
	}
	
	/**
	 * @deprecated
	 * Use dynamic table updating method
	 */
	public SongTableModel(List<SongItem> dataRows, String[] columns) {
		this.rows = dataRows;
		this.column = columns;
	}

	public SongTableModel(String[] column) {
		this.rows = new ArrayList<SongItem>();
		this.column = column;
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
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		SongItem.toObjectArray(rows.get(rowIndex))[columnIndex] = aValue;
	}
	
	public Object getValueAt(int rowIndex, String columnName) {
		int colPos = Arrays.binarySearch(column, columnName);
		return SongItem.toObjectArray(rows.get(rowIndex))[colPos];
	}
	
	public SongItem getRow(int index){
		return rows.get(index);
	}
	
	public void setDataObj(List<SongItem> list) {
		clearRows();
		setRows(list);
		fireTableDataChanged();
	}
	
	@Override
	public void fireTableDataChanged() {
		// update the data
	}

}
