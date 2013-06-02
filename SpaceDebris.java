public class SpaceDebris extends Enemy {

	double accel;

	public SpaceDebris(double x, double y) {
		super(x,y);
		accel = 1;
	}

	public void update() {
		Player p = Main.game.player;
		double theta = Math.atan2(p.y - y, p.x - x);
		double distanceSquared = (p.y - y) * (p.y - y) + (p.x - x) * (p.x - x);

		vx += accel * Math.cos(theta) / distanceSquared;
		vy += accel * Math.sin(theta) / distanceSquared;

		super.update();
	}

}
