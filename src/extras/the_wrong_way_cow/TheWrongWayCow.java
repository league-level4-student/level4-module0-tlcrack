
	//https://www.codewars.com/kata/the-wrong-way-cow
	//
	//Task
	//Given a field of cows find which one is the Wrong-Way Cow and return her position.
	//
	//Notes:
	//
	//There are always at least 3 cows in a herd
	//There is only 1 Wrong-Way Cow!
	//Fields are rectangular
	//The cow position is zero-based [x,y] of her head (i.e. the letter c)
	//Examples
	//Ex1
	//
	//cow.cow.cow.cow.cow
	//cow.cow.cow.cow.cow
	//cow.woc.cow.cow.cow
	//cow.cow.cow.cow.cow
	//Answer: [6,2]
	//
	//Ex2
	//
	//c..........
	//o...c......
	//w...o.c....
	//....w.o....
	//......w.cow
	//Answer: [8,4]
	//
	//Notes
	//The test cases will NOT test any situations where there are "imaginary" cows, so your solution does not need to worry about such things!
	//
	//To explain - Yes, I recognize that there are certain configurations where an "imaginary" cow may appear that in fact is just made of three other "real" cows.
	//In the following field you can see there are 4 real cows (3 are facing south and 1 is facing north). There are also 2 imaginary cows (facing east and west).
	//
	//...w...
	//..cow..
	//.woco..
	//.ow.c..
	//.c.....

	package extras.the_wrong_way_cow;

import java.util.HashMap;

public class TheWrongWayCow {
		
	    public static int[] findWrongWayCow(final char[][] field) {
			int cowsFacingSouth = 0;
			int cowsFacingNorth = 0;
			int cowsFacingEast = 0;
			int cowsFacingWest = 0;
			HashMap<String, int[]> cows = new HashMap<String, int[]>();
	        // Fill in the code to return the x,y coordinate position of the
	        // head (letter 'c') of the wrong way cow!
	        for(int i = 0; i<field.length; i++) {
	        	for(int j = 0; j < field[i].length; j++) {
	        		if(field[i][j]=='c') {
	        			if(i>1&&field[i-1][j]=='o'&&field[i-2][j]=='w') {
	        				cowsFacingSouth+= 1;
	        				cows.put("South", new int[] {j, i});
	        			}
	        			else if(i<field.length-2&&field[i+1][j]=='o'&&field[i+2][j]=='w') {
	        				cowsFacingNorth += 1;
	        				cows.put("North", new int[] {j, i});
	        			}
	        			else if(j>1&&field[i][j-1]=='o'&&field[i][j-2]=='w') {
	        				cowsFacingWest += 1;
	        				cows.put("East", new int[] {j, i});
	        			}
	        			else if(j<field[i].length-2&&field[i][j+1]=='o'&&field[i][j+2]=='w') {
	        				cowsFacingEast += 1;
	        				cows.put("West", new int[] {j, i});
	        			}
	        		}
	        	}
	        }

	        if(cowsFacingSouth==1) {
	        	return cows.get("South");
	        }
	        if(cowsFacingNorth==1) {
	        	return cows.get("North");
	        }
	        if(cowsFacingWest==1) {
	        	return cows.get("East");
	        }
	        if(cowsFacingEast==1) {
	        	return cows.get("West");
	        }
	        
	        return null;
	    }
	}


