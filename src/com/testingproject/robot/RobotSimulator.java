package com.testingproject.robot;

import java.util.Scanner;

public class RobotSimulator {
	static int[][] floor;
	static int x, y;
	static boolean penDown;
	static int facing; // 0: north, 1: east, 2: south, 3: west
	static final int MAX_SIZE = 100;

	public static void main(String[] args) {

		try {
			Scanner sc = new Scanner(System.in);

			System.out.print("Enter command: ");
			String command = sc.nextLine();

			while (!command.equals("Q")) {
				switch (command) {
				case "I":
					intializeFloor();
					break;
				case "C":
					System.out.println(
							"Position: " + x + ", " + y + " - Pen: " + (penDown ? "down" : "up") + " - Facing: "
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

		} catch (Exception e) {

			System.out.print("Please provide valid input to move robot ");
		}
	}

	public static int[][] intializeFloor() {
		Scanner sc = new Scanner(System.in);

		int size = Integer.parseInt(sc.nextLine());

		if (size <= 100) {

			floor = new int[size][size];
			penDown = false;
			facing = 0;
		} else {

			System.out.println("Please provide valid number for floor size ");

		}
		return floor;

	}

	private static void printFloor() {
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

	static void moveNorth(int num) {
		y += num;
	}

	static void moveEast(int num) {
		x += num;
	}
	
	static void moveSouth(int num) {
		y -= num;
	}
	
	static void moveWest(int num) {
		x -= num;
	}

	public static void move() {
		try {
			Scanner sc = new Scanner(System.in);
			int steps = Integer.parseInt(sc.nextLine());

			switch (facing) {
			case 0:
				moveNorth(steps);
				break;
			case 1:
				moveEast(steps);
				break;
			case 2:
				moveSouth(steps);
				break;
			case 3:
				moveWest(steps);
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





