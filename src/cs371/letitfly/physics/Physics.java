package cs371.letitfly.physics;

public class Physics {
	public static final double g = 9.80665;
	
	private Physics() {}
	
	public static double getDisplacement(double velocity, double time) {
		return velocity * time;
	}
	
	public static double getTimeElapsed(double initialVelocity) {
		return 2 * Math.abs(initialVelocity / g);
	}
	
	public static double normalizeAcceleration(double acceleration, double mass, MobileDevice mobileDevice) {
		double force = (mobileDevice.weight() / g) * acceleration;
		return force / mass;
	}
	
	public static void main(String[] args) {
		
		double weight = 0.62;
		weight = 1000;
		
		/* We are given two values, horizontal and vertical acceleration in m/s/s */
		double xAcceleration = 12;
		double yAcceleration = 29;
		double zAcceleration = 22;
		
		/* Mass of an object (basketball) in kg */
		double mass = weight / Physics.g;
		
		/* Current device */
		MobileDevice mobileDevice = MobileDevice.GALAXY_S4;
		
		/* Calculate acceleration for the given object */
		xAcceleration = normalizeAcceleration(xAcceleration, mass, mobileDevice);
		yAcceleration = normalizeAcceleration(yAcceleration, mass, mobileDevice);
		zAcceleration = normalizeAcceleration(zAcceleration, mass, mobileDevice);
		
		/* We assume the values given are constant and the average time for a device
		 * to be "thrown" is one second. In other words, it took one second for the device
		 * to leave my hand from the moment I began to throw it with constant acceleration.
		 * Therefore, after one second, the initial velocity = initial acceleration
		 * Also note that as soon as the device leaves my hand, horizontal acceleration is zero.
		 */
		double xVelocity = xAcceleration;
		double yVelocity = yAcceleration;
		double zVelocity = zAcceleration;
		
		/* Calculate the time based on the initial vertical velocity */
		double time = getTimeElapsed(yVelocity);
		
		/* Calculate displacement in meters */
		double xDisplacement = getDisplacement(xVelocity, time);
		double zDisplacement = getDisplacement(zVelocity, time);
		double displacement = Math.sqrt(Math.pow(xDisplacement, 2) + Math.pow(zDisplacement, 2));
		
		double feet = displacement * 3.28;
		
		/* Print a message */
		System.out.println("The basketball traveled " + feet + " feet!");
		
		/* Add a modifier if necessary */
		feet *= Math.max((weight / mobileDevice.weight()), 1);
		System.out.println("Joking! The basketball really traveled " + feet + " feet.");
	}
}
