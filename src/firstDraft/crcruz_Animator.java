package firstDraft;
import java.io.*;
import java.util.*;

//DECLARATION OF VARIABLES
public class crcruz_Animator {

	EZImage actorImage;
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
	public crcruz_Animator(String image, String scriptfile, int x, int y) {
		frame = new EZImage[6];
		angle = 0;
		size = 0;
		
		//for world group
		if (image.equals("worldGrp")){
			actorGrp = EZ.addGroup();
			actorGrp.addElement(mainOperator.worldGrp);
		}
		//for showing frames
		else if(image.equals("CityBG1.png")){
			frame[0]= EZ.addImage("bgColor1.png", x, y);
			frame[1]= EZ.addImage("bgColor2.png", x, y);
			frame[2]= EZ.addImage("bgColor3.png", x, y);
			frame[3]= EZ.addImage("bgColor4.png", x, y);
			frame[4]= EZ.addImage("bgColor5.png", x, y);
			frame[5]= EZ.addImage("bgColor6.png", x, y);
			frame[1].hide(); frame[2].hide(); frame[3].hide(); frame[4].hide(); frame[5].hide();
			actorImage = frame[0];
		}
		else{
		actorImage = EZ.addImage(image, x, y);
		}
		
		actorImage.show();
		hold = false;
		

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

			
			actorImage.rotateTo(angle);
			actorImage.scaleTo(size);

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

			actorImage.translateTo(x, y);
			actorImage.rotateTo(angle);
			actorImage.scaleTo(size);

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
			actorImage.translateTo(x, y);
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

			actorImage.rotateTo(angle);
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

			actorImage.scaleTo(size);
		}// end if

	}// end scale
	
	//credits to Andrew for original code, now modified
	public void frame(){
		if(System.currentTimeMillis()-starttime > (duration*1000)){					
			interpolation = false;
			//System.out.println("Switching Frame");
			}
	}

	public String readScript() throws IOException {

		line = fileScanner.nextLine();
		//System.out.println(line);
		return line;

	}// end readScript

	public void go() {

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
				//credits to Andrew for original code, now modified
			case "frame":
				currentFrame = Integer.parseInt(commandLine[1]);
				//System.out.println (commandLine[1]);
				duration = Double.parseDouble(commandLine[2]);
				starttime = System.currentTimeMillis();
				interpolation = true;
				actorImage.hide();
				actorImage = frame[currentFrame-1];
				actorImage.show();
				//System.out.println("Show Frame");
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
			case "frame":
				frame();
				break;

			}// end switch

		}// end else

	}// end go
	
}// end class

