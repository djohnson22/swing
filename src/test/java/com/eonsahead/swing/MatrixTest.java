package com.eonsahead.swing;

import java.util.Random;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MatrixTest {

    private static final double EPSILON = 1E-8;
    private final Random rng = new Random();

    public MatrixTest() {
    } // MatrixTest()

    @Test
    public void testGet() {
        int row = 0;
        int column = 0;
        Matrix instance = new Matrix();
        double expResult = 1.0;
        double result = instance.get(row, column);
        assertEquals(expResult, result, EPSILON);
    } // testGet()

    @Test
    public void testSet() {
        int row = 0;
        int column = 0;
        double value = 99.0;
        Matrix instance = new Matrix();
        instance.set(row, column, value);
        double result = instance.get(row, column);
        assertEquals(value, result, EPSILON);
    } // testSet()

    @Test
    public void testIdentity() {
        Matrix instance = new Matrix();
        instance.identity();
        double diagonalElement = 1.0;
        double nonDiagonalElement = 0.0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                double actual = instance.get(i, j);
                if (i == j) {
                    assertEquals(diagonalElement, actual, 1E-8);
                } // if
                else {
                    assertEquals(nonDiagonalElement, actual, 1E-8);
                } // else
            } // for
        } // for
    } // testIdentity()

    @Test
    public void testRotationX() {
        System.out.println("rotationX");
        double angle = 1;
        Matrix instance = new Matrix();
        instance.rotationX(angle);
        assertEquals(.54030230, instance.get(1, 1), 1E-8);
        assertEquals(-.84147098, instance.get(1, 2), 1E-8);
        assertEquals(.84147098, instance.get(2, 1), 1E-8);
        assertEquals(.54030230, instance.get(2, 2), 1E-8);
    } // testRotationX()

    @Test
    public void testRotationY() {
        System.out.println("rotationY");
        double angle = 1;
        Matrix instance = new Matrix();
        instance.rotationY(angle);
        assertEquals(.54030230, instance.get(0, 0), 1E-8);
        assertEquals(.84147098, instance.get(0, 2), 1E-8);
        assertEquals(-.84147098, instance.get(2, 0), 1E-8);
        assertEquals(.54030230, instance.get(2, 2), 1E-8);
    } // testRotationY()

    @Test
    public void testRotationZ() {
        System.out.println("rotationZ");
        double angle = 1;
        Matrix instance = new Matrix();
        instance.rotationZ(angle);
        assertEquals(.54030230, instance.get(0, 0), 1E-8);
        assertEquals(-.84147098, instance.get(0, 1), 1E-8);
        assertEquals(.84147098, instance.get(1, 0), 1E-8);
        assertEquals(.54030230, instance.get(1, 1), 1E-8);
    } // testRotationZ()

    @Test
    public void testScale() {
        System.out.println("scale");
        double xFactor = 1;
        double yFactor = 1;
        double zFactor = 1;
        Matrix instance1 = new Matrix();
        Matrix instance2 = new Matrix();
        instance1.scale(xFactor, yFactor, zFactor);
         for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                double scaleval = instance1.get(i, j);
                double idenval = instance2.get(i, j);
                    assertEquals(idenval, scaleval, 1E-8);
            } // for
        } // for
    } // testScale()

    @Test
    public void testTranslate() {
        double deltaX = this.rng.nextDouble();
        double deltaY = this.rng.nextDouble();
        double deltaZ = this.rng.nextDouble();
        
        Matrix forward = new Matrix();
        forward.translate(deltaX, deltaY, deltaZ);

        Matrix backward = new Matrix();
        backward.translate(-deltaX, -deltaY, -deltaZ);
        
        Matrix product = forward.multiply(backward);
        Matrix identity = new Matrix();
        identity.identity();
        
        for( int i = 0; i < 4; i++ ) {
            for( int j = 0; j < 4; j++ ) {
                double a = product.get(i,j);
                double b = identity.get(i,j);
                assertEquals( a, b, EPSILON);
            } // for
        } // for
    } // testTranslate()

    @Test
    public void testMultiply_Matrix() {
        Matrix identity = new Matrix();
        identity.identity();
        
        Matrix multiplier = new Matrix();
        multiplier.identity();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                double r = rng.nextDouble();
                multiplier.set(i, j, r);
            } // for
        } // for

        Matrix product = identity.multiply( multiplier );
        
        for( int i = 0; i < 4; i++ ) {
            for( int j = 0; j < 4; j++ ) {
                double a = multiplier.get(i, j);
                double b = product.get(i, j);
                assertEquals( a, b, EPSILON );
            } // for
        } // for
    } // testMultiply_Matrix()

    @Test
    public void testMultiply_Vector() {
        Vector u = new Vector( 1.0, 0.0, 0.0 );
        Matrix r = new Matrix();
        r.rotationZ( Math.PI/4 );
        
        Vector v = r.multiply(u);
        
        double vx = v.get( 0 );
        double vy = v.get( 1 );
        double vz = v.get( 2 );
        
        double expectedResult = Math.sqrt(2.0)/2;
        
        assertEquals( vx, expectedResult, EPSILON );
        assertEquals( vy, expectedResult, EPSILON );
        assertEquals( vz, 0.0, EPSILON );
    } // testMultiply_Vector()

} // MatrixTest
