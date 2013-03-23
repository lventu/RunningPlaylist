import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class ClickListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent asEvent) {
		String buttonName = ((JButton) asEvent.getSource()).getName();
		switch(buttonName){
		case "OK":
			if (!TableSelectionListener.selectedSong.isEmpty()){
				System.out.println("#"+TableSelectionListener.selectedSong.size()+" Songs Selected");
			}
			break;
		case "ANNULLA":	
			TableSelectionListener.resetRowSelection();
			System.out.println("Selection Resetted");
			break;
		}
	}

}
