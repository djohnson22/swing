package com.eonsahead.swing;

public class Vector {

    private double[] elements = new double[4];

    public Vector() {
        for (int i = 0; i < 4; i++) {
            this.elements[i] = 0.0;
        } // for
    } // Vector()

    public Vector(double x, double y, double z) {
        this.elements[0] = x;
        this.elements[1] = y;
        this.elements[2] = z;
        this.elements[3] = 1.0;
    } // Vector( double, double, double )

    public Vector(double x, double y, double z, double h) {
        this.elements[0] = x;
        this.elements[1] = y;
        this.elements[2] = z;
        this.elements[3] = h;
    } // Vector( double, double, double, double )

    public double get( int index ) {
        return this.elements[index];
    } // get( int )
    
    public Vector add( Vector v ) {
        double x = this.get(0) + v.get(0);
        double y = this.get(1) + v.get(1);
        double z = this.get(2) + v.get(2);        
        return new Vector( x, y, z );
    } // add( Vector )
    
    public Vector subtract( Vector v ) {
        double x = this.get(0) - v.get(0);
        double y = this.get(1) - v.get(1);
        double z = this.get(2) - v.get(2);        
        return new Vector( x, y, z );
    } // subtract( Vector )
    
    public double dot( Vector v ) {
        double xProduct = this.get(0) * v.get(0);
        double yProduct = this.get(1) * v.get(1);
        double zProduct = this.get(2) * v.get(2);
        return xProduct + yProduct + zProduct;
    } // dot( Vector )
    
    public double magnitude() {
        return Math.sqrt( this.dot( this ) );
    } // magnitude()
    
    public Vector cross( Vector v ) {
        double x = this.get(1) * v.get(2) - this.get(2) * v.get(1);
        double y = this.get(2) * v.get(0) - this.get(0) * v.get(2);
        double z = this.get(0) * v.get(1) - this.get(1) * v.get(0);
        return new Vector( x, y, z );
    } // cross( Vector )
    
} // Vector
