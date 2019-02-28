// Anonyme Klasse (1) vs. Lamda-Ausdruck (2) 
(1) incButton.addActionListener 
(   new ActionListener() 
{  public void actionPerformed(ActionEvent event) {  
	label.setText("Anz = " + ++anzahl); } } ); 
(2) decButton.addActionListener (   
	event -> label.setText("Anz = " + --anzahl) );