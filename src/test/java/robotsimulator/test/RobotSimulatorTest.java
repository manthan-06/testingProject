package robotsimulator.test;

import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
      
		public class RobotSimulatorTest {
			
			private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		    private final PrintStream originalOut = System.out;

		    @After
		    public void clearConsole() throws Exception {
		        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();  
		    }
			@Test
		    public void testPenU() {
		        RobotSimulator.penDown = true;
		        RobotSimulator.penU();
		        assertFalse(RobotSimulator.penDown);
		    }		
		    @Test
		    public void testPenDown() {
		        RobotSimulator.penD();
		        assertTrue(RobotSimulator.penD());
		    }

		    @Test 
		    public void testTurnRight() {
		        RobotSimulator.facing = 0;
		        RobotSimulator.turnRight(); 
		        assertEquals(1, RobotSimulator.facing);
		    }
		    @Test
		    public void testTurnLeft() {
		    	  RobotSimulator.facing = 0;
		        RobotSimulator.turnLeft();
		        assertEquals(3, RobotSimulator.facing);
		    }		
			@Test
		    public void testValidMoveEast() {
		        
				RobotSimulator.size = 10;
		        RobotSimulator.x = 3;
		        RobotSimulator.moveEast(4);
		        assertEquals(7,RobotSimulator.x);
		    }
		    @Test
		    public void testInvalidMoveEast() {
		    	RobotSimulator.size = 10;
		        RobotSimulator.x = 0;
		        assertFalse(RobotSimulator.moveEast(-4));
		        assertEquals(0, RobotSimulator.x);
		    }	    
		    @Test
		    public void testValidMoveNorth() {
		        
				RobotSimulator.size = 10;
		        RobotSimulator.y = 3;
		        RobotSimulator.moveNorth(4);
		        assertEquals(7,RobotSimulator.y);
		    }
		    @Test
		    public void testInvalidMoveNorth() {
		    	RobotSimulator.size = 10;
		        RobotSimulator.y = 0;
		        RobotSimulator.facing =0;
		        assertFalse(RobotSimulator.moveNorth(11));
		        assertEquals(0, RobotSimulator.y);
		    }
		    @Test
		    public void testValidMoveSouth() {
		        
				RobotSimulator.size = 10; 
		        RobotSimulator.y = 7;
		        RobotSimulator.facing =2;
		        RobotSimulator.moveSouth(4);
		        assertEquals(3,RobotSimulator.y);
		    }
		    @Test
		    public void testInvalidMoveSouth() {
		    	RobotSimulator.size = 10;
		        RobotSimulator.y = 0;
		        RobotSimulator.facing =2;
		        assertFalse(RobotSimulator.moveSouth(4));
		        assertEquals(0, RobotSimulator.y);
		    }
		    @Test
		    public void testValidMoveWest() {
		        
				RobotSimulator.size = 10;
		        RobotSimulator.x = 7;
		        RobotSimulator.facing =3;
		        RobotSimulator.moveWest(4);
		        assertEquals(3,RobotSimulator.x);
		    }
		    @Test
		    public void testInvalidMoveWest() {
		    	RobotSimulator.size = 10;
		        RobotSimulator.x = 0;
		        RobotSimulator.facing =3;
		        assertFalse(RobotSimulator.moveWest(4));
		        assertEquals(0, RobotSimulator.x);
		    } 
		       @Test
		       public void testPrintFloorEmpty() {
		        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		        System.setOut(new PrintStream(outContent));
		        
		        RobotSimulator.floor = new int[3][3]; // Empty floor
		        
		        RobotSimulator.printFloor();
		        
		        String expectedOutput = "Displaying the Floor:\n          \n          \n          \n";
		       assertFalse(outContent.toString().equals(expectedOutput));
		    }  
		        @Test
			    public void testMoveWithWrongInput() {
			        // Prepare input with wrong command
			        String input = "M\nQ\n";
			        InputStream in = new ByteArrayInputStream(input.getBytes());
			        System.setIn(in);
			        ByteArrayOutputStream out = new ByteArrayOutputStream();
			        System.setOut(new PrintStream(out)); 
			        RobotSimulator.input();
			        String output = out.toString();
			        assertTrue(output.contains("Wrong input!begin 2, end 1, length 1"));
			        System.setIn(System.in);
				     System.setOut(originalOut);
			    } 
		       @Test
			    public void testMoveWithNegativeInput() {
			        // Prepare input with wrong command
			        String input = "M -2\nQ\n";
			        InputStream in = new ByteArrayInputStream(input.getBytes());
			        System.setIn(in);
			        ByteArrayOutputStream out = new ByteArrayOutputStream();
			        System.setOut(new PrintStream(out));
			        RobotSimulator.input();
			        String output = out.toString();
			        assertTrue(output.contains("Invalid steps!! Provide integer value for steps."));
			        System.setIn(System.in);
				     System.setOut(originalOut);
			    }	  
			   @Test 
			    public void testIntializerWrongInput() {
			        //Arrange
			        String input = "I 0\nI 200\nI 5\nQ";
			        InputStream in = new ByteArrayInputStream(input.getBytes());  
			            System.setIn(in);
			            System.setOut(new PrintStream(outContent));
			            RobotSimulator.input();
			            String output = outContent.toString();
			            assertTrue(output.contains("Please provide a valid number for floor size (1-100)."));     
			            System.setIn(System.in);     // Restore original System.in and System.out
			            System.setOut(originalOut);
			        }	    
			   @Test
			    public void testMoveWithPenDown() { 
			        //Arrange
			        String input = "I 5\nD\nR\nM 4\nL\nM 4\nL\nM 3\nL\nM 3\nL\nM 2\nP\nQ";
			        InputStream in = new ByteArrayInputStream(input.getBytes());     
			            System.setIn(in);
			            System.setOut(new PrintStream(outContent));
			            //Act
			            RobotSimulator.input();
			            RobotSimulator.floor = new int[5][5];
			            RobotSimulator.printFloor();            
			            //Assert
			            String output = outContent.toString();
			            assertTrue(output.contains("  * * * * \n"+ "  *     * \n" + "  *     * \n"   + "  * * * * \n" + "* * * * * \n"));                     
			            System.setIn(System.in);
			            System.setOut(originalOut);
			        }    
			    @Test
			    public void testMoveWithPenUp() {
			        //Arrange
			        String input = "I 5\nR\nM 4\nL\nM 4\nL\nM 3\nL\nC\nM 3\nL\nM 2\nP\nQ";
			        InputStream in = new ByteArrayInputStream(input.getBytes());       
			            System.setIn(in);
			            System.setOut(new PrintStream(outContent));
			            RobotSimulator.input(); 
			            RobotSimulator.floor = new int[5][5];
			            RobotSimulator.printFloor();            
			            //Assert
			            String output = outContent.toString();
			            String expected = "Position: (1, 4) - Pen: up - Facing: south";
			            assertTrue(output.contains("          \n" + "          \n" + "          \n"  + "          \n" + "          \n"));
			            assertTrue(output.contains(expected));
			            System.setIn(System.in);
			            System.setOut(originalOut);
			        }
			    @Test
				public void testCommandHistoryAllCommand() {
					// Arrange
					String input = "I 5\nU\nD\nM 4\nL\nR\nR\nM 4\nC\nR\nM 3\nR\nM 3\nR\nM 2\nP\nH";
					InputStream in = new ByteArrayInputStream(input.getBytes());
					System.setIn(in);
					System.setOut(new PrintStream(outContent));
					RobotSimulator.input(); 
					RobotSimulator.floor = new int[5][5];
					RobotSimulator.printFloor();
					RobotSimulator.printCommandHistory();
					String output = outContent.toString();
					assertTrue(output
							.contains("* * * * * \n" + "* *     * \n" + "* *     * \n" + "* * * * * \n" + "*         \n"));
					String expected = "Command history:\n"
					    + "- I 5\n"
						+ "- U\n"
						+ "- D\n"
						+ "- M 4\n"
						+ "- L\n"
						+ "- R\n"
						+ "- R\n"
						+ "- M 4\n"
						+ "- C\n"
						+ "- R\n"
						+ "- M 3\n"
						+ "- R\n"
						+ "- M 3\n"
						+ "- R\n"
						+ "- M 2\n"
						+ "- P\n"
						+ "- H\n";
					assertTrue(RobotSimulator.printCommandHistory().contains(expected));
					//assertEquals(expected, RobotSimulator.printCommandHistory());
					System.setIn(System.in); // Restore original System.in and System.out
					System.setOut(originalOut);
				}    
			    
			   
			    @Test
				public void testTurnLeftInvalid() {
				 RobotSimulator.facing = 2;
				 RobotSimulator.turnLeft();
					assertFalse(RobotSimulator.facing == 2);
				} 
				@Test
				public void testTurnRightInvalid() { 
					 RobotSimulator.facing = 2;
					 RobotSimulator.turnRight();
					 assertFalse(RobotSimulator.facing == 2);
				} 
					@Test
					public void testturnLeft() {
						 RobotSimulator.facing = 0;
						 RobotSimulator.turnLeft();
					   assertEquals(3, RobotSimulator.facing);
					   RobotSimulator.turnLeft();
					   assertEquals(2, RobotSimulator.facing);
					   RobotSimulator.turnLeft();
					   assertEquals(1, RobotSimulator.facing);
					   RobotSimulator.turnLeft();
					   assertEquals(0, RobotSimulator.facing);
					}
					@Test
					public void testturnRight() {
						RobotSimulator.facing = 0;
						RobotSimulator.turnRight();
					   assertEquals(1, RobotSimulator.facing);
					   RobotSimulator.turnRight();
					   assertEquals(2, RobotSimulator.facing);
					   RobotSimulator.turnRight();
					   assertEquals(3, RobotSimulator.facing);
					   RobotSimulator.turnRight();
					   assertEquals(0, RobotSimulator.facing);
					}					
					@Test
					public void moveEastPenUp() {
						String input = "I 4\nU\nr\nm 2\nC\nQ";
						InputStream in = new ByteArrayInputStream(input.getBytes());
						System.setIn(in);
						System.setOut(new PrintStream(outContent));
						RobotSimulator.input();
						// expected result
						String expected = "Position: (2, 0) - Pen: up - Facing: east";
						String output = outContent.toString();
						assertTrue(output.contains(expected));
						System.setIn(System.in); // Restore original System.in and System.out
					    System.setOut(originalOut);
					}					
				
		    
					@Test
					public void moveWithPenUp() {
						String input = "i 4\nD\nM 1\nU\nM 2\nD\nR\nM 1\nU\nM 2\nR\nM 2\nD\nM 1\nU\nR\nM 1\nD\nM 1\nP\nC\nQ";
						InputStream in = new ByteArrayInputStream(input.getBytes());
						System.setIn(in);
						System.setOut(new PrintStream(outContent));
						RobotSimulator.input();
						// expected result
						String expected = "Displaying the Floor:\n" + "* *     \n" + "        \n" + "*     * \n"
								+ "* * * * \n";
						// call the method and compare with the expected result
						String output = outContent.toString();
						assertEquals(expected, RobotSimulator.printFloor());
						String expected1 = "Position: (1, 0) - Pen: down - Facing: west";
						assertTrue(output.contains(expected1));
						System.setIn(System.in); // Restore original System.in and System.out
						System.setOut(originalOut);
					}
					@Test
					public void testExceptionHandleInMove() {
						String input = "I x\nI 5\nd\nM x\nM 3\nR\nM 1.0f\nM 3\nR\nM x\nM 2\nC\nR\nM x\nM 2\nQ";
						InputStream in = new ByteArrayInputStream(input.getBytes());
						System.setIn(in);
						System.setOut(new PrintStream(outContent));
						RobotSimulator.input();
						// expected result
						String expected = "Position: (3, 1) - Pen: down - Facing: south";
						String output = outContent.toString();
						assertTrue(output.contains(expected));
						assertTrue(output.contains("Invalid input. Please enter a integer number."));
			           System.setIn(System.in); // Restore original System.in and System.out
				       System.setOut(originalOut);
				}	
				  
					@Test
					public void testExceptionHandleInMove3() {
						String input = "I 5\nd\nM -4\nM 3\nC\nQ";
						InputStream in = new ByteArrayInputStream(input.getBytes());
						System.setIn(in);
						System.setOut(new PrintStream(outContent));
						RobotSimulator.input();
						// expected result
						String expected = "Position: (0, 3) - Pen: down - Facing: north";
						String output = outContent.toString();
						assertTrue(output.contains("Invalid steps!! Provide integer value for steps."));
						assertTrue(output.contains(expected));
			           System.setIn(System.in); // Restore original System.in and System.out
				       System.setOut(originalOut);
				}
					
					@Test
					public void testOutofFloorCondition() {
						String input = "I 5\nd\nM 10\nM 3\nR\nM 10\nM 3\nR\nM 10\nM 2\nR\nM 10\nM 2\nC\nQ";
						InputStream in = new ByteArrayInputStream(input.getBytes());
						System.setIn(in);
						System.setOut(new PrintStream(outContent));
						RobotSimulator.input();
						// expected result
						String expected = "Position: (1, 1) - Pen: down - Facing: west";
						String output = outContent.toString();
						assertTrue(output.contains("Invalid move!! Robot can't move outside of the floor. Please provide valid move."));
						assertTrue(output.contains(expected));
			           System.setIn(System.in); // Restore original System.in and System.out
				       System.setOut(originalOut);
				}	
					@Test
					public void testOutofFloorCondition2() {
						String input = "I 5\nd\nM 10\nM 3\nL\nM 10\nL\nM 10\nM 2\nL\nM 10\nM 2\nC\nQ";
						InputStream in = new ByteArrayInputStream(input.getBytes());
						System.setIn(in);
						System.setOut(new PrintStream(outContent));
						RobotSimulator.input();
						// expected result
						String expected = "Position: (2, 1) - Pen: down - Facing: east";
						String output = outContent.toString();
						assertTrue(output.contains("Invalid move!! Robot can't move outside of the floor. Please provide valid move."));
						assertTrue(output.contains(expected));
			           System.setIn(System.in); // Restore original System.in and System.out
				       System.setOut(originalOut);
				}	
					@Test
					public void testExceptioninInput() {
						String input = "I 5\nd\nM\nQ";
						InputStream in = new ByteArrayInputStream(input.getBytes());
						System.setIn(in);
						System.setOut(new PrintStream(outContent));
						RobotSimulator.input();
						// expected result
						String output = outContent.toString();
						assertTrue(output.contains("Wrong input!begin 2, end 1, length 1"));
			           System.setIn(System.in); // Restore original System.in and System.out
				       System.setOut(originalOut);
					}
					
					
					@Test
					public void testWrongInput() {
						// Prepare input with wrong command
						String input = "k\nQ\n";
						InputStream in = new ByteArrayInputStream(input.getBytes());
						System.setIn(in);
						System.setOut(new PrintStream(outContent));
						RobotSimulator.input();
						String output = outContent.toString();
						// Verify that the output contains the error message
						assertTrue(output.contains("Invalid command!! Enter Valid command again"));
						System.setIn(System.in);
						System.setOut(originalOut);
					}
					
					@Test
					public void testWrongCommand() {
						String input = "Cw\nDw\nUw\nRw\nLwwww\nPwww\nHww\nIwww\nMww";
						InputStream in = new ByteArrayInputStream(input.getBytes());
						System.setIn(in);
						System.setOut(new PrintStream(outContent));
						RobotSimulator.input();
						// expected result
						String expected1 = "Invalid input. Please enter a number.";
						String expected2 = "Invalid input. Please enter a integer number.";
						String expected3 = "Invalid command!! Enter Valid command again";
						String output = outContent.toString();
						assertTrue(output.contains(expected1));
						assertTrue(output.contains(expected2));
						assertTrue(output.contains(expected3));
						System.setIn(System.in); // Restore original System.in and System.out
					    System.setOut(originalOut);
					}
		}
	
			
		    
	
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
//		@Test
//		public void testNumberFormateException1() {
//			String input = "I i\nI 5\nd\nM x\nM 2\nC";
//			InputStream in = new ByteArrayInputStream(input.getBytes());
//			System.setIn(in);
//			System.setOut(new PrintStream(outContent));
//			RobotSimulator.input();
//			// expected result
//			String expected1 = "Position: (0, 2) - Pen: down - Facing: north";
//			String[] lines = outContent.toString().trim().split("\n");
//			String output = outContent.toString();
//			assertTrue(output.contains("Invalid input. Please enter a number."));
//			for (int i = 0; i < lines.length; i++) {
//				String s1 = lines[i];
//				if (s1.equals(expected1.trim())) {
//					assertEquals(expected1.trim(), lines[i]);
//					break;
//				} 
//			}
//           System.setIn(System.in); // Restore original System.in and System.out
//	       System.setOut(originalOut);
//	}		
		    
		    
		    
		    
		// @Test
//			public void testEmptyCommandHistory() {
//				// Arrange
//				String input = "H\nQ";
//				InputStream in = new ByteArrayInputStream(input.getBytes());
//				System.setIn(in);
//				System.setOut(new PrintStream(outContent));
//				RobotSimulator.printCommandHistory();
//			//	String output = outContent.toString();
//				String expected = "Command history is empty";
//			//	assertTrue(RobotSimulator.printCommandHistory().contains(expected));
//				assertEquals(expected, RobotSimulator.printCommandHistory());
//				System.setIn(System.in); // Restore original System.in and System.out
//				System.setOut(originalOut);
//			}   
		
		
//		@Test
//		public void testExceptionHandleInMove2() {
//			String input = "I 5\nd\nM 1.0f\nM 3\nC\nQ";
//			InputStream in = new ByteArrayInputStream(input.getBytes()); 
//			System.setIn(in);
//			System.setOut(new PrintStream(outContent));
//			RobotSimulator.input();
//			// expected result
//			String expected = "Position: (0, 3) - Pen: down - Facing: north";
//			String output = outContent.toString();
//			assertTrue(output.contains("Invalid input. Please enter a integer number."));
//			assertTrue(output.contains(expected));
//           System.setIn(System.in); // Restore original System.in and System.out
//	       System.setOut(originalOut);
//	}