package basicpaint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;



public class SwingPaint  {
	String shape;
	JButton clearBtn;
	DrawArea drawArea;
	
	ActionListener actionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource()==clearBtn) {
				drawArea.clear();
			}
			
		}
		
	};
	public static void main(String[] args) {
	new SwingPaint().show();
	}
	
	public void show() {
		//create main frame
				JFrame frame = new JFrame ("Swing Paint");
				Container content = frame.getContentPane();
				//set layout on contect pane
				content.setLayout(new BorderLayout());
				// create draw area 
				drawArea = new DrawArea();
				// ad to content pane 
				content.add(drawArea, BorderLayout.CENTER);
				
				frame.setSize(600, 600);
				//can close frame
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//show the swing paint result
				frame.setVisible(true);JMenuBar menuBar = new JMenuBar();
				frame.add(menuBar, BorderLayout.NORTH);
				JMenu mnFile = new JMenu("File");
				
				mnFile.setHorizontalAlignment(SwingConstants.RIGHT);
				menuBar.add(mnFile);
				
				JMenuItem mntmNew = new JMenuItem("New");
				mnFile.add(mntmNew);
				
				JMenuItem mntmOpen = new JMenuItem("Open");
				mntmOpen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						drawArea.openImage();
					}
				});
				mnFile.add(mntmOpen);
				
				JMenuItem mntmSave = new JMenuItem("Save");
				mntmSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						drawArea.saveImage();
					}
				});
				mnFile.add(mntmSave);
				
				JMenuItem mntmSaveas = new JMenuItem("SaveAs");
				mnFile.add(mntmSaveas);
				
				JMenuItem mntmClose = new JMenuItem("Close");
				mnFile.add(mntmClose);
				
				JMenu mnNetwork = new JMenu("Network");
				menuBar.add(mnNetwork);
				
				JMenuItem mntmJoinToA = new JMenuItem("Join to a Network");
				mnNetwork.add(mntmJoinToA);
				
				JMenuItem mntmCreateANetwork = new JMenuItem("Create a Network");
				mnNetwork.add(mntmCreateANetwork);
					
				JToolBar toolBar = new JToolBar();
				frame.add(toolBar, BorderLayout.SOUTH);
				clearBtn = new JButton("Clear");
				clearBtn.addActionListener(actionListener);
				toolBar.add(clearBtn);
				
				JButton btnCircle = new JButton("circle");
				btnCircle.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						drawArea.setNameShape("Circle");
					}
				});
				toolBar.add(btnCircle);
				
				JButton btnText = new JButton("text");
				btnText.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						drawArea.setNameShape("Text");
					}
				});
				toolBar.add(btnText);

				
				
				JButton btnElipse = new JButton("elipse");
				btnElipse.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						drawArea.setNameShape("Oval");
					}
				});
				toolBar.add(btnElipse);
				
				JButton btnErase = new JButton("erase");
				btnErase.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						drawArea.erase();
					}
				});
				toolBar.add(btnErase);
				
				JButton btnPen = new JButton("pen");
				btnPen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						drawArea.setNameShape("Pen");
					}
				});
				toolBar.add(btnPen);
				
				JButton btnRect = new JButton("Rect");
				btnPen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						drawArea.setNameShape("Rectangle");
					}
				});
				toolBar.add(btnRect);
				
				JButton btnColor = new JButton("color");
				btnColor.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Color initialcolor = Color.RED; 
						  
				        // color chooser Dialog Box 
				        Color color = JColorChooser.showDialog(frame, "Select a color", initialcolor); 
				  
				        // set Background color of the Conatiner 
				        drawArea.setColor(color); 
				        drawArea.color();
					}
				});
				toolBar.add(btnColor);
				JButton btnLine = new JButton("line");
				btnLine.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						drawArea.setNameShape("Line");
					}
				});
				toolBar.add(btnLine);
	}
}
