import java.util.ArrayList;
import java.awt.Color;

public class Player extends Actor{	

	double dVx, dVy;
	double accelSpeed;
	double shiftFactor;

	Weapon weapon;

	public Player(double x, double y){
		super(x, y);
    	health = 3; //dummy val
		accelSpeed = 0.125;
		shiftFactor = 0.3333;
		//set weapon

		radius = 8;

		points = new ArrayList<int[]>();
		points.add(new int[]{8,8});
		points.add(new int[]{-8,8});
		points.add(new int[]{-8,-8});
		points.add(new int[]{8,-8});
		
		color = Color.CYAN;
		weapon = new Shotgun();
	}

	//performs player motion update given set of pressed keys
	public void keyMove(boolean[] keys) {
		dVx = dVy = 0;
		
		double as = accelSpeed;
		if (Main.game.shiftPressed)
			as *= shiftFactor;

		if (keys[0])
			dVy -= as;
		if (keys[2])
			dVy += as;

		if (keys[1])
			dVx -= as;
		if (keys[3])
			dVx += as;

		vx += dVx;
		vy += dVy;
	}

	//overrides Actor.update()
	public void update() {
		keyMove(Main.game.keysPressed);
		super.update();

		if (Main.game.mouseDown)
			weapon.attack();
		else
			weapon.recover();
	}
}
