package com.eonsahead.swing;

/**
 * Model a matrix.
 *
 * @author Leon Tabak
 * @version 1 April 2020
 */
public class Matrix {

    private final double[][] elements;
/**
 * Construct a 4 by 4 matrix.
 */
    public Matrix() {
        this.elements = new double[4][4];
        this.identity();
    } // Matrix()
/**
 * Retrieve a value at a position in the matrix.
 * @param row row in target matrix
 * @param column column in target matrix
 * @return the value at that location
 */
    public double get(int row, int column) {
        return this.elements[row][column];
    } // get( int, int )
/**
 * Set a value at a position in the matrix.
 * @param row row in target matrix
 * @param column column in target matrix
 * @param value the value to set that location to
 */
    public void set(int row, int column, double value) {
        this.elements[row][column] = value;
    } // set( int, int, double )
    
/**
 * Transform an empty matrix into an identity matrix.
 */
    public final void identity() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == j) {
                    this.set(i, j, 1.0);
                } // if
                else {
                    this.set(i, j, 0.0);
                } // else
            } // for
        } // for
    } // identity()
/**
 * Rotate about the x axis.
 * @param angle the desired rotation angle 
 */
    public void rotationX(double angle) {
        this.identity();
        this.set(1, 1, Math.cos(angle));
        this.set(1, 2, -Math.sin(angle));
        this.set(2, 1, Math.sin(angle));
        this.set(2, 2, Math.cos(angle));
    } // rotationX( double )
/**
 * Rotate about the y axis.
 * @param angle  the desired rotation angle
 */
    public void rotationY(double angle) {
        this.identity();
        this.set(0, 0, Math.cos(angle));
        this.set(0, 2, Math.sin(angle));
        this.set(2, 0, -Math.sin(angle));
        this.set(2, 2, Math.cos(angle));
    } // rotationY( double )
/**
 * Rotate about the z axis.
 * @param angle the desired rotation angle
 */
    public void rotationZ(double angle) {
        this.identity();
        this.set(0, 0, Math.cos(angle));
        this.set(0, 1, -Math.sin(angle));
        this.set(1, 0, Math.sin(angle));
        this.set(1, 1, Math.cos(angle));
    } // rotationZ( double )

   /**
    * Scale a matrix
    * @param xFactor value to scale x by
    * @param yFactor value to scale y by
    * @param zFactor value to scale z by
    */
    public void scale(double xFactor, double yFactor, double zFactor) {
        this.identity();
        this.set(0, 0, xFactor);
        this.set(1, 1, yFactor);
        this.set(2, 2, zFactor);
    } // scale( double, double, double )

    /**
     * 
     * @param deltaX shift in x
     * @param deltaY shift in y
     * @param deltaZ shift in z
     */
    public void translate(double deltaX, double deltaY, double deltaZ) {
        this.identity();
        this.set(0, 3, deltaX);
        this.set(1, 3, deltaY);
        this.set(2, 3, deltaZ);
    } // translate( double, double, double )
    
/**
 * Multiply two matricies.
 * @param otherMatrix the second matrix to be multiplied
 * @return the product matrix
 */
    public Matrix multiply(Matrix otherMatrix) {
        Matrix product = new Matrix();
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                double sum = 0.0;
                for (int k = 0; k < 4; k++) {
                    sum += this.elements[row][k]
                            * otherMatrix.elements[k][column];
                } // for
                product.set(row, column, sum);
            } // for
        } // for
        return product;
    } // multiply( Matrix )
    
/**
 * Multiply two vectors.
 * @param v the second vector
 * @return product vector
 */
    public Vector multiply(Vector v) {
        double x = 0.0;
        for (int i = 0; i < 3; i++) {
            x += this.get(0, i) * v.get(i);
        } // for

        double y = 0.0;
        for (int i = 0; i < 3; i++) {
            y += this.get(1, i) * v.get(i);
        } // for

        double z = 0.0;
        for (int i = 0; i < 3; i++) {
            z += this.get(2, i) * v.get(i);
        } // for

        return new Vector(x, y, z);
    } // multiply( Vector )
/**
 * Convert matrix row to string.
 * @param row the row to convert
 * @return string containing values in matrix row.
 */
    private String rowToString(int row) {
        StringBuilder result = new StringBuilder();
        result.append("( ");
        for (int i = 0; i < 3; i++) {
            result.append(this.get(row, i));
            result.append(",");
        } // for
        result.append(this.get(row, 3));
        result.append(" )");
        return result.toString();
    } // rowToString( int )
/**
 * Convert an entire matrix to be printed
 * @return string containing matrix values
 */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[ ");
        for (int i = 0; i < 4; i++) {
            result.append(rowToString(i));
        } //
        result.append(" ]");
        return result.toString();
    } // toString()

    public static void main(String[] args) {
        Matrix identity = new Matrix();
        System.out.println("identity = " + identity);

        Matrix product = identity.multiply(identity);
        System.out.println("product = " + product);

        Matrix ccw = new Matrix();
        ccw.rotationZ(Math.PI / 4);
        System.out.println("counter-clockwise rotation = " + ccw);

        Matrix cw = new Matrix();
        cw.rotationZ(-Math.PI / 4);
        System.out.println("clockwise rotation = " + cw);

        Matrix netRotation = ccw.multiply(cw);
        System.out.println("net rotation = " + netRotation);
    } // main( String [] )

} // Matrix
