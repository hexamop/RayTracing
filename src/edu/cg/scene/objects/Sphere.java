package edu.cg.scene.objects;

import edu.cg.UnimplementedMethodException;
import edu.cg.algebra.*;

public class Sphere extends Shape {
	private Point center;
	private double radius;
	
	public Sphere(Point center, double radius) {
		this.center = center;
		this.radius = radius;
	}
	
	public Sphere() {
		this(new Point(0, -0.5, -6), 0.5);
	}
	
	@Override
	public String toString() {
		String endl = System.lineSeparator();
		return "Sphere:" + endl + 
				"Center: " + center + endl +
				"Radius: " + radius + endl;
	}
	
	public Sphere initCenter(Point center) {
		this.center = center;
		return this;
	}
	
	public Sphere initRadius(double radius) {
		this.radius = radius;
		return this;
	}
	
	@Override
	public Hit intersect(Ray ray) {

		double[] tValues;

		Vec RaySourceToSphereCenter = new Vec(this.center, ray.source());

		double a = Ops.norm(ray.direction()); //should be 1.
		double b = 2 * (ray.direction().dot(RaySourceToSphereCenter));
		double c = Ops.normSqr(RaySourceToSphereCenter) - Math.pow(this.radius,2);

		tValues = Ops.calcSquareRoots(a,b,c);

		if (tValues == null)
			return null;

		double t = Ops.selectSmallestPositive(tValues);

		if (Double.isNaN(t))
			return null;

		Vec normal = new Vec(this.center, Ops.tMult(t, ray.direction()));

		return new Hit(t, normal);

		//throw new UnimplementedMethodException("intersect(Ray)");
	}
}
