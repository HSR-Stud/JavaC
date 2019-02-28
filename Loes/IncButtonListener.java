import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IncButtonListener implements ActionListener 
{
	private MyAwtWindow mw;
	public IncButtonListener(MyAwtWindow mw) 
	{
		this.mw = mw;
	}
	public void actionPerformed(ActionEvent e) 
	{
		int n = mw.getAnzahl();
		n++;
		mw.setAnzahl(n);
    	String t = (n % 2 == 0) ? "Anzahl = " : "Number = ";
		mw.setLabelText(t + n);
	}
}
