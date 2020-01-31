

/**
 * Celestial Body class for NBody
 * @author Jason Few
 *
 */
public class CelestialBody {

	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;

	/**
	 * Create a Body from parameters
	 * @param xp initial x position
	 * @param yp initial y position
	 * @param xv initial x velocity
	 * @param yv initial y velocity
	 * @param mass of object
	 * @param filename of image for object animation
	 */
	public CelestialBody(double xp, double yp, double xv, double yv, double mass, String filename){
		myXPos = xp;
		myYpos = yp;
		myXVel = xv;
		myYvel = yv;
		myMass = mass;
		myFileName = filename

	}

	/**
	 * Copy constructor: copy instance variables from one
	 * body to this body
	 * @param b used to initialize this body
	 */
	public CelestialBody(CelestialBody b){
		myXPos = b.getX();
		myYPos = b.getY();
		myXVel = b.getXVel();
		myYVel = b.getYVel();
		myMass = b.getMass();
		myFileName = b.getName();
		// TODO: complete constructor
	}

	public double getX() {
		// TODO: complete method
		return myXPos;
	}
	public double getY() {
		return myYPos;
	}
	public double getXVel() {
		return myXVel;
	}
	public double getYVel() {
		return myYVel;
	}
	/**
	 * Return y-velocity of this Body.
	 * @return value of y-velocity.
	 */
	public double getMass() {
		return myMass;
		// TODO: complete method

	}
	public String getName() {
		// TODO: complete method
		return myFileName;
	}

	/**
	 * Return the distance between this body and another
	 * @param b the other body to which distance is calculated
	 * @return distance between this body and b
	 */
	public double calcDistance(CelestialBody b) {
		double dx = myXPos - b.getX();
		double dy = myYPos - b.getY();
		return Math.sqrt(dx*dx + dy*dy);
	}

	public double calcForceExertedBy(CelestialBody b) {
		return (6.67*1e-11) * myMass * b.getMass() / (calcDistance(b)*calcDistance(b));
	}

	public double calcForceExertedByX(CelestialBody b) {
		double fx = calcForceExertedBy(b) * (b.getX()-myXPos) / calcDistance(b);
		return fx;
	}
	public double calcForceExertedByY(CelestialBody b) {
		double fy = calcForceExertedBy(b) * (b.getY()-myYPos) / calcDistance(b);
		return fy;
	}

	public double calcNetForceExertedByX(CelestialBody[] bodies) {
		double netForceX = 0;
		for (CelestialBody body: bodies){
			if (!body.equals(this)){
				netForceX += calcForceExertedByX(body)
			}
		}
		return netForceX;
	}

	public double calcNetForceExertedByY(CelestialBody[] bodies) {
		double netForceY = 0;
		for (CelestialBody body: bodies){
			if (!body.equals(this)){
				netForceY += calcForceExertedByY(body)
			}
		}
		return netForceY;
	}

	public void update(double deltaT, double xforce, double yforce) {
		double xm = xforce / myMass;
		double ym = yforce / myMass;
		double xat = myXVel + (xm * deltaT);
		double yat = myYVel + (ym * deltaT);
		double xt = myXPos + (deltaT * xat);
		double yt = myYPos + (deltaT * yat);
		myXVel= xat;
		myYVel = yt;
		myXPos = xt;
		myYPos = yt;
	}

	public void draw() {
		StdDraw.picture(myXPos, myYPos, "images/" + myFileName);
	}
}
