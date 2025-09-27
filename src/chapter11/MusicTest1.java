package chapter11;

import javax.sound.midi.*;

public class MusicTest1 {

    public void play() {  // need to handle exception
        // Sequencer sequencer = MidiSystem.getSequencer(); // might throw MidiUnavailableException

        System.out.println("We got a sequencer");
    } // close play

    public static void main(String[] args) {
        MusicTest1 mt = new MusicTest1();
        mt.play();
    } // close main
} // close class
