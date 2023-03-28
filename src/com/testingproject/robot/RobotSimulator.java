package com.testingproject.robot;

import java.util.Scanner;

public class RobotSimulator {
	static int[][] floor;
	static int x, y;
	static boolean penDown;
	static int facing; // 0: north, 1: east, 2: south, 3: west
	static final int MAX_SIZE = 100;
	static int size;

	public static void main(String[] args) {

		RobotSimulator.input();
	}

	public static void input() {
 
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter command: ");
		String command = sc.nextLine();

		while (!command.equals("Q")) {
			switch (command) {
			case "I":
				intializeFloor();
				break;
			case "C": 
				System.out.println("Position: " + x + ", " + y + " - Pen: " + (penDown ? "down" : "up") + " - Facing: "
						+ (facing == 0 ? "north" : facing == 1 ? "east" : facing == 2 ? "south" : "west"));
				break;
			case "D":
				penD();
				break;
			case "U":
				penU();
				break;
			case "M":
				move();
				break;
			case "R":
				turnRight();
				break;
			case "L":
				turnLeft();
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

	}

	public static int[][] intializeFloor() {
		Scanner sc = new Scanner(System.in);

		size = Integer.parseInt(sc.nextLine());

		if (size <= 100) {

			floor = new int[size][size]; 
			penDown = false;
			facing = 0;
		} else {

			System.out.println("Please provide valid number for floor size ");

		}
		return floor;

	}

	public static void printFloor() {
		System.out.println("Displaying the Floor:");
		for (int i = floor[0].length - 1; i >= 0; i--) {

			for (int j = 0; j < floor.length; j++) {
				System.out.print(floor[i][j] == 1 ? "* " : "  ");
			}
			System.out.println();
		}
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
			System.out.println("Invalid move");
			return false;
		}
		y += steps;
		return true;

	}

	public static boolean moveEast(int steps) {
		int current = x + steps;
		if (current >= size || current < 0) {
			System.out.println("Invalid move");
			return false;
		}
		x += steps;
		return true;
  
	}

	public static boolean moveSouth(int steps) {
		int current = y - steps;
		if (current >= size || current < 0) {
			System.out.println("Invalid move");
			return false;
		}
		y = y - steps;
		return true;

	}

	public static boolean moveWest(int steps) {
		int current = x - steps;
		if (current >= size || current < 0) {
			System.out.println("Invalid move");
			return false;
		}
		x -= steps;
		return true;

	}

	public static void move() {
		Scanner sc = new Scanner(System.in);
		int steps = Integer.parseInt(sc.nextLine());

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
		if (!status)
			return;
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

	}
}
