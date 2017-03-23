# hw1

//Abigail Perry 

import java.awt.*; //container
import java.awt.event.*; //windowAdapter
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DesktopFrame extends JFrame{

	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu quitMenu;
	private JMenuItem newFrame; 
	private JDesktopPane desktop;
	private JRadioButton redButton;
	private JRadioButton greenButton;
	private JRadioButton blueButton;
	private Color greenColor, redColor, blueColor; 
	private ActionListener listener;
	private ButtonGroup radio; 
	private int x =0, y =0; 

	public DesktopFrame ()
	{
	
		ImageIcon icon = new ImageIcon();
		menuBar = new JMenuBar(); //create menu bar  
	
		setJMenuBar(menuBar);

		//build first File menu
		fileMenu = new JMenu("Create"); //create Create menu option
		fileMenu.setMnemonic( 'c' ); //set mnemonic to c 
		
		JMenuItem picItem = new JMenuItem("Picture Frame"); 
		picItem.setMnemonic ( 'p' ); //set picture fram mnemonic to p
		fileMenu.add( picItem ); //add pic item to create menu 
		menuBar.add(fileMenu); //add Create to menu bar 
		setJMenuBar(menuBar); 
		
		picItem.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{
					JInternalFrame frame = new JInternalFrame(
					"Picture Frame", true, true, true, true); 
					
					MyPanel p = new MyPanel(); //create new panel 
					frame.add(p, BorderLayout.CENTER); 
					frame.pack(); 
				
					desktop.add(frame); 
					frame.setVisible(true); 
				}
			}
		); 
		
		
		JMenuItem changePic = new JMenuItem("Changeable Picture Frame"); 
		changePic.setMnemonic ( 'c' ); //set changeable picture fram mnemonic to c
		fileMenu.add( changePic ); //add changeable pic item to create menu 
		
		changePic.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{
					JInternalFrame frame = new JInternalFrame(
					"Change Picture Frame", true, true, true, true); 
					JPanel p2 = new JPanel(); 
				
					MyPanel2 p = new MyPanel2();  //create new panel 
					frame.add(p2, BorderLayout.NORTH); 
					frame.add(p, BorderLayout.CENTER); 
					frame.pack(); 
				
					
				JCheckBox checkbox = new JCheckBox("Move on Drag"); 
			
				
				p2.add(checkbox); 
				checkbox.setSelected(false); 
				
				checkbox.addItemListener(new ItemListener(){
					public void itemStateChanged(ItemEvent e){
						if(e.getStateChange() == ItemEvent.SELECTED){ //check box has been selected 
							frame.addMouseMotionListener(new MouseMotionAdapter(){
					public void mouseDragged(MouseEvent mouseEvent){
							x = mouseEvent.getX(); 
							y = mouseEvent.getY();
						frame.setContentPane(new movePicture(x, y));
						frame.add(checkbox); 
						}
					});
				  }
				} 
			});
			
				//setLayout( new FlowLayout() ); //set frame layout
				//setVisible(true); 
		
				greenButton = new JRadioButton("Green", true); 
				redButton = new JRadioButton("Red", true); 
				blueButton = new JRadioButton("Blue", true);
				
				radio = new ButtonGroup(); //create button group 
				radio.add(greenButton); 
				radio.add(redButton);
				radio.add(blueButton); 
				
				greenButton.addActionListener(this); 
				redButton.addActionListener(this); 
				blueButton.addActionListener(this); 
				
				p2.add(greenButton); 
				p2.add(redButton); 
				p2.add(blueButton); 
				
				desktop.add(frame); 
				frame.setVisible(true); 
				
				//create new color objects
				greenColor = new Color(0, 255, 0); 
				redColor = new Color(255, 0, 0); 
				blueColor = new Color(0, 0, 255); 
				
				greenButton.addItemListener(
					new RadioButtonHandler(greenColor)); 
				redButton.addItemListener(
					new RadioButtonHandler(redColor)); 
				blueButton.addItemListener(
					new RadioButtonHandler(blueColor));
				}	
		
			}
		);
	
		quitMenu = new JMenu("Quit"); 
		quitMenu.setMnemonic( 'q' ); //set mnemonic to q for quit 
		JMenuItem exitItem = new JMenuItem("Exit Program", KeyEvent.VK_X); //single quit menu item 
		//exitItem.setAccelorator(KeyStroke.getKeyStroke("X")); 
		
		//closes program
		exitItem.setMnemonic( 'x' ); 
		//exitItem.setAccelorator( KeyStroke.getKeyStroke("ctrl X")); 
		quitMenu.add( exitItem ); //add pic item to create menu 
		exitItem.addActionListener(new exitApp()); 
		
		menuBar.add(quitMenu); //add Create to menu bar 
		setJMenuBar(menuBar); 
		
		desktop = new JDesktopPane(); //create desktop pane
		add( desktop ); //add desktop pane to frame	
		
	}
	
	static class drawPicture extends JPanel{
			public drawPicture(){
        }
        @Override
        public void paintComponent(Graphics g){
            //get the width/height of the oval and the x/y coordinates of the oval
            int width = (int)(this.getWidth() * 0.3);
            int height = (int)(this.getHeight() * 0.3);
            int x = (this.getWidth() - width)/2;
            int y = (this.getHeight() - height)/2;
            //call parent function
            super.paintComponent(g);
            //paint background and then set the color to green for the oval
            setBackground(Color.WHITE);
            g.setColor(Color.GREEN);
            g.drawOval(x, y, width, height);
            g.fillOval(x, y, width, height);
        }
    }
	
	static class movePicture extends JPanel{
        int x, y;
        
		public movePicture(int picture_x, int picture_y){
            x = picture_x;
            y = picture_y;
        }
        @Override
        public void paintComponent(Graphics g){
            //get the width/height of the oval
            int width = (int)(this.getWidth() * 0.3);
            int height = (int)(this.getHeight() * 0.3);
            //call parent function
            super.paintComponent(g);
            //paint background and then set the color to green for the oval
            setBackground(Color.WHITE);
            g.setColor(Color.GREEN);
            g.drawOval(x, y, width, height);
            g.fillOval(x, y, width, height);
        }
    }
	
	public class RadioButtonHandler implements ItemListener 
	 {
      private Color color; // color associated with this listener
		public RadioButtonHandler( Color c )
      {
         color = c; // set the color of this listener
      } // end constructor RadioButtonHandler
           
      // handle radio button events
      public void itemStateChanged( ItemEvent event )
      {
         setBackground( color );  // set color
      } // end method itemStateChanged
	 } // end private inner class RadioButtonHandler
	 
		
		static class exitApp implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0); 
			}
		}
		
		static class MyPanel extends JPanel{
			private ImageIcon picture; //image to be displayed

			public MyPanel()
			{
				picture = new ImageIcon(); 
				
			}
			
			public void paintComponent( Graphics g)
			{	//not hard coded dimension for circle
				int width =(int)(this.getWidth() - (this.getWidth() *0.3)); 
				int height =(int)(this.getHeight() - (this.getHeight() *0.9));
				
				//not hard coded dimensions for rect
				int width2 =(int)(this.getWidth() *0); 
				int height2 =(int)(this.getHeight() *.50);
				
				super.paintComponent(g); 
				picture.paintIcon(this, g, 0, 0); 
				setBackground(Color.BLUE); 
				
				g.setColor(Color.YELLOW);
				g.fillArc(width, height, (int)(this.getWidth() * .25), (int)(this.getHeight() * .25), 0, 360); 
				//g.fillArc(520, 60,130, 130 ,0, 360);  //25, 25, 60, 10 
				
				Color brown = new Color(139, 69, 19); 
				g.setColor(brown); 
				g.fillRect(0, (int)(this.getHeight() * 0.9), this.getWidth(), (int)(this.getHeight() * 0.1)); 
				//g.fillRect(0, 490, 800, 60); 
				
			}
		}
		
		static class MyPanel2 extends JPanel {//implements MouseListener, MouseMotionListener{
			private ImageIcon picture; //image to be displayed
			private int x, y, size; 
			boolean drag = false; 
			int picX = 0, picY = 0; 
		
		
			public MyPanel2()
			{
				picture = new ImageIcon();
				x = picX; 
				y = picY; 
				
			}
			
			public void paintComponent( Graphics g)
			{
				//get height and width of oval 
				int width =(int)(this.getWidth() *0.3); 
				int height =(int)(this.getHeight() *0.3);
				int x = (this.getWidth() - width)/2; 
				int y = (this.getHeight() - height)/2; 
				
				picture = new ImageIcon();
				
				super.paintComponent(g); 
				picture.paintIcon(this, g, 0, 0); 
				setBackground(Color.WHITE); 
 
				g.setColor(Color.GREEN); 
				g.fillOval(x, y, width, height); 
				
			}

		}	
}
