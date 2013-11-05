package cs371.letitfly.physics;

public enum Projectile {
	
	BASKETBALL("basketball", 0.62369),
	CAT("cat", 4.53592),
	FOOTBALL("football", 0.453592),
	ROCK("rock", 2.26796),
	HAT("hat", 1.1);
	
	private final String description;
    private final double weight;
	
	Projectile(String description, double weight) {
		this.description = description;
		this.weight = weight;
	}
	
	public String description() {
		return description;
	}
	
	public double weight() {
		return weight;
	}
	
	@Override
	public String toString() {
		return description;
	}
}
