package robotsimulator.test;

import java.util.ArrayList;
import java.util.Scanner;
  
public class RobotSimulator {
	static int[][] floor;
	static int x, y;
	public static boolean penDown;
	static int facing; // 0: north, 1: east, 2: south, 3: west
	static final int MAX_SIZE = 100;
	static int size;
	private static ArrayList<String> commandHistory = new ArrayList<String>();
	
	public static void main(String[] args) {
		System.out.print("Welcome to RobotSimulator!!!\n"
				+ "Use below commands!!!\n"
				+ "[1] U or u     => Pen up\r\n"
				+ "[2] D or d     => Pen down\r\n"
				+ "[3] R or r     => Turn right\r\n"
				+ "[4] L or l     => Turn left\r\n"
				+ "[5] M s or m s => Move forward s spaces (s is a non-negative integer)\r\n"
				+ "[6] P or p     => Print the floor mapped\r\n"
				+ "[7] C or c     => Print current position of the pen and whether it is up or down and its facing direction\r\n"
				+ "[8] Q or q     => Stop the program\r\n"
				+ "[9]I n or i n  => Initialize the system: The values of the array floor are zeros and the robot\r\n"
				+ "                  is back to [0, 0], pen up and facing north. x size of the array, an integer greater than zero\r\n"           
				+ "[10] H or h    => Show all commands entered\n");
		System.out.print("Enter command: ");
		RobotSimulator.input();
	}

	public static void input() {  
		try {
		Scanner sc = new Scanner(System.in);
		String command = sc.nextLine();
		char firstchar = command.charAt(0);
			while (!((firstchar =='q')|| (firstchar =='Q'))) {
			switch (firstchar) {
			case 'i':
			case 'I':
				try {
					size = Integer.parseInt(command.substring(2).trim());
					if (size < 1 || size > 100) {
						throw new IllegalArgumentException("Please provide a valid number for floor size (1-100).");
					}
					initializeFloor(size);
					commandHistory.add(command);
					break;  
				} catch (NumberFormatException e) {
					System.out.println("Invalid input. Please enter a number.");
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 'c':
			case 'C': 
				String temp = command.substring(1).trim();
				if(temp.length() > 0) {
					System.out.println("Invalid command!! Enter Valid command again");
					break;
				}
				commandHistory.add(command);
				System.out.println("Position: (" + x + ", " + y + ") - Pen: " + (penDown ? "down" : "up") + " - Facing: "
						+ (facing == 0 ? "north" : facing == 1 ? "east" : facing == 2 ? "south" : "west"));
				break;
			case 'd':
			case 'D':
				String temp1 = command.substring(1).trim();
				if(temp1.length() > 0) {
					System.out.println("Invalid command!! Enter Valid command again");
					break;
				}
				commandHistory.add(command);
				penD();
				break;
			case 'u':
			case 'U':
				String temp2 = command.substring(1).trim();
				if(temp2.length() > 0) {
					System.out.println("Invalid command!! Enter Valid command again");
					break;
				}
				commandHistory.add(command);
				penU();
				break;
			case 'm':
			case 'M': 
				commandHistory.add(command);
				try{
					int steps = Integer.parseInt(command.substring(2).trim());
					if(steps<0) {
						throw new IllegalArgumentException("Invalid steps!! Provide integer value for steps.");
					}
					move(steps);
					break;
				}catch (NumberFormatException e) {
		            System.out.println("Invalid input. Please enter a integer number.");
				} catch (IllegalArgumentException e) {
			        System.out.println(e.getMessage());
				}
				break;
			case 'r':
			case 'R':
				String temp3 = command.substring(1).trim();
				if(temp3.length() > 0) {
					System.out.println("Invalid command!! Enter Valid command again");
					break;
				}
				commandHistory.add(command);
				turnRight();
				break;
			case 'l':
			case 'L':
				String temp4 = command.substring(1).trim();
				if(temp4.length() > 0) {
					System.out.println("Invalid command!! Enter Valid command again");
					break;
				}
				commandHistory.add(command);
				turnLeft();
				break;
			case 'p':
			case 'P':
				String temp5 = command.substring(1).trim();
			    if(temp5.length() > 0) {
				System.out.println("Invalid command!! Enter Valid command again");
				break;
			}
				commandHistory.add(command);
				 System.out.println(printFloor());
				break;
			case 'h':
			case 'H':
				String temp6 = command.substring(1).trim();
				if(temp6.length() > 0) {
					System.out.println("Invalid command!! Enter Valid command again");
					break;
				}
				commandHistory.add(command);
				System.out.println(printCommandHistory());
				break;
			default:
				System.out.println("Invalid command!! Enter Valid command again");
			}
			System.out.print("Enter command: ");
			command = sc.nextLine();
			firstchar = command.charAt(0);
		}
		commandHistory.clear();
		System.out.println("Robot is turn off");
		
	} catch(Exception e) {
		//commandHistory.clear();
		System.out.println("Wrong input!" + e.getMessage());
		
	}
}
	
	public static void initializeFloor(int size) {
	            floor = new int[size][size];
	            penDown = false;
				x = 0;
				y = 0;
				facing = 0;
	}

	static int turnRight() {
		return facing = (facing + 1) % 4;
	}

	static int turnLeft() {
		return facing = (facing + 3) % 4;
	}

	static boolean penD() {
		return penDown = true;
	}

	static boolean penU() {
		return penDown = false;
	}
	
	static boolean moveNorth(int steps) {
			int current = y + steps;
			        if (current >= size || current < 0) {
				System.out.println("Invalid move!! Robot can't move outside of the floor. Please provide valid move.");
				return false;
			}
			y += steps;
			return true;

		}
	
	public static boolean moveEast(int steps) {
		int current = x + steps;
		         if (current >= size || current < 0) {
			System.out.println("Invalid move!! Robot can't move outside of the floor. Please provide valid move.");
			return false;
		}
		x += steps;
		return true;
   
	}


	public static boolean moveSouth(int steps) {
		int current = y - steps;
		         if (current >= size || current < 0) {
			System.out.println("Invalid move!! Robot can't move outside of the floor. Please provide valid move.");
			return false;
		}
		y = y - steps;
		return true;

	}

	public static boolean moveWest(int steps) {
		int current = x - steps;
		if (current >= size || current < 0) {
			System.out.println("Invalid move!! Robot can't move outside of the floor. Please provide valid move.");
			return false;
		}
		x -= steps;
		return true;
	}
	
	public static void move(int steps) {
	   
	        boolean status = true;
	                switch (facing) {
	            case 0:
	                status = moveNorth(steps);
	                break;
	            case 1:
	                status = moveEast(steps);
	                break;  
	            case 2:
	                status = moveSouth(steps);
	                break; 
	            case 3:
	                status = moveWest(steps);
	                break;
	        }
	        if (!status) {
	            return;
	        }
	        if (penDown) {
	            for (int i = 0; i <=steps; i++) {
	                    switch (facing) {
	                    case 0:
	                        floor[y - i][x] = 1;
	                        break;
	                    case 1:
	                        floor[y][x - i] = 1;
	                        break;
	                    case 2:
	                        floor[y + i][x] = 1;
	                        break;
	                    case 3:
	                        floor[y][x + i] = 1;
	                        break;
	                }
	            }
	        }
	}
	
	public static String printCommandHistory() {
	    StringBuilder sb = new StringBuilder();
	        sb.append("Command history:\n"); 
	        for (String command : commandHistory) {
	            sb.append("- ").append(command).append("\n");
	        }
	    return sb.toString();
	}
	
	public static String printFloor() { 
	    StringBuilder sb = new StringBuilder();
	    sb.append("Displaying the Floor:\n");
	    for (int i = floor[0].length - 1; i >= 0; i--) {
	        for (int j = 0; j < floor.length; j++) {
	            sb.append(floor[i][j] == 1 ? "* " : "  ");
	        }
	        sb.append("\n");
	    }
	    return sb.toString();
	}
}
