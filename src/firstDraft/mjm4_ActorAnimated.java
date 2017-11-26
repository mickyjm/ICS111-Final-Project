package firstDraft;
import java.util.*;
import java.io.*;

/**
 * 
 * Project 3 Actor Class
 * @author Michael Mangrobang
 *
 **/

public class mjm4_ActorAnimated {

	// Declare Actor Pictures (FlipBook) Variables
	private FlipBook actor;
	
	// Declare Scanner and String "command" for script reading
	private Scanner scan;
	private String command;

	// Declare Position variables for Interpolation
	private int x, y, startx, starty, destx, desty, posy, posx;

	// Declare Rotation variables for Interpolation
	private int angle, newangle, angleA, angleB;

	// Declare Scale Variables for Interpolation
	private float scale, newscale, scaleA, scaleB;

	// Declare Interpolation variables
	private long starttime, duration;
	private boolean interpolation;
	
	// End script reading
	private boolean end;
	
	private long newdur;

	public mjm4_ActorAnimated(String scriptText, String[] filenames, long dur, int setx, int sety) throws java.io.IOException {

		// Initialize Variables
		actor = new FlipBook(filenames, dur, setx, sety);
		dur = newdur;
		scan = new Scanner(new FileReader(scriptText));
		x = setx;
		y = sety;
		interpolation = false;
		end = false;
		posx = x;
		posy = y;
		newscale = 1;
		newangle = 0;
		scale = 1;

	}
	
	// Script Reading for Movement, Scaling, & Rotating Starts Here
	public void go() {

		// Initialize Actor Animation
		actor.go();
		
		// If end is true
		if (end) {
			
			// Then return and end script reading
			return;
			
		}
		
		// If Interpolation is false
		if (interpolation == false) {

			// Then read script command
			command = scan.next();

			// Check script command for new case
			switch (command) {
				
			// Move + Scale + Rotate **(mo)ve + (sc)ale + (ro)tate = (moscro)**
			case "moscro":

				// Jason Leigh suggested the script to have dashes or other symbols
				// If I did not want to type the same numbers in the script again
				// So I made these "if, else if" statements
				
				/* Check for new X Position integer */
				if (scan.hasNextInt()) {
					
					posx = scan.nextInt();
					
				// Else if there is "-" instead of integer, then X Position integer remains the same
				} else if (scan.next() == "-") {
					// Do nothing					
				}
				
				/* Check for new Y Position integer */
				if (scan.hasNextInt()) {
					
					posy = scan.nextInt();

				// Else if there is "-" instead of integer, then Y Position integer remains the same
				} else if (scan.next() == "-") {
					// Do nothing
				}
				
				/* Check for new Scale Size float */
				if (scan.hasNextFloat()) {
					
					newscale = scan.nextFloat();

				// Else if there is "-" instead of float, then Scale Size float remains the same
				} else if (scan.next() == "-") {
					// Do nothing
				}
				
				/* Check for new Angle integer */
				if (scan.hasNextInt()) {
					
					newangle = scan.nextInt();

				// Else if there is "-" instead of integer, then Angle integer remains the same
				} else if (scan.next() == "-") {
					// Do nothing
				}
				
				/* Check for new Duration long */
				if (scan.hasNextLong()) {
					
					duration = scan.nextLong();
					
				// Else if there is "-" instead of long, then Duration long remains the same
				} else if (scan.next() == "-") {
					// Do nothing
				}
				
				// Print Check (Prints out variables to check if script is being read)
				//System.out.print("Moving to: (" + posx);
				//System.out.print("," + posy);
				//System.out.print("), Scaling by: " + newscale*100);
				//System.out.print("%, Rotating to: " + newangle);
				//System.out.println(" degrees, in MilliSeconds: " + duration);
				
				// Set new control variables 
				setControl(posx, posy, newscale, newangle, duration);
				break;
				
			// Stop FlipBook Animation
			case "stop":
				actor.stop();
				break;
				
			// Start FlipBook Animation
			case "start":
				actor.start();
				break;
			
			// Hide FlipBook Actor
			case "hide":
				actor.hide();
				break;
				
			// Show FlipBook Actor	
			case "show":
				actor.show();
				break;
				
			// End Script Reading
			case "fin":
				
				end = true;
				//System.out.println("Script Fin");
				break;

			}

			// Else if interpolation is true
		} else {

			// Then read script and interpolate commands
			switch (command) {
				
			// Move + Scale + Rotate **(mo)ve + (sc)ale + (ro)tate = (moscro)**
			case "moscro":
				
				// Interpolate new command variables
				interpolateControl();
				break;

			}

		}

	}

	// Initialize New Variables From Script
	public void setControl(int posx, int posy, float scaleNum, int degree, long dur) {

		startx = x;
		starty = y;
		destx = posx;
		desty = posy;
		scaleA = scale;
		scaleB = scaleNum;
		angleA = angle;
		angleB = degree;
		duration = dur;
		duration = dur;
		starttime = System.currentTimeMillis();
		interpolation = true;

	}

	// Interpolate New Variables From Script
	public void interpolateControl() {

		if (interpolation == true) {

			float normTime = (float) (System.currentTimeMillis() - starttime) / (float) duration;
			x = (int) (startx + ((float) (destx - startx) * normTime));
			y = (int) (starty + ((float) (desty - starty) * normTime));
			scale = (float) (scaleA + ((float) (scaleB - scaleA) * normTime));
			angle = (int) (angleA + ((float) (angleB - angleA) * normTime));

			if ((System.currentTimeMillis() - starttime) >= duration) {

				interpolation = false;
				x = destx;
				y = desty;
				scale = scaleB;
				angle = angleB;

			}

			actor.translateTo(x, y);
			actor.scaleTo(scale);
			actor.rotateTo(angle);

		}

	}
	
}