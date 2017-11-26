package firstDraft;

import java.awt.Color;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.Random;
import java.util.StringTokenizer;
//@Andrew Guagliardo
//Program for Assignment 3 part 2 ICS 111 Fall 2014 taught by Prof Jason Leigh
//Use of EZ library courtesy of Dylan Kobayashi
public class andrewG_basicAnimator {
		//basic set up for manipulating the image
		EZImage thisActor, frame[];
		BufferedReader reader; BufferedWriter writer;
		//for interpolation
		long startTime; float normTime, currentTime; double duration;
		//to determine if need to take specific action
		boolean interpolate, move, rotate, scale, fractal, circle, show, holdFrame;
		//for movement
		int startX, startY, targetX, targetY;
		//for rotation
		int targetA, startA;
		//for scale
		double currentScale, targetS;
		//for randomness
		Random random;
		//circle stuff
		int circleX, circleY, circleDraw, circleReverse, angle, radius, colorPick;
		Color color;
		//fractal stuff
		int thisFractal, length;
		//EZGroup stuff
		//EZGroup framesGrp, actorGrp;
		String actorName;
		
		//Constructor
		//create the images for manipulation, set default values
		public andrewG_basicAnimator(String thisImage, int posX, int posY, String thisScript, String thisRecord, String withFrames, int thisMany)throws java.io.IOException{
			startX = posX; startY = posY; targetX = posX; targetY = posY;
			currentScale = 1; targetS = 1;
			random = new Random();
			color = new Color(0,0,0);
			//framesGrp = EZ.addGroup(); actorGrp = EZ.addGroup();
			if(withFrames.equals("withFrames")){
				frame = new EZImage[thisMany];
				for (int i=0; i<thisMany; i++){
					StringBuffer f = new StringBuffer();
					String x = String.valueOf(i+1);
					f.append(thisImage); f.append("_"); f.append(x);
					f.append(".png");
					x = f.toString();
					frame[i] = EZ.addImage(x, posX, posY);
					frame[i].hide();
					//framesGrp.addElement(frame[i]);
				}
				thisActor = frame[0];
				thisActor.show();
				actorName = thisImage;
			}
			else{
				StringBuffer f = new StringBuffer();
				f.append(thisImage); f.append(".png");
			thisActor = EZ.addImage(f.toString(), posX, posY);
			}
			reader = new BufferedReader(new FileReader(thisScript));
			writer = new BufferedWriter(new FileWriter(thisRecord));
		}
		
		
		public void act()throws java.io.IOException{
			//this is going to be the function called to do all initial checks and determine which action
			//to take after each update
		
			if(interpolate == false && fractal == false && circle == false && holdFrame == false){
				read();
			}
			//checking if each item is interpolating or not, if so call that function
			if(interpolate){
				executeScript();
			}
			if(fractal){
				//further checks to see which fractal is running
				if(thisFractal==1){
					sierpinski();
				}
				if(thisFractal==2){
					tree();
				}
				if(thisFractal==3){
					brocolli();
				}
				//double checking in case it needs to turn off
				if(thisFractal==0){
					fractal=false;
				}
			}
			if(circle){
				circle();
			}
			if(holdFrame){
				holdFrame();
			}
		}
		public void read()throws java.io.IOException{
			////System.out.println("Reading");
			//create tokenizer
			reader.ready();
			String line;
			if ((line = reader.readLine()) != null){
				////System.out.println(line);
				StringTokenizer parse = new StringTokenizer(line);
				String command;
				while (parse.hasMoreTokens()){
					String token;
					token = parse.nextToken();
					//switch to check initial command input, if it's a normal command carry on with
					//proper variables
					//otherwise trigger special case commands, such as fractal, circle, animate, show, hide
					command = token;
					////System.out.println(line);
					switch (command){
					case "control":
						//make sure we store current info
						//Michael Mangrobang suggested using a - to
						//mean that we use the same value as previously, so we don't have to
						//retype everything when we want the actor to simply wait, I have
						//incorporated this idea into my own actor using my own code that
						//I built myself
						startX = thisActor.getXCenter();
						startY = thisActor.getYCenter();
						//next tokens are X and Y coords
						token = parse.nextToken();
						if(token.equals("-")){
							//do nothing
						}
						else{
						targetX = Integer.parseInt(token);
						}
						token = parse.nextToken();
						if(token.equals("-")){
							//do nothing
						}
						else{
						targetY = Integer.parseInt(token);
						}
						//next is rotation
						token = parse.nextToken();
						if(token.equals("-")){
							//do nothing
						}
						else{
						targetA = Integer.parseInt(token);
						}
						//next is scale
						token = parse.nextToken();
						if(token.equals("-")){
							//do nothing
						}
						else{
						targetS = Double.parseDouble(token);
						}
						//finally time
						token = parse.nextToken();
						duration = Double.parseDouble(token)*1000;
						//convert to milliseconds
						
						interpolate = true;
						//write("Executing this actor's script!");
						//System.out.println("Command recieved " + actorName + " - " + duration/1000 + " seconds.");
						break;
					case "circle":
						//center of circle
						token = parse.nextToken();
						if(token.equals("-")){
							circleX = 0;
						}
						else{
						circleX = Integer.parseInt(token);
						}
						token = parse.nextToken();
						if(token.equals("-")){
							circleY = 0;
						}
						else{
						circleY = Integer.parseInt(token);
						}
						
						token = parse.nextToken();
						circleDraw = Integer.parseInt(token);
						token = parse.nextToken();
						circleReverse = Integer.parseInt(token);
						token = parse.nextToken();
						angle = Integer.parseInt(token);
						token = parse.nextToken();
						radius = Integer.parseInt(token);
						token = parse.nextToken();
						duration = Double.parseDouble(token)*1000;
						startX = thisActor.getXCenter();
						startY = thisActor.getYCenter();
						colorPick = random.nextInt(5);
						circle = true;
						write("Creating a circle with this actor!");
						break;
					case "fractal":
						//which fractal and length of base
						token = parse.nextToken();
						thisFractal = Integer.parseInt(token);
						token = parse.nextToken();
						length = Integer.parseInt(token);
						fractal = true;
						write("Creating a fractal with this actor!");
						break;
					case "hide":
						thisActor.hide();
						EZ.refreshScreen();
						System.out.println("Hiding " + actorName);
						break;
					case "show":
						thisActor.show();
						EZ.refreshScreen();
						System.out.println("Showing " + actorName);
						break;
					case "front":
						thisActor.pullToFront();
						EZ.refreshScreen();
						break;
					case "back":
						thisActor.pushToBack();
						EZ.refreshScreen();
						break;
					case "frame":
						int thisFrame;
						token = parse.nextToken();
						thisFrame = Integer.parseInt(token);
						token = parse.nextToken();
						duration = Double.parseDouble(token)*1000;
						thisActor.hide();
						thisActor = frame[thisFrame-1];
						thisActor.scaleTo(currentScale); thisActor.rotateTo(targetA); thisActor.translateTo(targetX, targetY);
						thisActor.show();
						startTime = System.currentTimeMillis();
						holdFrame = true;
					}
				}
			}
			startTime = System.currentTimeMillis();
		}
		public void write(String line)throws java.io.IOException{
			////System.out.println("Writing");
			writer.write("Executed the following script "+line);
			writer.newLine();
			//System.out.println(line);
		}
		
		public void executeScript(){
			int newX, newY, newAngle;
			double newScale;
			if((System.currentTimeMillis() - startTime) > duration){
				thisActor.translateTo(targetX, targetY); thisActor.rotateTo(targetA); thisActor.scaleTo(targetS);
				startA = targetA; currentScale = targetS; interpolate = false;
			}
			//for the control command execute all commands given
			if((System.currentTimeMillis() - startTime) < duration){
				normTime = (float) (System.currentTimeMillis() - startTime)/ (float) duration;
				newX = (int) (startX + ((float) (targetX - startX) *  normTime));
				newY = (int) (startY + ((float) (targetY - startY) *  normTime));
				thisActor.translateTo(newX,newY);
				newAngle = (int) (startA + ((targetA-startA)* normTime));
				thisActor.rotateTo(newAngle);
				newScale = (double) currentScale+((targetS-currentScale) * normTime);
				////System.out.println("SCALE TO " + newScale);
				thisActor.scaleTo(newScale);
			}
		
			
		}
		//to do, will work on this once everything else with the project is squared away, still
		//determined to get this to function
		public void sierpinski(){
			//fractals!
			
			//set to false when it's completed and call the next read command
		}
		
		public void tree(){
			//
		}
		
		public void brocolli(){
			//
		}
		
		public void fern(){
			
		}
		
		public void circle(){
			//radius
					//duration
					//interpolation of circle's start X and Y center in relationship to
					int newX, newY, oldX, oldY;
					//random color
					switch (colorPick){
						case 0: color = new Color(49, 170, 54);
						break;
						case 1: color = new Color(0, 92, 161);
						break;
						case 2: color = new Color(149, 68, 137);
						break;
						case 3: color = new Color(217, 37, 36);
						break;
						case 4: color = new Color(229, 110, 30);
						break;
						case 5: color = new Color(255, 245, 0);
						break;
					}
					if((System.currentTimeMillis() - startTime) > duration){
						circle=false;
						newX = (int)(radius*Math.cos(Math.toRadians(angle))+startX);
						newY = (int)(radius*Math.sin(Math.toRadians(angle))+startY);
						//startX = thisActor.getXCenter();
						//startY = thisActor.getYCenter();
					}
					if((System.currentTimeMillis() - startTime) < duration && circleReverse == 0){
						oldX = thisActor.getXCenter();
						oldY = thisActor.getYCenter();
						//check to see if we should be using a specific center point, or a
						//center point pulled from the current position of the actor
						if(circleX != 0 && circleY != 0){
							normTime = (float) (System.currentTimeMillis() - startTime)/ (float) duration;
							newX = (int)((radius*Math.cos(Math.toRadians(angle*normTime)))+circleX);
							newY = (int)((radius*Math.sin(Math.toRadians(angle*normTime)))+circleY);
							thisActor.translateTo(newX,newY);
							if(circleDraw==1){
								EZ.addLine(oldX,oldY,newX,newY,color,3);
							}
						}
						else{
							normTime = (float) (System.currentTimeMillis() - startTime)/ (float) duration;
							newX = (int)((radius*Math.cos(Math.toRadians(angle*normTime)))+startX);
							newY = (int)((radius*Math.sin(Math.toRadians(angle*normTime)))+startY);
							thisActor.translateTo(newX,newY);
							if(circleDraw==1){
								EZ.addLine(oldX,oldY,newX,newY,color,3);
							}
						}
					}
					if((System.currentTimeMillis() - startTime) < duration && circleReverse == 1){
						oldX = thisActor.getXCenter();
						oldY = thisActor.getYCenter();
						if(circleX != 0 && circleY != 0){
							normTime = (float) (System.currentTimeMillis() - startTime)/ (float) duration;
							newX = (int)((radius*Math.cos(Math.toRadians(angle*normTime))+circleX)*-1);
							newY = (int)((radius*Math.sin(Math.toRadians(angle*normTime))+circleY)*-1);
							thisActor.translateTo(newX,newY);
							if(circleDraw==1){
								EZ.addLine(oldX,oldY,newX,newY,color,3);
							}
							
						}
						else{
							normTime = (float) (System.currentTimeMillis() - startTime)/ (float) duration;
							newX = (int)((radius*Math.cos(Math.toRadians(angle*normTime))+startX)*-1);
							newY = (int)((radius*Math.sin(Math.toRadians(angle*normTime))+startY)*-1);
							thisActor.translateTo(newX,newY);
							if(circleDraw==1){
								EZ.addLine(oldX,oldY,newX,newY,color,3);
							}
						}
					}
		}
		
		public void scaleTo(double thisSize){
			thisActor.scaleTo(thisSize);
			currentScale = thisSize; targetS = thisSize;
		}
		
		public void translateTo(int x, int y){
			thisActor.translateTo(x, y);
			startX = x; startY = y; targetX = x; targetY = y;
		}
		
		public void holdFrame(){
			if(System.currentTimeMillis()-startTime > duration){
				holdFrame = false;
			}
		}
}
