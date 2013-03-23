import java.awt.GridLayout;

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
		super(new GridLayout(1,0));
		JComboBox<SortBy> combo = new JComboBox<>();
		combo.setModel(new DefaultComboBoxModel<>(SortBy.values()));
		combo.setSize(100, 30);
		combo.addActionListener(new SortChangeListener(songTable));
		
		combo.setSelectedIndex(0); //initial
		
		this.add(combo);
	}
	

	
}
