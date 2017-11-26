package firstDraft;
import java.awt.Color;
//Main Operator Class written by Team CARMA, use of EZ Library courtesy of Dylan Kobayashi
//Flip Book class courtesy of Prof. Jason Leigh
//for Project 3 ICS 111 taught by Professor Jason Leigh @ UH Manoa
//@Crystal Cruz, Alfred Chan, Michael Mangrobang, Reno Redaja, Andrew Guagliardo (CARMA)

//READ ME PLEASE!

//Hey everyone! It's a bit messy but I tried to explain everything with comments, we can start
//plugging in our animators and trying to get stuff to work. If you have any questions or need
//help email or text or write comments in here with your questions and we'll try to work it out together!
//feel free to change the txt files (scripts) on my actors for your own testing purposes I have backups of everything- Andrew


public class mainOperator {

	//an array for Andrew's animators
	andrewG_basicAnimator actor[];
	//Alfred's animators
	alfredjc_animator character1_1, character2_1, character3_1, character4_1, character5_1;
	//Crystal's animators
	crcruz_Animator theSun, theMoon, background;
	crcruz_GroupAnimator actorGrp;
	//Michael's animators
	String[] Seed;
	String[] TreeGrowing;
	String[] TreeSway;
	mjm4_ActorAnimated TreeSeed;
	mjm4_ActorAnimated TreeGrow;
	mjm4_ActorAnimated Tree;
	String[] charizardpng, articunopng;
	mjm4_ActorAnimated Charizard, Articuno;
	//RENO'S ANIMATION
	private String[] starsAnime;
	private String[] starsAnime2;
	private String[] starsAnime3;
	
	private rredaja_Animator stars1; //stars
	private rredaja_Animator stars2; //stars
	private rredaja_Animator stars3; //stars
	private rredaja_Animator stars4; //stars
	private rredaja_Animator stars5; //stars
	private rredaja_Animator stars6; //stars
	private rredaja_Animator stars7; //stars
	private rredaja_Animator stars8; //stars
	private rredaja_Animator stars9; //stars
	private rredaja_Animator stars10; //stars
	private rredaja_Animator stars11; //stars
	private rredaja_Animator stars12; //stars
	private rredaja_Animator stars13; //stars

	private String[] comets;
	private rredaja_Animator cometActor; //comet
	
	//intro stuff courtesy of Michael
	AnimatorTEST introSequence;

	//misc stuff for the main class
	long startTimeAfterLoading;
	EZRectangle rectangles[];
	EZImage starClicks[];
	int mouseClicks;
	int clickX, clickY;
	EZSound sadMachine;
	static EZGroup worldGrp;
	EZGroup sunGrp;
	EZGroup moonGrp;
	EZGroup starsGrp;
	double newAngle, targetA = 360, sunStartA = 100, moonStartA = 280;
	//placeholder Sun and Moon for now, will remove later
	EZImage sun, moon;

	

	//We have to keep these java.io.IOException things here because of the way Andrew wrote his code, don't remove them
	//if you write a new function and it doesn't seem to be working it's probably because you need to add this at the
	//end of your function because Andrew's code will break it otherwise.
	public static void main(String[] args)throws java.io.IOException {
		//Set up our window size and trigger the rest of the construction
		EZ.initialize(1280, 720);
		mainOperator program = new mainOperator();
		program.begin();
	}

	public static EZGroup returnEZGrp(){
		return worldGrp;
	}
	
	public void begin() throws java.io.IOException{
		//music
		sadMachine = new EZSound("sadMachine.wav");
		//Variable so we know how many times the mouse has been clicked
		mouseClicks= 0;
		//rectangle array to put up rectangles when the mouse is clicked
		//rectangles = new EZRectangle[100];
		starClicks = new EZImage[100];

		//READ ME! We need to create and position everything in a specific order so they are properly layered
		//and there are no issues with things looking wierd and overlapping when they need to go behind something
		//or vice versa. So- Proper Order for final initialization Is As FOLLOWS----
		//please put your pieces for initializing these items in the proper place when you are done with your testing!

		//1) Crystal's Background initialization code
		background = new crcruz_Animator("CityBG1.png", "background.txt", 640, 200);
		
		//2) Reno's Stars initialization code
		starsAnime = new String[] {"Star1.png", "Star2.png", "Star3.png"};
		starsAnime2 = new String[] {"Star3.png", "Star2.png", "Star1.png"};
		starsAnime3 = new String[] {"Star2.png", "Star3.png", "Star1.png"};
		
		stars1 = new rredaja_Animator("star1", starsAnime3, 1044, 55, 65);
		stars2 = new rredaja_Animator("star2", starsAnime2, 1000, 293, 156);
		stars3 = new rredaja_Animator("star3", starsAnime, 1015, 236, 263);
		stars4 = new rredaja_Animator("star4", starsAnime3, 1000, 328, 365);
		stars5 = new rredaja_Animator("star5", starsAnime2, 700, 276, 365);
		stars6 = new rredaja_Animator("star6", starsAnime, 351, 215, 365);
		stars7 = new rredaja_Animator("star7", starsAnime2, 438, 46, 365);
		stars8 = new rredaja_Animator("star8", starsAnime3, 838, 189, 365);
		stars9 = new rredaja_Animator("star9", starsAnime2, 226, 115, 365);
		stars10 = new rredaja_Animator("star10", starsAnime, 75, 54, 365);
		stars11 = new rredaja_Animator("star11", starsAnime2, 89, 325, 365);
		stars12 = new rredaja_Animator("star12", starsAnime3, 955, 131, 365);
		stars13 = new rredaja_Animator("star13", starsAnime, 1194, 193, 365);

		
		//3) Crystal's Sun and Moon initialization code, including initialization for the EZGroups
		theSun = new crcruz_Animator("Sun.png", "theSun.txt", 640, 200);
		theMoon = new crcruz_Animator("Moon.png", "theMoon.txt", 640, 200);
		sunGrp = EZ.addGroup();
		moonGrp = EZ.addGroup();
		sunGrp.addElement(theSun.actorImage);
		moonGrp.addElement(theMoon.actorImage);
		worldGrp = EZ.addGroup();
		worldGrp.addElement(sunGrp);
		worldGrp.addElement(moonGrp);
		//worldGrp.translateTo(640, 750);
		actorGrp = new crcruz_GroupAnimator("worldGrp", "actorGrp.txt", 640, 750);
		sunGrp.rotateTo(100);
		moonGrp.rotateTo(280);
		//moonGrp.translateTo(0, 200);
		//sunGrp.translateTo(0, 200);
		
		//4) Andrew's CityScape and Walls / effects initialization code
		actor = new andrewG_basicAnimator[8];
		actor[0] = new andrewG_basicAnimator("CityBG1", 640, 360, "andrewG_actor2.txt", "andrewG_record2.txt", "withFrames", 6);
		actor[1] = new andrewG_basicAnimator("Door1", 640, 360, "andrewG_actor4.txt", "andrewG_record3.txt", "withFrames", 6);
		actor[2] = new andrewG_basicAnimator("Character1", 1241, 380, "andrewG_actor3.txt", "andrewG_record3.txt", "withFrames", 8);
		actor[3] = new andrewG_basicAnimator("CitySkyLine1", 640, 360, "andrewG_actor1.txt", "andrewG_record1.txt", "withFrames", 6);
		//need to add - Glows for seed, dust cloud for comet impact (if there's time)
		actor[4] = new andrewG_basicAnimator("characterGlow1", 695, 547, "andrewG_actor5.txt", "andrewG_record5.txt", "0", 0);
		actor[5] = new andrewG_basicAnimator("treeGlow1", 192, 217, "andrewG_actor6.txt", "andrewG_record6.txt", "withFrames", 6);
		actor[6] = new andrewG_basicAnimator("seedGlow1", 192, 680, "andrewG_actor7.txt", "andrewG_record7.txt", "withFrames", 6);
		
		//5) Reno's Comet initialization code
		comets = new String[] {"comet_1.png", "comet_2.png", "comet_3.png", "comet_4.png"};
		cometActor = new rredaja_Animator("comet", comets, 1000, 1280, 0); //comet
		
		//6) Alfred's Characters initialization code
		character2_1 = new alfredjc_animator("character2_1.txt","Character2_1.png", 1);
		character3_1 = new alfredjc_animator("character3_1.txt","Character3_1.png", 1);
		character4_1 = new alfredjc_animator("character4_1.txt","Character4_1.png", 1);
		character5_1 = new alfredjc_animator("character5_1.txt","Character5_1.png", 1);
		
		//7) Michael's Tree Seed and Tree initialization code
		Seed = new String[] {"seed.png"};
		TreeGrowing = new String[] {"Tree_1.png","Tree_2.png","Tree_3.png","Tree_4.png","Tree_5.png","Tree_6.png",
						"Tree_7.png","Tree_8.png","Tree_9.png","Tree_10.png","Tree_11.png","Tree_12.png",
						"Tree_13.png","Tree_14.png","Tree_15.png","Tree_16.png","Tree_17.png","Tree_18.png",
						"Tree_19.png","Tree_20.png","Tree_21.png","Tree_22.png","Tree_23.png","Tree_24.png"};
		TreeSway = new String[] {"Tree_24.png","Tree_25.png","Tree_26.png","Tree_27.png","Tree_28.png","Tree_29.png",
						"Tree_30.png","Tree_31.png","Tree_32.png","Tree_33.png","Tree_34.png","Tree_35.png"};
		
		charizardpng = new String[] {"Charizard1.png", "Charizard2.png", "Charizard3.png", "Charizard4.png"};
		articunopng = new String[] {"Articuno1.png", "Articuno2.png", "Articuno3.png", "Articuno2.png"};

		Charizard = new mjm4_ActorAnimated("charizardscript.txt", charizardpng, 1000, 1300, 50);
		Articuno = new mjm4_ActorAnimated("articunoscript.txt", articunopng, 1000, 1300, 150);
		TreeSeed = new mjm4_ActorAnimated("seedscript.txt", Seed, 1200, 193, 672);
		TreeGrow = new mjm4_ActorAnimated("growscript.txt", TreeGrowing, 24000, 200, 700);
		Tree = new mjm4_ActorAnimated("treescript.txt", TreeSway, 12000, 200, 700);
		
		//8) Michael's Intro Sequence
		introSequence = new AnimatorTEST();
		
		//9) Tree slicer
		actor[7] = new andrewG_basicAnimator("slice", 0, 0, "andrewG_actor8.txt", "andrewG_record8.txt", "0", 0);
		
		
		//for proper time stamping, before there was an issue where the start time would begin before loading was finished, this throws timing off because even though
		//time is elapsing the scripts don't start being read until we trigger the update loop. this sort of solves that but still is slightly off.
		startTimeAfterLoading = System.currentTimeMillis();
		//start up our update loop
		loopIt();
		
	}

	public void loopIt() throws java.io.IOException{
		sunGrp.rotateTo(sunStartA);
		moonGrp.rotateTo(moonStartA);
		int i = 0; int starClicker = 1;
		String scene = "startSequence";
		long startTime = System.currentTimeMillis(), cycleTime = startTime;  float normTime;
		//startTime is self explanatory, it's the time stamp for the beginning of the updater loop.
		//cycleTime is the time stamp used for the sun and moon orbits, cycle duration is the time it should take
		//for them to complete a full orbit from their starting positions.
		float cycleDuration = 11000, startDuration = 9500, firstActDuration = 29500, cometActDuration = 64500;
		float secondActDuration = 84500;
		
		while(true){
			//checking to see what scene we are in and having things behave accordingly
			switch(scene){
			case "startSequence":
				if((System.currentTimeMillis() - startTime) > 3750){
					startMusic(i);
					i++;
					}
					//things to add
					//timer for intro
				if(System.currentTimeMillis()-startTime > startDuration){
					cycleTime = System.currentTimeMillis();
					scene = "firstAct";
					}
				//stuff for the intro
				/*Intro Section Start*/
				// Song Title
				introSequence.songname.go();
				// Crystal Cruz
				introSequence.letterc1.go();
				introSequence.Rystal.go();
				introSequence.letterc2.go();
				introSequence.Ruz.go();
				// Alfred Chan
				introSequence.lettera1.go();
				introSequence.Lfred.go();
				introSequence.letterc3.go();
				introSequence.Han.go();
				// Reno Redaja
				introSequence.letterr1.go();
				introSequence.Eno.go();
				introSequence.letterr2.go();
				introSequence.Edaja.go();
				// Michael Mangrobang
				introSequence.letterm1.go();
				introSequence.Ichael.go();
				introSequence.letterm2.go();
				introSequence.Angrobang.go();
				// Andrew Guagliardo
				introSequence.lettera2.go();
				introSequence.Ndrew.go();
				introSequence.letterg.go();
				introSequence.Uagliardo.go();
				break;
			case "firstAct":
				if(System.currentTimeMillis()-startTime > firstActDuration){
					scene = "cometAct";
					System.out.println("Comet Act: " + (System.currentTimeMillis()-startTime) + " milliseconds");
					}
				
				//for causing the sun and moon to orbit
				if(System.currentTimeMillis()-cycleTime > cycleDuration){
					cycleTime = System.currentTimeMillis();
				}
					//causes the sun and moon to rotate around a central point thanks to EZGroups
				else if (System.currentTimeMillis()-cycleTime < cycleDuration){
					normTime = (float) (System.currentTimeMillis() - cycleTime)/ (float) cycleDuration;
					newAngle = (int) (targetA* normTime);
					worldGrp.rotateTo(newAngle);
					}
				break;
			case "cometAct":
				if(System.currentTimeMillis()-startTime > cometActDuration){
					scene = "secondAct";
					System.out.println("Second Act: " + (System.currentTimeMillis()-startTime) + " milliseconds");
					cycleDuration = 22000;
					cycleTime = ((System.currentTimeMillis()-startTime) + cycleTime);
					}
				
				break;
			case "secondAct":
				if(System.currentTimeMillis()-startTime > secondActDuration){
					scene = "finalAct";
					System.out.println("Final Act: " + (System.currentTimeMillis()-startTime) + " milliseconds");
					}
				
				//for causing the sun and moon to orbit
				if(System.currentTimeMillis()-cycleTime > cycleDuration){
					cycleTime = System.currentTimeMillis();
				}
					//causes the sun and moon to rotate around a central point thanks to EZGroups
				else if (System.currentTimeMillis()-cycleTime < cycleDuration){
					normTime = (float) (System.currentTimeMillis() - cycleTime)/ (float) cycleDuration;
					newAngle = (int) (targetA* normTime);
					worldGrp.rotateTo(newAngle);
					}
				break;
				
			}
			
		//This is a mouse clicker to find x and y co-ordinates, will print the information to the console along
		//with the click number, write these down before you run the program again because the console will wipe
		//every time you restart the program.
		boolean clicked = false;
		//checking to see if the mouse was clicked... if true it will grab the X and Y co-ordinate
		if (EZInteraction.getXMouseClick() != -1) {
			clickX = EZInteraction.getXMouseClick();
			clicked = true;
		}
		if (EZInteraction.getYMouseClick() != -1) {
			clickY = EZInteraction.getYMouseClick();
			clicked = true;
		}
		//so if the mouse was clicked, make a rectangle where it was clicked and print the location to the console
		if (clicked==true){
			if(starClicker == 1){
			starClicks[mouseClicks] = EZ.addImage("Star1.png", clickX, clickY);
			starClicks[mouseClicks].scaleTo(.5);
			starClicker++;
			}
			else if(starClicker == 2){
				starClicks[mouseClicks] = EZ.addImage("Star2.png", clickX, clickY);
				starClicks[mouseClicks].scaleTo(.5);
				starClicker++;
			}
			else if(starClicker == 3){
				starClicks[mouseClicks] = EZ.addImage("Star3.png", clickX, clickY);
				starClicks[mouseClicks].scaleTo(.5);
				starClicker = 1;
			}
			//rectangles[mouseClicks] = EZ.addRectangle(clickX, clickY, 10,10, Color.BLACK, true);
			System.out.println("Number "+mouseClicks+" = "+clickX+".."+clickY);
			System.out.println("Time Stamp Recorded at: " + (System.currentTimeMillis()-startTimeAfterLoading) + " milliseconds");
			mouseClicks++;
		}
		//actors reading scripts
			
		//Andrew's
		actor[0].act();	actor[1].act();	actor[2].act(); actor[3].act(); actor[4].act();	actor[5].act();	actor[6].act(); actor[7].act();
		//Alfred's
		character2_1.go(); character3_1.go(); character4_1.go(); character5_1.go();
		
		//Crystal's
		theSun.go(); theMoon.go(); background.go(); actorGrp.go();
			
		//Michael's
		//SampleActor.go(); SampleActor1.go(); SampleActor2.go();
		TreeSeed.go(); TreeGrow.go(); Tree.go(); Charizard.go(); Articuno.go();
		
		//Reno's
		stars1.go(); stars2.go(); stars3.go(); stars4.go(); 
		stars5.go(); stars6.go(); stars7.go(); stars8.go();
		stars9.go(); stars10.go(); stars11.go(); stars12.go();
		stars13.go();
		
		cometActor.go(); 
		if (scene.equals("cometAct") && System.currentTimeMillis()-startTime < 49750){
			cometActor.translateTo(EZInteraction.getXMouseMove(), EZInteraction.getYMouseMove());
		}
			
		EZ.refreshScreen();
		
		}
	}

	public void startMusic(int zeroStart){
		if(zeroStart == 0){
		sadMachine.play();
		}
	}

}
