package nl.andrewlalis.model;

public class PhysicsObject {
	private static final double G = 6.674E-11;

	// Mass in Kg.
	protected double mass;

	// Position in meters.
	protected double positionX;
	protected double positionY;

	// Velocity in m/s
	protected double velocityX;
	protected double velocityY;

	protected double orientation;
	protected double angularVelocity;

	public PhysicsObject(double mass, double positionX, double positionY, double velocityX, double velocityY, double orientation) {
		this.mass = mass;
		this.positionX = positionX;
		this.positionY = positionY;
		this.velocityX = velocityX;
		this.velocityY = velocityY;
		this.orientation = orientation;
		this.angularVelocity = 0;
	}

	public void updateVelocityForObject(PhysicsObject other, double deltaT) {
		double dx = this.positionX - other.positionX;
		double dy = this.positionY - other.positionY;
		double radius = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
		radius = Math.max(radius, 0.01);
		double angle = Math.atan2(dy, dx);
		double a = -G * other.mass / (Math.pow(radius, 2));

		double ax = a * Math.cos(angle);
		double ay = a * Math.sin(angle);
		this.velocityX += ax * deltaT;
		this.velocityY += ay * deltaT;
	}

	public void updatePosition(double deltaT) {
		this.positionX += this.velocityX * deltaT;
		this.positionY += this.velocityY * deltaT;
		this.orientation += this.angularVelocity * deltaT;
		this.normalizeOrientation();
	}

	/**
	 * Normalizes the object's orientation to between 0 and 2 pi.
	 */
	private void normalizeOrientation() {
		while (this.orientation > 2 * Math.PI) {
			orientation -= 2 * Math.PI;
		}
		while (this.orientation < 0) {
			orientation += 2 * Math.PI;
		}
	}

	public double getPositionX() {
		return positionX;
	}

	public double getPositionY() {
		return positionY;
	}

	public double getVelocityX() {
		return velocityX;
	}

	public double getVelocityY() {
		return velocityY;
	}

	public double getOrientation() {
		return orientation;
	}

	public double getAngularVelocity() {
		return angularVelocity;
	}

	public void setAngularVelocity(double angularVelocity) {
		this.angularVelocity = angularVelocity;
	}

	@Override
	public String toString() {
		return "PhysicsObject{" +
				"mass=" + mass +
				", positionX=" + positionX +
				", positionY=" + positionY +
				", velocityX=" + velocityX +
				", velocityY=" + velocityY +
				", orientation=" + orientation +
				'}';
	}
}
