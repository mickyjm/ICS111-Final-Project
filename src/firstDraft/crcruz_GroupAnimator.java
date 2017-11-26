package firstDraft;
import java.io.*;
import java.util.*;

public class crcruz_GroupAnimator {


	int x, y;
	int angle;
	double size;
	int posx, posy;

	Scanner fileScanner;
	String line;

	String command;
	// move
	int startx, starty;
	int destx, desty;

	// rotate
	int initialAngle;
	int finalAngle;

	// scale
	double initialSize;
	double finalSize;

	// duration
	long starttime;
	double duration;

	boolean interpolation;
	boolean exit;
	boolean clicked;
	String commandLine[];
	//stuff from Andrew's code
	int currentFrame;
	EZImage frame[];
	boolean hold;
	
	//for EZGroup
	EZGroup actorGrp;
	

	// CONSTRUCTOR
	public crcruz_GroupAnimator(String image, String scriptfile, int x, int y) {
		
		angle = 0;
		size = 0;
		
		if (image.equals("worldGrp")){
			actorGrp = EZ.addGroup();
			actorGrp.addElement(mainOperator.returnEZGrp());
			actorGrp.translateTo(x, y);
			//System.out.println("Actor Group Position " + actorGrp.getXCenter() + "." + actorGrp.getYCenter());
			
			//actorGrp = mainOperator.returnEZGrp();
			//System.out.println("assigned group");
		}
		try {
			fileScanner = new Scanner(new FileReader(scriptfile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		interpolation = false;
		exit = false;
		commandLine = null;
	}// end constructor
	
	
	//added for Project 3 for sun and moon objects
	public void destinationOrbit(int degree, double scale, double dur){
		// rotate
		initialAngle = angle;
		finalAngle = degree;

		// scale
		initialSize = size;
		finalSize = scale;

		// duration
		duration = 1000 * dur;
		starttime = System.currentTimeMillis();

		interpolation = true;
	}//end of destinationOrbit

	//added for Project 3: part 3
	public void destinationControl(int posx, int posy, int degree, double scale,
			double dur) {
		// move
		startx = x;
		starty = y;

		destx = posx;
		desty = posy;

		// rotate
		initialAngle = angle;
		finalAngle = degree;

		// scale
		initialSize = size;
		finalSize = scale;

		// duration
		duration = 1000 * dur;
		starttime = System.currentTimeMillis();

		interpolation = true;
	}// end destinationControl
	
	
	//credits to Jason for interpolation from RC Bug
	public void destinationMove(int posx, int posy, double dur) {
		startx = x;
		starty = y;

		destx = posx;
		desty = posy;

		duration = 1000 * dur;
		starttime = System.currentTimeMillis();

		interpolation = true;

	}// end destinationMove

	public void destinationRotate(int degree, double dur) {
		initialAngle = angle;

		finalAngle = degree;

		duration = 1000 * dur;
		starttime = System.currentTimeMillis();

		interpolation = true;

	}// end destinationRotate

	public void destinationScale(double scale, double dur) {
		initialSize = size;
		finalSize = scale;

		duration = 1000 * dur;
		starttime = System.currentTimeMillis();

		interpolation = true;
	}


	public boolean moving() {
		return interpolation;
	}
	
	public void orbit(){
		if (interpolation == true) {

			float normTime = (float) (System.currentTimeMillis() - starttime)
					/ (float) duration;

		
			angle = (int) (initialAngle + ((float) (finalAngle - initialAngle) * normTime));
			size =  (initialSize + ( (finalSize - initialSize) * normTime));

			if ((System.currentTimeMillis() - starttime) >= duration) {
				interpolation = false;
				
				angle = finalAngle;
				size = finalSize;
			} // end if

			
			actorGrp.rotateTo(angle);
			actorGrp.scaleTo(size);

		}// end if
	}

	public void control() {

		if (interpolation == true) {

			float normTime = (float) (System.currentTimeMillis() - starttime)
					/ (float) duration;

			x = (int)(startx + ((float)(destx - startx) * normTime));
			y = (int) (starty + ((float) (desty - starty) * normTime));
			angle = (int) (initialAngle + ((float) (finalAngle - initialAngle) * normTime));
			size =  (initialSize + ( (finalSize - initialSize) * normTime));

			if ((System.currentTimeMillis() - starttime) >= duration) {
				interpolation = false;
				x = destx;
				y = desty;
				angle = finalAngle;
				size = finalSize;
			} // end if

			actorGrp.translateTo(x, y);
			actorGrp.rotateTo(angle);
			actorGrp.scaleTo(size);

		}// end if

	}// end control
	
	public void move() {
		if (interpolation == true) {

			float normTime = (float) (System.currentTimeMillis() - starttime)
					/ (float) duration;

			x = (int) (startx + ((float) (destx - startx) * normTime));
			y = (int) (starty + ((float) (desty - starty) * normTime));

			if ((System.currentTimeMillis() - starttime) >= duration) {
				interpolation = false;
				x = destx;
				y = desty;
			} // end if
			actorGrp.translateTo(x, y);
		}// end if
	}// end move

	public void rotate() {
		if (interpolation == true) {

			float normTime = (float) (System.currentTimeMillis() - starttime)
					/ (float) duration;

			angle = (int) (initialAngle + ((float) (finalAngle - initialAngle) * normTime));

			if ((System.currentTimeMillis() - starttime) >= duration) {
				interpolation = false;
				angle = finalAngle;
			}// end if

			actorGrp.rotateTo(angle);
		}// end if

	}// end rotate

	public void scale() {
		if (interpolation == true) {

			float normTime = (float) (System.currentTimeMillis() - starttime)
					/ (float) duration;

			size =  (initialSize + ( (finalSize - initialSize) * normTime));

			if ((System.currentTimeMillis() - starttime) >= duration) {
				interpolation = false;
				size = finalSize;

			}// end if

			actorGrp.scaleTo(size);
		}// end if

	}// end scale

	public String readScript() throws IOException {

		line = fileScanner.nextLine();
		//System.out.println(line);
		return line;

	}// end readScript

	public void go() {
		//System.out.println("Actor Group Position " + actorGrp.getXCenter() + "." + actorGrp.getYCenter());
		
		if (exit) {
			return;
		}

		if (interpolation == false) {
			try {
				commandLine = readScript().split("\\s+");
				interpolation = true;

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			switch (commandLine[0]) {
			
			case "orbit":
				int orbitRotate = Integer.parseInt(commandLine[1]);
				float orbitScale = Float.parseFloat(commandLine[2]);
				duration = Double.parseDouble(commandLine[3]);

				destinationOrbit(orbitRotate,orbitScale, duration);
				break;


			case "control":
				int moveX = Integer.parseInt(commandLine[1]);
				int moveY = Integer.parseInt(commandLine[2]);
				int rotateAngle = Integer.parseInt(commandLine[3]);
				float scaleSize = Float.parseFloat(commandLine[4]);
				duration = Double.parseDouble(commandLine[5]);

				destinationControl(moveX, moveY, rotateAngle, scaleSize,
						duration);
				break;

				
			case "move":
				int x = Integer.parseInt(commandLine[1]);
				int y = Integer.parseInt(commandLine[2]);
				duration = Double.parseDouble(commandLine[3]);
				//System.out.println("Reading Move command " + x + ".." + y);

				destinationMove(x, y, duration);

				break;

			case "rotate":
				int rotate = Integer.parseInt(commandLine[1]);
				duration = Double.parseDouble(commandLine[2]);

				destinationRotate(rotate, duration);
				break;

			case "scale":
				float scale = Float.parseFloat(commandLine[1]);
				duration = Double.parseDouble(commandLine[2]);

				destinationScale(scale, duration);
				break;
				
			case "end":
				exit = true;
				break;
			
			}// end switch
		}// end if

		else {
			switch (commandLine[0]) {
			
			case "orbit":
				orbit();
				break;

			case "control":
				control();
				break;
			
			case "move":
				move();
				break;

			case "rotate":
				rotate();
				break;

			case "scale":
				scale();
				break;
			

			}// end switch

		}// end else

	}// end go
	
}// end class



