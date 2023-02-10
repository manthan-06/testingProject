package com.testingproject.robot;

import java.util.Scanner;

public class RobotSimulator {
    static int[][] floor;
    static int x, y;
    static boolean penDown;
    static int facing; // 0: north, 1: east, 2: south, 3: west
    static final int MAX_SIZE = 100;

    public static void main(String[] args) {
    	
        try (Scanner sc = new Scanner(System.in)) {
			floor = new int[MAX_SIZE][MAX_SIZE];
			x = y = 0;
			penDown = false;
			facing = 0;

			System.out.print("Enter command: ");
			String command = sc.nextLine();
			
			while (!command.equals("Q")) {         
			    switch (command) {
			        case "I":
			            int size = Integer.parseInt(sc.nextLine());
			            floor = new int[size][size];

			            penDown = false;
			            facing = 0;
			            break;
			        case "C":
			            System.out.println("Position: " + x + ", " + y + " - Pen: " + (penDown ? "down" : "up") + " - Facing: " + (facing == 0 ? "north" : facing == 1 ? "east" : facing == 2 ? "south" : "west"));
			            break;
			        case "D":
			            penDown = true;
			            break;
			        case "U":
			            penDown = false;
			            break;
			        case "M":
			            move();
			            break;
			        case "R":
			        	facing = (facing + 1) % 4;
			            break;
			        case "L":
			            facing = (facing + 3) % 4;
			            break;                  
			        case "P":
			        	printFloor();
			            break;

			        default:
			            System.out.println("Invalid command");
			    }
			    System.out.print("Enter command: ");
			    command = sc.nextLine();
			}
			
		} catch (Exception e) {
			
			System.out.println("Please provide valid number for floor size ");
		}
    }
    
    private static void printFloor() {
    	System.out.println("Displaying the Floor:");
    	for (int i = floor[0].length - 1; i >= 0; i--) {
        	
            for (int j = 0; j <floor.length ; j++) {
                System.out.print(floor[i][j] == 1 ? "* " : "  ");
            }
            System.out.println();
        }
    }
     private static void move() {
    	 try {
    		 Scanner sc = new Scanner(System.in);
			int steps = Integer.parseInt(sc.nextLine());
			 
			 switch (facing) {
			     case 0:
			         y += steps;
			         break;
			     case 1:
			         x += steps;
			         break;
			     case 2:
			         y -= steps;
			         break;
			     case 3:
			         x -= steps;
			         break;
			 }
			 if (penDown) {
			 	for (int i = 0; i < steps; i++) {
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
				 
    	 } catch (Exception e) {
			
			System.out.println("INVALID MOVE : ROBOT IS OUTSIDE OF THE FLOOR");
		}  
    }
}
