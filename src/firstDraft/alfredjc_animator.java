package firstDraft;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;



public class alfredjc_animator { //Beginning of class
	
	EZImage myActor;
	EZImage mySecondActor;
	EZImage myThirdActor;
	
	int x, y, startx, starty,startxx,startyy;
	float dur1;
	int destx, desty;
	int ax,ay;
	int numFrames;
	float rotateA;
	float scalea;
	float scaleb;
	float scaleStart;
	float scaleto1;
	double rotateB;
	double rotateC;
	long starttime;
	boolean interpolation;//Move
	boolean interpolation1 = false; //Rotate
	boolean interpolation2 = false; //Scale
	boolean interpolation3 = false; //Dash
	boolean interpolationShow = false;
	boolean interpolationHide = false;
	boolean starting = true;
	boolean stopped = false;
	boolean visibility = true;
	boolean loopIt = true;
	boolean holdFrame = false;
	String Script1 = "Script1.txt", Script2 = "Script2.txt", Script3 = "Script3.txt";
	String word;
	String MOVEx,MOVEy,dur;
	String scaleto;
	String direction;
	Scanner scan;
	float angleDur;
	int dur2;
	double scale1;
	String show1;
	double showDur;
	double showDur2;
	String hide1;
	double hideDur;
	double hideDur2;
	EZGroup group;
	EZImage frames[];
	
	//this is currently breaks the class so I've blocked it for now, I also blocked all directions in the rest of the code....(Andrew)
	//Directions left,right,foward,back;

	
	//String[]filenames ={"Bass right.png","Bass 1.png"};

	int a; //For identifying integers when comparing it to a string
	
	//Scanner scan = new Scanner (new File (Script1));
	//Scanner scan = new Scanner(System.in);  //TEMPORARY FIX
	
	//Public
	
	//note - frames and all things related to them was implemented with help from Andrew
	public alfredjc_animator(String filename, String filename1, int framesOne) throws FileNotFoundException{
		frames = new EZImage[8];
		if(framesOne == 1){
			for (int i=0; i<8; i++){
				StringBuffer f = new StringBuffer();
				String x = String.valueOf(i+1);
				f.append(filename1); f.setLength(filename1.length()-5); f.append(x);
				f.append(".png");
				x = f.toString();
				//System.out.println(x);
				frames[i] = EZ.addImage(x, EZ.getWindowWidth()/2, EZ.getWindowHeight()/2);
				frames[i].hide();
				//framesGrp.addElement(frame[i]);
			}
			myActor = frames[0];
			myActor.show();
			//System.out.println("DING");
		}
		else if (framesOne == 0){
		myActor = EZ.addImage(filename1, EZ.getWindowWidth()/2, EZ.getWindowHeight()/2);
		myActor.translateTo(x,y);
		startxx = x;
		startyy = y;
		//System.out.println("DING2");
		}
		//left = new Directions();
		scan = new Scanner (new File(filename));
		//Scanner scan2 = new Scanner(new InputStreamReader(System.in));
		interpolation = false;
		//mySecondActor = EZ.addImage("eve elsowrd.png", startx, starty);
		//myThirdActor = EZ.addImage("eve elsword.png", startx, starty);
		//startx = x;
		//starty = y;
		
	
	//alfredjc_animator(String[] fn, long dur, int posX, int posY) {
		//dur1 = dur;
		
		// Make an EZgroup to gather up all the individual EZimages.
		/*group = EZ.addGroup();
		numFrames = filenames.length;
		//System.out.println("filenames: "+filenames.length);
		
		// Make an array to hold the EZImages
		frames = new EZImage[numFrames];
		
		// Load each image
		for(int i = 0; i < numFrames; i++){
			frames[i] = EZ.addImage(filenames[i], 0, 0);
			frames[i].hide();
			group.addElement(frames[i]);
		}*/
		}
	
	public long getDur(){
		dur1 = dur1;
		return a;
	}

	//The setAngel method gets the angle needed for the rotation method.
	public void setAngle(){
		angleDur = (dur1*1000);
		starttime = System.currentTimeMillis();
		interpolation = true;
	}
	
	
	//scale1 = myActor.getScale();
	//scaleb = scalea/dur1;
public void sync(){//sync
	 //left.Lshow();
	 //left.L();
	 distanceCalculator();
	 scaleCalculator();
	 angleCalculator();
	 //animationCalculator();
	 
}
/*boolean animationCalculator(){
		if (stopped) return false;
		
		// If the animation is starting for the first time save the starttime
		if (starting){
			starttime = System.currentTimeMillis();
			starting = false;
		}

		// If the duration for the animation is exceeded and if looping is enabled
		// then restart the animation from the beginning.
		if ((System.currentTimeMillis() - starttime) > dur1) {
			if (loopIt) {
				restart();
				return true;
			}
			
			// Otherwise there is no more animation to play so stop.
			return false;
		}
		
		// Based on the current frame and elapsed time figure out what frame to show.
		float normTime = (float) (System.currentTimeMillis() - starttime)/ (float) dur1;

		int currentFrame = (int) (((float) numFrames) *  normTime);
		if (currentFrame > numFrames-1) currentFrame = numFrames-1;
		
		// Hide all the frames first
		for (int i=0; i < numFrames; i++) {
			if (i != currentFrame) frames[i].hide();
		}
		
		// Then show all the frame you want to see.
		if (visibility){
			//System.out.println(currentFrame);
			frames[currentFrame].show();
		}
		else 
			frames[currentFrame].hide();
		return true;
		
		

	}*/
void setLoop(boolean loop){
	loopIt = loop;
}
public void restart(){
	starting = true;
}


public void dash(){
	// fix this later
}
public void scaleCalculator(){
	if (interpolation2 == true){
		float normTime = (float) (System.currentTimeMillis() - starttime)/ (float) dur1;
		//System.out.println("scale here");
		scaleStart= (float)(scale1 +  ((float)  (scalea - scale1 )*  normTime));
		//System.out.println(scaleStart);
		myActor.scaleTo(scaleStart);

		if ((System.currentTimeMillis() - starttime) >= dur1) {
			interpolation2 = false;
			//rotateB = rotateC;
			
			//System.out.println("false");
		}
		
	}
	
}//End of scaleCalculator method

	
public void angleCalculator(){
		
	//Exhibits accelerated rotation on the second time the rotation method(2nd Rotation) is used 
	//Diagnosis: probably recycling the old values(1st rotation) from the previous "rotation" method . 
	//UPDATE: There is method in the Angle switch case that corrects this by setting the angle back to zero 
	//		  so it doesn't end up using the previous (current value of getRotation();).
		if (interpolation1 == true) {
			float normTime = (float) (System.currentTimeMillis() - starttime)/ (float) dur1;
			////System.out.println(normTime);
			////System.out.println("Here");
			//System.out.println("here");
			rotateA = (int)(rotateC +(float)(rotateB - rotateC )*  normTime);
			//rotateC = myActor.getRotation();
		    //rotateB = (degree of rotation);
			//rotateA = (EMPTY);
			//System.out.println(rotateA);
			myActor.rotateTo(rotateA);
			
			if ((System.currentTimeMillis() - starttime) >= dur1) {
				interpolation1 = false;
				//rotateB = rotateC;
				
			
				
				//System.out.println("false");
				//System.out.println(rotateA);
			
				//System.out.println(rotateC);
				
			}
		
			
		}
	
	}
	
	/*public void PLAY(){
		song1.loop();
		//System.out.println("music");
	}
	public void STOP(){
		song1.stop();
		song2.play();
	}
	public void PLAYPOKEMON(){
		song2.loop();
	}
	public void STOPPOKEMON(){
		song2.stop();
	}*/
	//
	//
	public void SHOW(){
		myActor.show();
	}
	public void HIDE(){
		myActor.hide();
	}
	
	public void setDestination(){
		destx = startxx; desty = startyy;
		
		dur1 =  (dur2*1000);
		starttime = System.currentTimeMillis();
		startx=x;
		starty=y;
		interpolation = true;
	}
	
	public boolean moving() {
		return interpolation;
	}
	public void distanceCalculator(){
		
		//The if statement is not going through, the translateTo is not working, look into this.
		//you are missing a bracket for this if statement that's why it's not working -Andrew
		if (interpolation == true) {
			float normTime = (float) (System.currentTimeMillis() - starttime)/ (float) dur1;
			//System.out.println(normTime);
			//System.out.println("Here");
			
			x = (int) (startx + ((float) (destx - startx) *  normTime));
			y = (int) (starty + ((float) (desty - starty) *  normTime));
			
			//System.out.println("pos: " + x + "," + y);
			//probably need to close this statement, that's why the one below it
			//isn't going off, it's inside the previous if statement. - Andrew
			
			if ((System.currentTimeMillis() - starttime) >= dur1) {
				interpolation = false;
				x = destx; y = desty;
				startx = x;
				starty = y;
			}
			myActor.translateTo(x,y);

			//System.out.println("pos: " + myActor.getXCenter() + "," + myActor.getYCenter());
		}
	}
	
	
	public void go() {
		if(interpolation == true && interpolation1 == true&& interpolation2==true){
			sync();
		}
		if(interpolation1 == true){
			angleCalculator();
			//System.out.println("Angle Working");
		}
		else if(interpolation) {
			distanceCalculator();
			//System.out.println("Working");
			
		}
		else if(interpolation2 == true){
			scaleCalculator();
			//System.out.println("Scale Working");
		}
		else {
			String POSb;
			String POSc;
			String ROTATEa;
			String ROTATEb;
			String SCALEa; //SCALEb;
			//String MOVEa,MOVEb,MOVEc;
		

			if(scan.hasNext() == true){
			
			word = scan.next();
			//System.out.println("Command: ");
			
			
			
			////System.out.println(word);
			
			// NUGGET OF WISDOM: when you assign a variable with a scan.next(); value; it will due the scan.next on it's own.
			if(word.equals("POS")){
				POSb = scan.next();
				POSc = scan.next();
				//System.out.println(word+" "+POSb+" "+POSc);
				int posb = Integer.parseInt(POSb);
				int posc = Integer.parseInt(POSc);
				myActor.translateBy(posb,posc);
			}
			else if(word.equals("ROTATE")){
				ROTATEa = scan.next();
				ROTATEb = scan.next();
				rotateB = Double.parseDouble(ROTATEa);
				dur1 = Integer.parseInt(ROTATEb);
				//System.out.println(word+" "+ROTATEa+" "+ROTATEb);
				//myActor.rotateTo(-myActor.getRotation()+ myActor.getRotation());
				rotateC = myActor.getRotation();
				dur1 =  (dur1*1000);
				//System.out.println("duration = " + ROTATEb);
				//System.out.println("rotateB = " + rotateB );
				//System.out.println(" dur1 =" + dur1);
				//System.out.println("currentAngle: " + rotateC);
				starttime = System.currentTimeMillis();
				interpolation1 = true;
				
			}
			else if(word.equals("SCALE")){
				SCALEa = scan.next();
				dur = scan.next();
				scalea = Float.parseFloat(SCALEa);
				dur1 = Float.parseFloat(dur);
				//System.out.println(word+" "+SCALEa+" "+dur1);
				scale1 = myActor.getScale();
				scaleb = scalea/dur1;
				//myActor.scaleTo(scalea);
				dur1 *= 1000;
				starttime = System.currentTimeMillis();
				interpolation2 = true;
				
				
			}
	       else if (word.equals("SCALETO")){
	    	   	scaleto = scan.next();
	    	   	scaleto1 = Float.parseFloat(scaleto);
				myActor.scaleTo(scaleto1);
			}
			
			else if(word.equals("MOVE")){
				MOVEx = scan.next();
				MOVEy = scan.next();
				dur = scan.next();
				startxx = Integer.parseInt(MOVEx);
				startyy = Integer.parseInt(MOVEy);
				dur1 = Float.parseFloat(dur);
				startx = myActor.getXCenter();
				starty = myActor.getYCenter();
				destx = startxx;
				desty = startyy;
				starttime = System.currentTimeMillis();
				dur1 *= 1000;
				interpolation = true;
				
				//System.out.println(word+" "+MOVEx+" "+MOVEy+" "+dur1);
				}
			
			
			else if(word.equals("SHOW")){
				SHOW();
			}
			else if(word.equals("HIDE")){
				HIDE();
			}
			else if(word.equals("left")){
				//left.L();
			}
			else if(word.equals("FRAME")){
				int thisFrame;
				String sFrame;
								
				sFrame = scan.next();
				thisFrame = Integer.parseInt(sFrame);
				//System.out.println("hello");
				myActor.hide();
				myActor = frames[thisFrame-1];
				myActor.scaleTo(scalea); myActor.translateTo(startx, starty);
				myActor.show();
				
			}
		
			else if(word.equals("sync")){
				//System.out.println("sync");
				MOVEx = scan.next();
				if(MOVEx.equals("-")){
					startxx = startxx;
					//System.out.println("dash working");
					}
				else{
					startxx = Integer.parseInt(MOVEx);
					}
				MOVEy = scan.next();
				if(MOVEy.equals("-")){
					startyy = startyy;
					}
				else{
					startyy = Integer.parseInt(MOVEy);
					}
				startx = myActor.getXCenter();
				starty = myActor.getYCenter();
				destx = startxx;
				desty = startyy;
				ROTATEa = scan.next();
				rotateC = myActor.getRotation();
				if(ROTATEa.equals("-")){
					rotateB = rotateB;
					}
				else{
					rotateB = Double.parseDouble(ROTATEa);
					}
				SCALEa = scan.next();
				scale1 = myActor.getScale();
				if(SCALEa.equals("-")){
					scalea = scalea;
					}
				else{
					scalea = Float.parseFloat(SCALEa);
					}
				dur = scan.next();
					if(dur.equals("-")){
						dur1 = dur1;
					}
					else{
				dur1 = Float.parseFloat(dur);
				dur1 *= 1000;
					}
				starttime = System.currentTimeMillis();
				interpolation = true;
				interpolation1 = true;
				interpolation2 = true;
				//System.out.println(startxx+" "+startyy+" "+rotateB+" "+scalea+" "+dur1);
				//direction = scan.next();
					//if(direction.equals("ANIMATE")){
						
				//	}
		
					
			}
			
			
		/*	else if(word.equals("-")){
				//System.out.println("dash is working");
				interpolation3 = true;
			}*/
		 }
		} //end else get a command
	} //end go	
}// End of Class
