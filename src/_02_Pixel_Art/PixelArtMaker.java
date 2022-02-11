package _02_Pixel_Art;

import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;

public class PixelArtMaker implements MouseListener{
	private JFrame window;
	private JButton save;
	private JButton load;
	
	private GridInputPanel gip;
	private GridPanel gp;
	ColorSelectionPanel csp;
	
	private static final String fileName = "src/_02_Pixel_Art/saved.dat";
	
	public void start() {
		gip = new GridInputPanel(this);	
		window = new JFrame("Pixel Art");
		save = new JButton();
		load = new JButton();
		window.setLayout(new FlowLayout());
		window.setResizable(false);
		
		window.add(gip);
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	public void submitGridData(int w, int h, int r, int c) {
		gp = new GridPanel(w, h, r, c);
		csp = new ColorSelectionPanel();
		window.remove(gip);
		buildWindow();
	}
	public void buildWindow() {
		window.add(gp);
		window.add(csp);
		window.add(save);
		window.add(load);
		save.setText("Save Image");
		load.setText("Load Image");
		gp.repaint();
		gp.addMouseListener(this);
		save.addMouseListener(this);
		load.addMouseListener(this);
		window.pack();
	}
	public void deconstructWindow() {
		window.remove(gp);
		window.remove(csp);
		window.remove(save);
		window.remove(load);
		window.pack();
	}
	public static void main(String[] args) {
		new PixelArtMaker().start();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource().equals(save)) {
			save(gp);
		}
		else if(e.getSource().equals(load)) {
			deconstructWindow();
			gp = load();
			window.add(gp);
			buildWindow();
		}
		else {
		gp.setColor(csp.getSelectedColor());
		System.out.println(csp.getSelectedColor());
		gp.clickPixel(e.getX(), e.getY());
		gp.repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	private static void save(GridPanel data) {
		try (FileOutputStream fos = new FileOutputStream(new File(fileName));
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(data);
			System.out.println("hello");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static GridPanel load() {
		try (FileInputStream fis = new FileInputStream(new File(fileName));
			ObjectInputStream ois = new ObjectInputStream(fis)) {
			return (GridPanel) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			// This can occur if the object we read from the file is not
			// an instance of any recognized class
			e.printStackTrace();
			return null;
		}
	}
}
