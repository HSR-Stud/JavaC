import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DecButtonListener implements ActionListener 
{
	private MyAwtWindow mw_;
	public DecButtonListener(MyAwtWindow mw) 
	{
		mw_ = mw;
	}
	public void actionPerformed(ActionEvent e) 
	{
		int n = mw_.getAnzahl();
		mw_.setAnzahl(n - 1);
    	String t = (n % 2 == 0) ? "Anzahl = " : "Number = ";
		mw_.setLabelText(t + n);
	}
}
