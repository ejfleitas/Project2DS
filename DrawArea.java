package basicpaint;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class DrawArea extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Image in where we are going to draw
	private Image image;
    //Graphics object //used to draw
	 Graphics2D g2;
	//mouse coordinates
	private int currentX, currentY, oldx, oldy;
	//color
	static Color color = null;
	//shape
	Shape r = null;
	//shape setType 
	static String shape = "Pen";
	//
	ArrayList<Shape> shapes = new ArrayList<Shape>();
    
	Point startDrag, endDrag;

	public DrawArea() {
		setDoubleBuffered(false);
		addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				oldx = e.getX();
				oldy = e.getY();
			    startDrag = new Point(oldx,oldy);
		        endDrag = startDrag;
		        repaint();
			}	
			public void mouseReleased(MouseEvent e) {
				System.out.println(shape);
				if (shape == "Rectangle") {
					g2.draw(makeRectangle(startDrag.x, startDrag.y, e.getX(), e.getY()));
		        		}else if (shape == "Text") {
		        				g2.drawString("hola",e.getX(), e.getY());
		        			}else if (shape == "Circle") {
		        					g2.draw(makeElipse(startDrag.x, startDrag.y, e.getX(), e.getY()));
		        				}else if (shape == "Oval") {
		        					g2.draw(makeElipse(startDrag.x, startDrag.y, e.getX(), e.getY()));
		        				}else if (shape == "Line") {
		        					g2.draw(makeLine(startDrag.x, startDrag.y, e.getX(), e.getY()));
		        				}
		       shapes.add(r);
		       startDrag = null;
		       endDrag = null;
		       repaint();
		     }
		});
		addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				//coord x,y when drag mouse
				currentX = e.getX();
				currentY = e.getY();
				endDrag = new Point(currentX,currentY);
				if (g2 !=null && shape == "Pen") {
					//draw line if g2 context not null
					g2.drawLine(oldx, oldy, currentX, currentY);
					//refresh draw area to repaint
						}
					repaint();
					//store current coords x,y as olds x,y
					oldx = currentX;
					oldy = currentY;
				}
			
		});
	}
	public void setNameShape(String nameShape) {
		DrawArea.shape = nameShape;
	}
	public String getNameShape() {
		return shape;
	}
	public void setColor(Color setcolor) {
		DrawArea.color = setcolor;
	}
	public Color getColor() {
		return color;
	}
	protected void paintComponent (Graphics g) {
		if (image == null) {
			//image to draw null / we create
			image= createImage(getSize().width, getSize().height);
			g2 = (Graphics2D) image.getGraphics();
			//enable antialising
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
			// clear the draw area
			clear();
			g2.setStroke(new BasicStroke(2));
		    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		    System.out.println(shapes);
		      for (Shape s : shapes) {
		        g2.setColor(Color.BLACK);
		        g2.draw(s);
		        g2.fill(s);
		      }
				
		      if (startDrag != null && endDrag != null) {
		        g2.setColor(Color.black);
		        if (shape == "Rectangle") {
		        	g2.draw(makeRectangle(startDrag.x, startDrag.y, endDrag.x, endDrag.y));
		            }else if (shape == "Circle") {
		            g2.draw(makeElipse(startDrag.x, startDrag.y, endDrag.x, endDrag.y));
		            }else if (shape == "Line") {
		            g2.draw(makeLine(startDrag.x, startDrag.y, endDrag.x, endDrag.y)); 
		            }
		      }
		}
		g.drawImage(image,0,0, null);
	}
	public void clear() {
		g2.setPaint(Color.white);
		//draw white on entire draw area to clear
		g2.fillRect(0, 0, getSize().width, getSize().height);
		g2.setColor(Color.black);
		repaint();
	}
	public void color() {
		// apply red color on g2 context
		g2.setColor(color);
	}
	public void erase() { // apply red color on g2 context
		g2.setPaint(Color.white); }
	
	public void saveImage(){
			try{
			     File f = new File("C:\\Users\\ENRIF\\OneDrive\\Escritorio\\test\\test.jpg");  //output file path
			      ImageIO.write((RenderedImage) image, "jpg", f);
			      System.out.println("Writing complete.");
			      clear();
			    }catch(IOException e){
			      System.out.println("Error: "+e);
			    }
	}
	public void openImage(){
		try{
			  File f = new File("C:\\Users\\ENRIF\\OneDrive\\Escritorio\\test\\pic.jpg"); //image file path
		      image = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
		      image = ImageIO.read(f);
		      System.out.println("Reading complete.");
		      
		    }catch(IOException e){
		      System.out.println("Error: "+e);
		    }
}
	 private Rectangle2D.Float makeRectangle(int x1, int y1, int x2, int y2) {
		   return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
		    }
		    private Ellipse2D.Double makeElipse(int x1, int y1, int x2, int y2){
				return new Ellipse2D.Double(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
		    }
		    private Line2D.Double makeLine(int x1, int y1, int x2, int y2){
				return new Line2D.Double(x1, y1, x2, y2);
		    }
	 }
