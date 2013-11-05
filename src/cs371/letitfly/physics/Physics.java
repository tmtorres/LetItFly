package cs371.letitfly.physics;

import java.util.Random;

public class Physics {
	public static final double g = 9.80665;
	
	private Physics() {}
	
	public static double getDisplacement(double velocity, double time) {
		return velocity * time;
	}
	
	public static double getTimeElapsed(double initialVelocity) {
		return 2 * Math.abs(initialVelocity / g);
	}
	
	public static double normalizeAcceleration(double acceleration, double weight, MobileDevice mobileDevice) {
		
		Random generator = new Random(0);
		
		double force = (mobileDevice.weight() / g) * acceleration;
		double mass = weight / g;
		
		if ((0 < weight) && (weight <= 1)) {
			return force / mass;
		}
		else if ((1 < weight) && (weight <= 3)) {
			return (force / mass) * (10 - weight * (generator.nextDouble() * 0.5 + 2.1));
		}
		else if ((3 < weight) && (weight <= 10)) {
			return 0;
		}
		else if ((10 < weight) && (weight < 25)) {
			return 0;
		}
		else {
			return 1 - (force / mass);
		}
	}
}
