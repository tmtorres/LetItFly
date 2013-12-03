package cs371.letitfly.physics;


public class Physics {
	public static float g = Gravity.EARTH.gravity();
	
	private Physics() {}
	
	public static double getDisplacement(double velocity, double time) {
		return velocity * time;
	}
	
	public static double getTimeElapsed(double initialVelocity) {
		return 2 * Math.abs(initialVelocity / g);
	}
	
	public static double normalizeVelocity(double velocity, double mass, MobileDevice mobileDevice) {
		double energy = 0.5 * mobileDevice.weight() * Math.pow(velocity, 2);
		return Math.sqrt((2 * energy) / mass);
	}
}
