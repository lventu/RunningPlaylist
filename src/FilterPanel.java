import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import types.SortBy;

/** An extension of JPanel that contains a 'Song Filter'
 * @author Luca Venturini
 * @version 1.00
 * 
 */
public class FilterPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public FilterPanel(SongTablePanel songTable) {
		this.setLayout(new FlowLayout(FlowLayout.LEFT,10,5));
		JComboBox<SortBy> combo = new JComboBox<>();
		combo.setModel(new DefaultComboBoxModel<>(SortBy.values()));
		combo.setSize(100, 30);
		SortChangeListener sortChangeListener = new SortChangeListener(songTable);
		combo.addActionListener(sortChangeListener);
		
		combo.setSelectedIndex(0); //initial
		
		this.add(combo);
	}
	

	
}
