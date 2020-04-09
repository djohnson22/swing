package com.eonsahead.swing;

import java.util.ArrayList;
import java.util.List;

public class Prism {

    private final List<Polygon3D> faces;
    private final List<Vector> top;
    private final List<Vector> bottom;
    private final Vector vTopCenter;
    private final Vector vBottomCenter;

    public Prism(int numberOfSides, double radius, double height) {
        this.faces = new ArrayList<>();
        this.top = new ArrayList<>();
        this.bottom = new ArrayList<>();

        double xCenter = 0.0;
        double yCenter = 0.0;
        double zTop = height / 2;
        double zBottom = -height / 2;

        // Construct points at the centers of the polygons
        // on the top and bottom of the prism
        this.vTopCenter = new Vector(xCenter, yCenter, zTop);
        this.vBottomCenter = new Vector(xCenter, yCenter, zBottom);

        // Construct vertices
        for (int i = 0; i < numberOfSides; i++) {
            double fraction = ((double) i) / numberOfSides;
            double angle = fraction * 2.0 * Math.PI;

            double x = radius * Math.cos(angle);
            double y = radius * Math.sin(angle);

            Vector vTop = new Vector(x, y, zTop);
            Vector vBottom = new Vector(x, y, zBottom);

            this.top.add(vTop);
            this.bottom.add(vBottom);
        } // for

        Vector v0;
        Vector v1;
        Vector v2;
        Polygon3D p;

        // Construct triangles that make top of prism
        for (int i = 0; i < numberOfSides - 1; i++) {
            v0 = this.vTopCenter;
            v1 = this.top.get(i);
            v2 = this.top.get(i + 1);
            p = new Polygon3D(v0, v1, v2);
            this.faces.add(p);
        } // for
        v0 = this.vTopCenter;
        v1 = this.top.get(numberOfSides - 1);
        v2 = this.top.get(0);
        p = new Polygon3D(v0, v1, v2);
        this.faces.add(p);

        // Construct triangles that make bottom of prism
        for (int i = 0; i < numberOfSides - 1; i++) {
            v0 = this.vBottomCenter;
            v1 = this.bottom.get(i);
            v2 = this.bottom.get(i + 1);
            p = new Polygon3D(v0, v2, v1);
            this.faces.add(p);
        } // for
        v0 = this.vBottomCenter;
        v1 = this.bottom.get(numberOfSides - 1);
        v2 = this.bottom.get(0);
        p = new Polygon3D(v0, v2, v1);
        this.faces.add(p);

        // Construct triangles that make sides of prism
        for (int i = 0; i < numberOfSides - 1; i++) {
            v0 = this.top.get(i);
            v1 = this.bottom.get(i);
            v2 = this.top.get(i + 1);
            p = new Polygon3D(v0, v1, v2);
            this.faces.add(p);

            v0 = this.top.get(i + 1);
            v1 = this.bottom.get(i);
            v2 = this.bottom.get(i + 1);
            p = new Polygon3D(v0, v1, v2);
            this.faces.add(p);
        } // for
        v0 = this.top.get(numberOfSides - 1);
        v1 = this.bottom.get(numberOfSides - 1);
        v2 = this.top.get(0);
        p = new Polygon3D(v0, v1, v2);
        this.faces.add(p);

        v0 = this.top.get(0);
        v1 = this.bottom.get(numberOfSides - 1);
        v2 = this.bottom.get(0);
        p = new Polygon3D(v0, v1, v2);
        this.faces.add(p);
    } // Prism()

    public void transform(Matrix m) {

        Vector v = this.vTopCenter;
        v.set(m.multiply(v));

        v = this.vBottomCenter;
        v.set(m.multiply(v));

        for (Vector u : this.top) {
            u.set(m.multiply(u));
        } // for 

        for (Vector u : this.bottom) {
            u.set(m.multiply(u));
        } // for 
    } // transform( Matrix )

    public List<Polygon3D> getFaces() {
        return this.faces;
    } // getFaces()

} // Prism
