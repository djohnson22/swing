package com.eonsahead.swing;

import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.List;

public class Polygon3D {
    public static final int CW = 0;
    public static final int CCW = 1;

    private final List<Vector> vertices = new ArrayList<>();
    private final int mode;

    public Polygon3D(Vector v0, Vector v1, Vector v2) {
        this.vertices.add(v0);
        this.vertices.add(v1);
        this.vertices.add(v2);
        this.mode = Polygon3D.CCW;
    } // Polygon3D( Vector, Vector, Vector )

    public Polygon3D(int numberOfSides, double radius, double z,
            int mode ) {
        for (int i = 0; i < numberOfSides; i++) {
            double fraction = ((double) i) / numberOfSides;
            double angle = fraction * 2.0 * Math.PI;
            double x = radius * Math.cos(angle);
            double y = radius * Math.sin(angle);
            Vector v = new Vector(x, y, z);
            this.vertices.add(v);
        } // for
        
        this.mode = mode;
    } // Polygon3D( int, double, double, int )

    public List<Vector> getVertices() {
        return this.vertices;
    } // getVertices()

    public List<Polygon3D> makeSleeve( Polygon3D otherPolygon ) {
        // Construct triangles that make sides of prism
        List<Polygon3D> faces = new ArrayList<>();

        int numberOfSides = this.getVertices().size();

        if (numberOfSides == otherPolygon.getVertices().size()) {
            Vector v0;
            Vector v1;
            Vector v2;
            Polygon3D p;

            List<Vector> vertexListA = this.getVertices();
            List<Vector> vertexListB = otherPolygon.getVertices();

            for (int i = 0; i < numberOfSides - 1; i++) {
                v0 = vertexListA.get(i);
                v1 = vertexListB.get(i);
                v2 = vertexListA.get(i + 1);
                p = new Polygon3D(v0, v1, v2);
                faces.add(p);

                v0 = vertexListA.get(i + 1);
                v1 = vertexListB.get(i);
                v2 = vertexListB.get(i + 1);
                p = new Polygon3D(v0, v1, v2);
                faces.add(p);
            } // for

            v0 = vertexListA.get(numberOfSides - 1);
            v1 = vertexListB.get(numberOfSides - 1);
            v2 = vertexListA.get(0);
            p = new Polygon3D(v0, v1, v2);
            faces.add(p);

            v0 = vertexListA.get(0);
            v1 = vertexListB.get(numberOfSides - 1);
            v2 = vertexListB.get(0);
            p = new Polygon3D(v0, v1, v2);
            faces.add(p);
        } // if

        return faces;
    } // makeSleeve( Polygon3D, Polygon3D )

    public void transform(Matrix m) {
        for (Vector u : this.vertices) {
            u.set(m.multiply(u));
        } // for 
    } // transform( Matrix )

    public Vector getNormal() {
        Vector p0 = this.vertices.get(0);
        Vector p1 = this.vertices.get(1);
        Vector p2 = this.vertices.get(2);

        Vector v0 = p2.subtract(p1);
        Vector v1 = p0.subtract(p1);

        Vector crossProduct = v0.cross(v1);

        if( this.mode == Polygon3D.CW ) {
            Matrix m = new Matrix();
            m.scale( -1.0, -1.0, -1.0);
            crossProduct = m.multiply( crossProduct );
        } // if
        
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
