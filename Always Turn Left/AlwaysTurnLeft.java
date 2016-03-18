/*
	Hammad Khalid
	Google Code Jam: "Always Turn Left"
*/

import java.util.*;
import java.io.File;

public class AlwaysTurnLeft {
	
	public static void main(String [] args) {
		MazeSolver mazeSolver = new MazeSolver();
	}	
}

// The Maze class gets an input for the "Always Turn Left"
// problem and prints out a solution to each case.
class MazeSolver {
	
	// Member Variables
	private final String filename = "B-small-practice.in";
	//private final String filename = "input2.txt";
	private Scanner filein;
	//private Maze[][];
	private String[] paths;
	private HashMap directions;
	private	Room[] rooms;
	private String entrance_to_exit;
	private String exit_to_entrance;
	
	// String constants represeting possible directions
	// in individual rooms
	private final String N = "1000";
	private final String S = "0100";
	private final String NS = "1100";
	private final String W = "0010";
	private final String NW = "1010";
	private final String SW = "0110";
	private final String NSW = "1110";
	private final String E = "0001";
	private final String NE = "1001";
	private final String SE = "0101";
	private final String NSE = "1101";
	private final String WE = "0011";
	private final String NWE = "1011";
	private final String SWE = "0111";
	private final String NSWE = "1111";
	
	// Strings representing direction we're heading in
	private final String n = "N";
	private final String s = "S";
	private final String w = "W";
	private final String e = "E";	
	
	// Integers representing directions we are heading in
	/*private final int N = 0;
	private final int W = 0;
	private final int E = 0;
	private final int S = 0;	*/
	
	// String representing current direction
	private String heading;
	
	public MazeSolver() {
		initMap();
		initRooms();
		readIn();	
		move();
		print();
		//calculateDimensions();
		//solve();
	}
	public void initRooms() {
		rooms = new Room[100];
		for(int i = 0; i < rooms.length; i++) {
			rooms[i] = new Room();
		}
	}
	public void initMap() {
		directions = new HashMap();
		directions.put("1000", "1");
		directions.put("0100", "2");
		directions.put("1100", "3");
		directions.put("0010", "4");
		directions.put("1010", "5");
		directions.put("0110", "6");
		directions.put("1110", "7");
		directions.put("0001", "8");
		directions.put("1001", "9");
		directions.put("0101", "a");
		directions.put("1101", "b");
		directions.put("0011", "c");
		directions.put("1011", "d");
		directions.put("0111", "e");
		directions.put("1111", "f");
	}
	
	public void readIn() {
		try {
			
			File inFile = new File(filename);
			filein = new Scanner(inFile);
			
			int numCases = 0;
			int i = 0;
			
			if(filein.hasNextInt())
				numCases = Integer.parseInt(filein.nextLine());
			
			print("numCases: " + numCases);
			/*
			paths = new String[numCases * 2];

			while(i < paths.length) {
				if(filein.hasNextLine())
					paths[i] = filein.nextLine();
				i++;
			}
			
			for(i = 0; i < paths.length; i++) {
				print("paths[" + i + "] = " + paths[i]);
			}
			*/
		} catch(Exception e) {
			e.printStackTrace();
			print("Can't open file");
		}
	}
	
	// Function to move from room to room
	public void move() {
		if(filein.hasNextLine()) {
			entrance_to_exit = filein.next();
			print("entrance_to_exit : " + entrance_to_exit); 
			
			// For the first room
			switch(entrance_to_exit.charAt(1)) {
				case 'W':
					rooms[0].setDirection(NS);
					heading = n;
					break;
				case 'L':
					rooms[0].setDirection(SW);	
					heading = w;
					break;
				case 'R':
					rooms[0].setDirection(SE);
					heading = e;
					break;
			}
			
			// For the rest of the rooms
			int pos = 2;
			int num = 2;
			while(pos < entrance_to_exit.length()) {
				print("Direction is : " + entrance_to_exit.charAt(pos));
				print("Room is " + num);
				switch(heading) {
					case "N": {
						switch (entrance_to_exit.charAt(pos)) {
							case 'W':
								rooms[num].setDirection(NS);
								break;
							case 'L':
								rooms[num].setDirection(SW);
								heading = w;
								break;
							case 'R':
								rooms[num].setDirection(SE);
								heading = e;
								break;
						}
						break;
					}
					case "S": {
						switch (entrance_to_exit.charAt(pos)) {
							case 'W':
								rooms[num].setDirection(NS);
								break;
							case 'L':
								rooms[num].setDirection(NE);
								heading = e;
								break;
							case 'R':
								rooms[num].setDirection(NW);
								heading = w;
								break;
						}
						break;
					}
					case "W": {
						switch (entrance_to_exit.charAt(pos)) {
							case 'W':
								rooms[num].setDirection(WE);
								break;
							case 'L':
								rooms[num].setDirection(SE);
								heading = s;
								break;
							case 'R':
								rooms[num].setDirection(NE);
								heading = n;
								break;
						}
						break;
					}
					case "E": {
						switch (entrance_to_exit.charAt(pos)) {
							case 'W':
								rooms[num].setDirection(WE);
								break;
							case 'L':
								rooms[num].setDirection(NW);
								heading = n;
								break;
							case 'R':
								rooms[num].setDirection(SW);
								heading = s;
								break;
						}
						break;
					}
				}
				if(entrance_to_exit.charAt(pos) == 'W')
					num++;
				pos++;
				//print("entrance_to_exit.length = " + entrance_to_exit.length() + "\npos = " + pos);
			}
		}
	}
	
	public void print() {
		for(int i = 0; i < rooms.length; i++) {
			//print(rooms[i].getDirection());
			if(rooms[i].getDirection() == N)
				print("1");
			else if(rooms[i].getDirection() == S)
				print("2");
			else if(rooms[i].getDirection() == NS)
				print("3");
			else if(rooms[i].getDirection() == W)
				print("4");
			else if(rooms[i].getDirection() == NW)
				print("5");
			else if(rooms[i].getDirection() == SW)
				print("6");
			else if(rooms[i].getDirection() == NSW)
				print("7");
			else if(rooms[i].getDirection() == E)
				print("8");
			else if(rooms[i].getDirection() == NE)
				print("9");
			else if(rooms[i].getDirection() == SE)
				print("a");
			else if(rooms[i].getDirection() == NSE)
				print("b");
			else if(rooms[i].getDirection() == NE)
				print("c");
			else if(rooms[i].getDirection() == NWE)
				print("d");
			else if(rooms[i].getDirection() == SWE)
				print("e");
			else if(rooms[i].getDirection() == NSWE)
				print("f");
			else if(rooms[i].getDirection() == "0000")
				break;
		}
	}
	
	// Function to calculate the dimensions of the mazes
	public void calculateDimensions() {
		
		int width = 1, 
			height = 0;
		int west = 0;
		int east = 0;
		int north = 0;
		int south = 0;
		int left = 0;
		int right = 0;
		
		for(int i = 0; i < paths.length; i++) {
			String path = paths[i];
			for(int j = 0; j < path.length(); j++) {
				switch(path.charAt(j)) {
					case 'W':
						// To determine height
						if(left == 0 && right == 0)  {
							north++;
							if(north > south)
								height++;
						}
						
						// If heading E/W
						if(left > 0 || right > 0) {
							// Check if heading west
							if(left > 0) {
								// Check if heading to N/S
								if(left % 2 == 0) {
									south++;
									if(south > north)
										height++;
								}
								else {
									west++;
									if(west > east)
										width++;
								}
							}
							if(right > 0) {
								if(right % 2 == 0) {
									south++;
									if(south > north)
										height++;
								}
								else {
									east++;
									if(east > west)
										width++;
								}
							}
						}
						
						break;
						
					case 'L':
						// To figure out direction
						if (right == 0) left++; 
						else right--;
						//if(left == 2) left = 0;
						
						break;
						
					case 'R':
						// To figure out direction
						if (left == 0) right ++;
						else left--;
						//if(right == 2) right = 0;
						
						break;
					
					default:
						// Skip the space
						break;
				}
			}
		}
		print("Dimensions of maze are: " + width + " X " + height);
	}
	
	public void solve() {
		String[][] directions = new String[new Random().nextInt(10) + 1][new Random().nextInt(10) + 1];
		for(int i = 0; i < paths.length; i++) {
			String path = paths[i];
			print("path at " + i + " is " + path);
			for(int j = 0; j < path.length(); j++) {
				print("j = " + j);
				String pair = Character.toString(path.charAt(j)) + Character.toString(path.charAt(j+1));
				print("first letter is: " + Character.toString(path.charAt(j)));
				if(j+1 <= path.length())
					print("second letter is: " + Character.toString(path.charAt(j+1)));
				else
					break;
				print("Pair is : " + pair);
				switch(pair) {
					case "WW":
					//	print(directions.get("0011"));
						break;
					case "WR":
					//	print(directions.get("0101"));
						break;
					case "WL":
					//	print(directions.get("0011"));
						break;
					case "RR":
					//	print(directions.get("0001"));
						break;
					case "LW":
						break;
					case "RW":
						break;
					default:
						j++;
						break;
				}
			}
		}
	}
	
	// Wrapper function for System.out.println()
	public void print(String output) {
		System.out.println(output);
	}
}

// The room class represents individual 
// rooms in the maze
class Room {
	
	// Member vairables
	private boolean north;
	private boolean west;
	private boolean south;
	private boolean east;
	
	private String direction;
	
	public Room() {
		direction = new String("0000");
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getDirection() {
		return direction;
	}
	public void setNorth(boolean val) {
		north = val;
	}
	public void setWest(boolean val) {
		west = val;
	}
	public void setEast(boolean val) {
		east = val;
	}
	public void setSouth(boolean val) {
		south = val;
	}
	public boolean getNorth() {
		return north;
	}
	public boolean getWest() {
		return west;
	}
	public boolean getSouth() {
		return south;
	}
	public boolean getEast() {
		return east;
	}
}