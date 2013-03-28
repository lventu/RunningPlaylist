import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/** 
 * @author luca
 *
 */
public class SongTableWindowListener extends WindowAdapter {
	
	public void windowOpened( WindowEvent e ) {
		YTDWrapper.ytdInitializeInvisible("/home/luca/Musica/");
	}
	
	public void windowClosing( WindowEvent e ) {
		YTDWrapper.ytdCloseProgram();
	} 
}
