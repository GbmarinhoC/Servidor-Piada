package use;

import java.net.*;
import java.io.*;

public class KnockKnockProtocol {
    private static final int WAITING = 0;
    private static final int SENTKNOCKKNOCK = 1;
    private static final int SENTCLUE = 2;
    private static final int ANOTHER = 3;

    private static final int NUMJOKES = 5;

    private int state = WAITING;
    private int currentJoke = 0;

    private String[] clues = { "Kgb" };
    private String[] answers = { "Nos que fazemos as perguntas!" };

    public String processInput(String theInput) {
        String theOutput = null;
        
        if (state == WAITING) {
            theOutput = "Knock! Knock!";
            state = SENTKNOCKKNOCK;
        } else if (state == SENTKNOCKKNOCK) {
            if (theInput.equalsIgnoreCase("Quem e?")) {
                theOutput = clues[currentJoke];
                state = SENTCLUE;
            } else {
                theOutput = "Escreva por favor \"Quem e?\"! " +
			    "Tente novamente. Knock! Knock!";
            }
        } else if (state == SENTCLUE) {
            if (theInput.equalsIgnoreCase(clues[currentJoke] + " quem?")) {
                theOutput = answers[currentJoke] + "Tchau('n' para sair)";
                state = ANOTHER;
            } else {
                theOutput = "Escreva por favor \"" + 
			    clues[currentJoke] + 
			    " quem?\"" + 
			    "! Tente novamente. Knock! Knock!";
                state = SENTKNOCKKNOCK;
            }
        } else if (state == ANOTHER) {
                theOutput = "Tchau.";
                state = WAITING;
            
        }
        return theOutput;
    }
}