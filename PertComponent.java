import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Canvas;

import java.lang.Runnable;

import javax.swing.JFrame;

public class PertComponent extends Canvas implements Runnable {

	static final int WIDTH = 800;
	static final int HEIGHT = 600;

	static JFrame frame;
	Thread thread;
	boolean running;

	BufferedImage img;
	int[] pixels;

	Screen screen;
	Game game;

	public PertComponent() {
		Dimension size = new Dimension(WIDTH, HEIGHT);
		setPreferredSize(size);
		setMaximumSize(size);
		setMinimumSize(size);

		game = new Game();
		screen = new Screen(WIDTH, HEIGHT);
		img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();
	}

	public void start() {
		//don't start if you're running
		if (running) return;
		running = true;

		thread = new Thread(this);
		thread.run();
	}

	public void run() {
		while (running) {
			tick();
			render();
		}
	}

	public void tick() {
		game.tick();
	}

	public void render() {
		screen.render(game);

		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();

		for (int i=0; i<WIDTH*HEIGHT; i++)
			pixels[i] = screen.pixels[i];
		g.drawImage(img, 0,0, WIDTH, HEIGHT, this);

		bs.show();
	}

	public void stop() {
		//don't stop if you're stopped
		if (!running) return;
		running = false;

		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		PertComponent game = new PertComponent();
		
		frame = new JFrame("Pert!");
		frame.add(game);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.pack();
		frame.setVisible(true);

		game.start();
	}
}