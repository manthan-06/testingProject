package com.testingproject.robot;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class RobotSimulatorTest {
    @Before
    public void setup() {
        new RobotSimulator();
    }

    @Test
    public void testPenD() {
        RobotSimulator.penD();
        assertTrue(RobotSimulator.penDown);
    }
    
    @Test
    public void testPenU() {
        RobotSimulator.penU();
        assertTrue(RobotSimulator.penDown==false);
    }
    
    @Test
    public void testMoveNorth() {
    	RobotSimulator.moveNorth(5);
    	assertEquals(5, RobotSimulator.y);
    }
    
    @Test
    public void testMoveEast() {
    	RobotSimulator.moveEast(4);
    	assertEquals(4, RobotSimulator.x);
    }

    @Test
    public void testTurnRight() {
        int facing = RobotSimulator.turnRight();
        assertEquals(1, facing);
        facing = RobotSimulator.turnRight();
        assertEquals(2, facing);
    }
}