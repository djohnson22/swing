package com.eonsahead.swing;

import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.List;

public class Polygon3D {

    private final List<Vector> vertices = new ArrayList<>();

    public Polygon3D( Vector v0, Vector v1, Vector v2 ) {
        this.vertices.add(v0);
        this.vertices.add(v1);
        this.vertices.add(v2);
    } // Polygon3D( Vector, Vector, Vector )
    
    public Polygon3D(int numberOfSides, double radius, double z) {
        for (int i = 0; i < numberOfSides; i++) {
            double fraction = ((double) i) / numberOfSides;
            double angle = fraction * 2.0 * Math.PI;
            double x = radius * Math.cos(angle);
            double y = radius * Math.sin(angle);
            Vector v = new Vector(x, y, z);
            this.vertices.add(v);
        } // for
    } // Polygon3D( int, double )

    public void transform(Matrix m) {
        for (Vector u : this.vertices) {
            u.set( m.multiply(u) );
        } // for 
    } // transform( Matrix )

    public Vector getNormal() {
        Vector p0 = this.vertices.get(0);
        Vector p1 = this.vertices.get(1);
        Vector p2 = this.vertices.get(2);
        
        Vector v0 = p2.subtract(p1);
        Vector v1 = p0.subtract(p1);
        
        Vector crossProduct = v0.cross(v1);
        
        return crossProduct.normalize();
    } // getNormal()
    
    public Shape getShape() {
        GeneralPath path = new GeneralPath();

        Vector v = this.vertices.get(0);
        double x = v.get(0);
        double y = v.get(1);
        path.moveTo(x, y);

        for (int i = 1; i < this.vertices.size(); i++) {
            v = this.vertices.get(i);
            x = v.get(0);
            y = v.get(1);
            path.lineTo(x, y);
        } // for

        path.closePath();

        return path;
    } // getShape()

} // Polygon3D
