package firstDraft;
import java.util.*;
import java.awt.Color;
import java.io.*;

public class AnimatorTEST {
	mjm4_ActorAnimated letterc1, letterc2, Rystal, Ruz, lettera1, Lfred, letterc3, Han, letterr1, Eno, letterr2, Edaja;
	mjm4_ActorAnimated letterm1, Ichael, letterm2, Angrobang, lettera2, Ndrew, letterg, Uagliardo, songname;
	
	// Letters
	String[] songtitle = {"SongName.png"};
	String[] LetterC = {"LetterC.png"};
	String[] LetterA = {"LetterA.png"};
	String[] LetterR = {"LetterR.png"};
	String[] LetterM = {"LetterM.png"};
	String[] LetterG = {"LetterG.png"};
	// Member Names
	String[] rystal = {"rystal.png"};
	String[] ruz = {"ruz.png"};
	String[] lfred = {"lfred.png"};
	String[] han = {"han.png"};
	String[] eno = {"eno.png"};
	String[] edaja = {"edaja.png"};
	String[] ichael = {"ichael.png"};
	String[] angrobang = {"angrobang.png"};
	String[] ndrew = {"ndrew.png"};
	String[] uagliardo = {"uagliardo.png"};
	
	public AnimatorTEST()throws java.io.IOException {
		
		songname = new mjm4_ActorAnimated("songnamescript.txt", songtitle, 700, 1216, 36);
		// Crystal
		letterc1 = new mjm4_ActorAnimated("LetterCscript1.txt", LetterC, 700, 70, 70);
		Rystal = new mjm4_ActorAnimated("Rystalscript.txt", rystal, 700, 70, 80);
		// Cruz
		letterc2 = new mjm4_ActorAnimated("LetterCscript2.txt", LetterC, 700, 70, 70);
		Ruz = new mjm4_ActorAnimated("Ruzscript.txt", ruz, 700, 400, 80);
		
		// Alfred
		 lettera1 = new mjm4_ActorAnimated("LetterAscript1.txt", LetterA, 700, 140, 70);
		 Lfred = new mjm4_ActorAnimated("Lfredscript.txt", lfred, 700, 70, 160);
		// Chan
		 letterc3 = new mjm4_ActorAnimated("LetterCscript3.txt", LetterC, 700, 70, 150);
		 Han = new mjm4_ActorAnimated("Hanscript.txt", han, 700, 400, 160);
		
		// Reno
		 letterr1 = new mjm4_ActorAnimated("LetterRscript1.txt", LetterR, 700, 220, 70);
		 Eno = new mjm4_ActorAnimated("Enoscript.txt", eno, 700, 70, 240);
		// Redaja
		 letterr2 = new mjm4_ActorAnimated("LetterRscript2.txt", LetterR, 700, 70, 230);
		 Edaja = new mjm4_ActorAnimated("Edajascript.txt", edaja, 700, 400, 240);
		
		// Michael
		 letterm1 = new mjm4_ActorAnimated("LetterMscript1.txt", LetterM, 700, 300, 70);
		 Ichael = new mjm4_ActorAnimated("Ichaelscript.txt", ichael, 700, 70, 320);
		// Mangrobang
		 letterm2 = new mjm4_ActorAnimated("LetterMscript2.txt", LetterM, 700, 70, 310);
		 Angrobang = new mjm4_ActorAnimated("Angrobangscript.txt", angrobang, 700, 400, 320);
		
		// Andrew
		 lettera2 = new mjm4_ActorAnimated("LetterAscript2.txt", LetterA, 700, 400, 70);
		 Ndrew = new mjm4_ActorAnimated("Ndrewscript.txt", ndrew, 700, 70, 400);
		// Guagliardo
		 letterg = new mjm4_ActorAnimated("LetterGscript.txt", LetterG, 700, 70, 390);
		 Uagliardo = new mjm4_ActorAnimated("Uagliardoscript.txt", uagliardo, 700, 400, 400);
	
		
		/*while(true){
			//Intro Section Start
			// Song Title
			songname.go();
			// Crystal Cruz
			letterc1.go();
			Rystal.go();
			letterc2.go();
			Ruz.go();
			// Alfred Chan
			lettera1.go();
			Lfred.go();
			letterc3.go();
			Han.go();
			// Reno Redaja
			letterr1.go();
			Eno.go();
			letterr2.go();
			Edaja.go();
			// Michael Mangrobang
			letterm1.go();
			Ichael.go();
			letterm2.go();
			Angrobang.go();
			// Andrew Guagliardo
			lettera2.go();
			Ndrew.go();
			letterg.go();
			Uagliardo.go();
			Intro Section End
			
			// Refresh Screen
			EZ.refreshScreen();
		}*/
	}
}
