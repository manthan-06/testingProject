package com.testingproject.robot;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;


        @TestMethodOrder(OrderAnnotation.class)
		public class RobotSimulatorTest {

			   
			@Test
			@Order(1)
		    public void testPenU() {
		        RobotSimulator.penDown = true;
		        RobotSimulator.penU();
		        assertFalse(RobotSimulator.penDown);
		    }	
//
		    @Test
		    @Order(2)
		    public void testPenDown() {
		        RobotSimulator.penD();
		        assertTrue(RobotSimulator.penD());
		    }

		    @Test
		    @Order(3)
		    public void testTurnRight() {
		        RobotSimulator.facing = 0;
		        RobotSimulator.turnRight(); 
		        assertEquals(1, RobotSimulator.facing);
		    }
//
		    @Test
		    @Order(4)
		    public void testTurnLeft() {
		    	  RobotSimulator.facing = 0;
		        RobotSimulator.turnLeft();
		        assertEquals(3, RobotSimulator.facing);
		    }
			
			@Test
			@Order(5)
		    public void testValidMoveEast() {
		        
				RobotSimulator.size = 10;
		        RobotSimulator.x = 3;
		        RobotSimulator.moveEast(4);
		        assertEquals(7,RobotSimulator.x);
		    }
//
		    @Test
		    @Order(6)
		    public void testInvalidMoveEast() {
		    	RobotSimulator.size = 10;
		        RobotSimulator.x = 0;
		        assertFalse(RobotSimulator.moveEast(-4));
		        assertEquals(0, RobotSimulator.x);
		    }

		    
		    @Test
		    @Order(7)
		    public void testValidMoveNorth() {
		        
				RobotSimulator.size = 10;
		        RobotSimulator.y = 3;
		        RobotSimulator.moveNorth(4);
		        assertEquals(7,RobotSimulator.y);
		    }
//
		    @Test
		    @Order(8)
		    public void testInvalidMoveNorth() {
		    	RobotSimulator.size = 10;
		        RobotSimulator.y = 0;
		        RobotSimulator.facing =0;
		        assertFalse(RobotSimulator.moveNorth(11));
		        assertEquals(0, RobotSimulator.y);
		    }

		    @Test
		    @Order(9)
		    public void testValidMoveSouth() {
		        
				RobotSimulator.size = 10; 
		        RobotSimulator.y = 7;
		        RobotSimulator.facing =2;
		        RobotSimulator.moveSouth(4);
		        assertEquals(3,RobotSimulator.y);
		    }
//
		    @Test
		    @Order(10)
		    public void testInvalidMoveSouth() {
		    	RobotSimulator.size = 10;
		        RobotSimulator.y = 0;
		        RobotSimulator.facing =2;
		        assertFalse(RobotSimulator.moveSouth(4));
		        assertEquals(0, RobotSimulator.y);
		    }
//
		    @Test
		    @Order(11)
		    public void testValidMoveWest() {
		        
				RobotSimulator.size = 10;
		        RobotSimulator.x = 7;
		        RobotSimulator.facing =3;
		        RobotSimulator.moveWest(4);
		        assertEquals(3,RobotSimulator.x);
		    }
//
		    @Test
		    @Order(12)
		    public void testInvalidMoveWest() {
		    	RobotSimulator.size = 10;
		        RobotSimulator.x = 0;
		        RobotSimulator.facing =3;
		        assertFalse(RobotSimulator.moveWest(4));
		        assertEquals(0, RobotSimulator.x);
		    } 
		    
		    @Test
			 @Order(17)
			    public void testInputMethod() {
			        //Arrange
				     RobotSimulator.input();
			         String input = "I\n20\nC\nU\nD\nM\n4\nR\nM\n4\nR\nM\n4\nL\nM\n4\nQ\n";
			        //InputStream in = new ByteArrayInputStream(input.getBytes());
			        // System.setIn(in); 
			        
			        //Act
			        //Assert
			        Assertions.assertEquals(20, RobotSimulator.size);
			        Assertions.assertTrue(RobotSimulator.penDown);
			        Assertions.assertEquals(1, RobotSimulator.facing);
			        Assertions.assertEquals(8, RobotSimulator.x);
			
			    }
// 
//		    
//		    @Test
//		    @Order(13)
//		    public void testPrintFloorEmpty() {
//		        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//		        System.setOut(new PrintStream(outContent));
//		        
//		        RobotSimulator.floor = new int[10][10]; // Empty floor
//		        
//		        RobotSimulator.printFloor();
//		        
//		        String expectedOutput = "Displaying the Floor:\n          \n          \n          \n          \n          \n          \n          \n          \n          \n          \n";
//		        Assertions.assertFalse(outContent.toString().equals(expectedOutput));
//		    }  
//			
//			
//			  @Test
//			  @Order(14)
//			    public void testInputMove() {
//			        //Arrange
//			    	//RobotSimulator.y = 0;
//			        String input = "I\n";
//			        InputStream in = new ByteArrayInputStream(input.getBytes());
//			        System.setIn(in);
//			 //       RobotSimulator.input(); 
//			        String input1 = "5\n";
//			        InputStream in1 = new ByteArrayInputStream(input1.getBytes());
//			        System.setIn(in1);
//			        RobotSimulator.intializeFloor();
//			        assertEquals(RobotSimulator.size, 5); 
//			        String input2 = "D\nM\n3\nC\nQ\n";
//			        InputStream in2 = new ByteArrayInputStream(input2.getBytes());
//			        System.setIn(in2);
//			       // RobotSimulator.input();
//			        
//			        //Act
//			        //Assert
//			        Assertions.assertEquals(5, RobotSimulator.size);
//			        Assertions.assertTrue(RobotSimulator.penD());
//			        Assertions.assertEquals(0, RobotSimulator.facing);
//			       // Assertions.assertEquals(3, RobotSimulator.y);
//			    }
//			  
//			  
//			  @Test 
//			  @Order(15)
//			    public void testInputPenD() {
//			        //Arrange
//			        String input = "I\n5\nD\nQ\n";
//			        InputStream in = new ByteArrayInputStream(input.getBytes());
//			        System.setIn(in);
//
//			        //Act
//			        //RobotSimulator.input();
//
//			        //Assert
//			        //assertEquals(5, RobotSimulator.size);
//			        assertTrue(RobotSimulator.penD());
//			    }
//			
//			  @Test
//			  @Order(16)
//			    public void testInputPenU() {
//			        //Arrange
//			        String input = "I\n5\nD\nQ\n";
//			        InputStream in = new ByteArrayInputStream(input.getBytes());
//			        System.setIn(in);
//
//			        //Act
//			        //RobotSimulator.input();
//
//			        //Assert
//			        //assertEquals(5, RobotSimulator.size);
//			        assertFalse(RobotSimulator.penU());
//			    }

				 
			
			 
			
		    
	}