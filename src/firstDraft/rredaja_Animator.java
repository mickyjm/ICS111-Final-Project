package firstDraft;
import java.util.*;
import java.io.*;

public class rredaja_Animator {

	private EZImage image;
	private EZSound sound;
	private String music;
	private Scanner s;

	//variables for interpolation
	public boolean interpolation;
	private boolean exit;
	public long starttime;
	public double duration;

	//variables for MOVE
	public int x, y, startx, starty, destx, desty;

	//variables for SCALE
	private float scale = 1;
	private float scaleA, scaleB; //scaleA: initial & scaleB: final

	//variables for ROTATE
	private int angle;
	private int angleA, angleB; //angleA: initial & angleB: final

	String[] command;
	private String line;

	private FlipBook anime; //JASON'S FlipBook.java file

	private int newX;
	private int newY;
	private float scaleTo;
	private int rotateTo;

	public rredaja_Animator(String script, String[] picture, long dur, int picX, int picY) throws java.io.IOException {

		//INPUT SCRIPT READER
		try{
			s = new Scanner(new FileReader(script));
		} catch(FileNotFoundException e){
			e.printStackTrace();
		}

		//INPUT EZIMAGE PICTURE
		anime = new FlipBook(picture, dur, picX, picY);
		x = picX;
		y = picY;
		anime.translateTo(x, y);

		newX = x;
		newY = y;
		scaleTo = 1;
		rotateTo = 0;

		interpolation = false;
		exit = false;
		command = null;

		duration = dur;

	}//end rredaja_Animator constructor method

	public void go() {

		anime.go();

		if(exit){
			return;
		}

		if(interpolation == false){
			try{
				command = readCommand().split("\\s+");
				interpolation = true;
			}//end try
			catch(IOException e){
				e.printStackTrace();
			}//end catch

			switch(command[0]){
			case "PLAY":
				music = command[1];
				playMusic();
				break;
			case "CONTROL":

				if (command[1].contentEquals("-")) {
					newX = newX;
				} else newX = Integer.parseInt(command[1]);

				if (command[2].contentEquals("-")) {
					newY = newY;
				} else newY = Integer.parseInt(command[2]);

				if (command[3].contentEquals("-")) {
					rotateTo = rotateTo;
				} else rotateTo = Integer.parseInt(command[3]);

				if (command[4].contentEquals("-")) {
					scaleTo = scaleTo;
				} else scaleTo = Float.parseFloat(command[4]);

				if (command[5].contentEquals("-")) {
					duration = duration;
				} else duration = Double.parseDouble(command[5]);

				setControl(newX, newY, rotateTo, scaleTo, duration);
				break;
			case "QUIT":
				exit = true;
				break; //END QUIT

			}//end switch
		}//end if
		else {
			switch(command[0]){
			case "PLAY":
				break;
			case "CONTROL":
				controlInterpolation();
				break;

			}//end switch
		}//end else

	}//end go()

	public String readCommand() throws IOException {

		line = s.nextLine();
		//System.out.println(line); 
		return line;


	}//end readCommand()

	public void setControl(int posX, int posY, int degree, float scaleNum, double dur){
		startx=x; 
		starty=y;
		destx = posX; 
		desty = posY; 

		angleA = angle;
		angleB = degree;

		scaleA = scale;
		scaleB = scaleNum;

		duration = (dur*1000);
		starttime = System.currentTimeMillis();

		interpolation = true;	

	}//end setControl()

	public void controlInterpolation(){

		if (interpolation == true) {
			float normTime = (float) (System.currentTimeMillis() - starttime)/ (float) duration;

			x = (int) (startx + ((float) (destx - startx) *  normTime)); //moves image x position
			y = (int) (starty + ((float) (desty - starty) *  normTime)); //moves image y position

			scale = (float) (scaleA + ((float) (scaleB - scaleA) *  normTime)); //scales image

			angle = (int) (angleA + ((float) (angleB - angleA) *  normTime)); //rotates image

			if ((System.currentTimeMillis() - starttime) >= duration) {
				interpolation = false;

				x = destx; //sets new x position
				y = desty; //sets new y position

				scale = scaleB; //scales image

				angle = angleB; //rotates image
			}
			anime.translateTo(x,y);
			anime.scaleTo(scale);
			anime.rotateTo(angle);

		}//end interpolation

	}//end controlInterpolation

	public void playMusic(){
		sound = EZ.addSound(music);
		sound.play();
	}//end playMusic()
	
	void translateTo(int posX, int posY) {
		anime.translateTo(posX,posY);
	}//end translateTo()

}//end class rredaja_Animator