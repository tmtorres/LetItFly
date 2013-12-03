package cs371.letitfly.physics;
import android.hardware.SensorManager;

public enum Gravity {
	
	
	DEATHSTAR("Death Star", SensorManager.GRAVITY_DEATH_STAR_I),
	EARTH("Earth", SensorManager.GRAVITY_EARTH),
	JUPITER("Jupiter", SensorManager.GRAVITY_JUPITER),
	MARS("Mars", SensorManager.GRAVITY_MARS),
	MERCURY("Mercury", SensorManager.GRAVITY_MERCURY),
	MOON("Moon", SensorManager.GRAVITY_MOON),
	NEPTUNE("Neptune",SensorManager.GRAVITY_NEPTUNE),
	PLUTO("Pluto", SensorManager.GRAVITY_PLUTO),
	SATURN("Saturn", SensorManager.GRAVITY_SATURN),
	URANUS("Uranus",SensorManager.GRAVITY_URANUS),
	VENUS("Venus", SensorManager.GRAVITY_VENUS)
	;
	
	private final String description;
    private final float gravity;
	
	Gravity(String description, float gravity) {
		this.description = description;
		this.gravity = gravity;
	}
	
	public String description() {
		return description;
	}
	
	public float gravity() {
		return gravity;
	}
	
	@Override
	public String toString() {
		return description;
	}
}
