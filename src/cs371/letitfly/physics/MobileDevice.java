package cs371.letitfly.physics;

public enum MobileDevice {
	
	/* TextView tv1 = (TextView) findViewById(R.id.tv1);
     * String str = android.os.Build.MODEL;
     * tv1.setText(str);
	 */
	
	GALAXY_S4(0.13),
	NEXUS_4(0.139),
	NEXUS_7(0.603),
	HTC_EVO(0.17);
	
	private final double weight;
	
	MobileDevice(double weight) {
		this.weight = weight;
	}
	
	public double weight() {
		return weight;
	}
}
