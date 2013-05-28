public class Thing {

	double x,y;
	double radius;
}
import java.awt.image.BufferedImage;

public class Actor {

	//coordinates
	double x, y;
	double vx, vy, vt;

	BufferedImage img;

	int width, height;

	public Actor(int x, int y, String imgpath) {
		this.x = x;
		this.y = y;

		img = Util.loadImg(imgpath);
	}

	public void update() {
		relativityUpdate();
	}

	public static double[] relativityHelper(double dVx, double dVy) {
	    double[] v = new double[3]; 
		double Bx = dVx / c;
		double By = dVy / c;
		double gamma = 1 / Math.sqrt(1 - (Bx ^ 2) - (By ^ 2));
		v[0] = (gamma / c) * (c * t - vx * Bx - y * By);
		v[1] = - gamma * Bx * c * t + (1 + (gamma - 1) * ((Bx ^ 2) / (Bx ^ 2 + By ^ 2))) * vx + (gamma - 1) ((Bx * By) / (Bx ^ 2 + By ^ 2));
		v[2] = - gamma * By * c * t + (1 + (gamma - 1) * ((By ^ 2) / (By ^ 2 + Bx ^ 2))) * vy + (gamma - 1) ((Bx * By) / (Bx ^ 2 + By ^ 2));
		return v;
	}

	

	
}