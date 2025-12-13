package com.headfirstjava.appendix.a;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.sound.midi.*;
import java.util.*;
import java.awt.event.*;
import java.net.*;
import javax.swing.event.*;

public class BeatBoxFinal {

    JFrame theFrame;
    JPanel mainPanel;
    JList incomingList;
    JTextField userMessage;
    ArrayList<JCheckBox> checkboxList;
    int nextNum;
    Vector<String> listVector = new Vector<String>();
    String userName;
    ObjectOutputStream out;
    ObjectInputStream in;
    HashMap<String, boolean[]> otherSeqsMap = new HashMap<String, boolean[]>();

    Sequencer sequencer;
    Sequence sequence;
    Track track;

    String[] instrumentNames = {"Bass Drum", "Closed Hi-Hat", "Open Hi-Hat","Acoustic Snare",
            "Crash Cymbal", "Hand Clap", "High Tom", "Hi Bongo", "Maracas", "Whistle",
            "Low Conga", "Cowbell", "Vibraslap", "Low-mid Tom", "High Agogo", "Open Hi Conga"};

    int[] instruments = {35,42,46,38,49,39,50,60,70,72,64,56,58,47,67,63};

    // Foundation patterns
    Map<String, boolean[]> foundationPatterns = new LinkedHashMap<>();

    public static void main (String[] args) { // args[0] is your user ID/screen name
        String userName = "Anonymous"; // default
        if (args.length > 0) {
            userName = args[0];
        } else {
            System.out.println("No username provided, using default: " + userName);
        }
        new BeatBoxFinal().startUp(userName);
    }

    public void startUp(String name) {
        userName = name;
        loadFoundationPatterns(); // load ready-made patterns
        // open connection to the server
        try {
            Socket sock = new Socket("127.0.0.1", 4242);
            out = new ObjectOutputStream(sock.getOutputStream());
            in = new ObjectInputStream(sock.getInputStream());
            Thread remote = new Thread(new RemoteReader());
            remote.start();
        } catch(Exception ex) {
            System.out.println("couldn't connect - you'll have to play alone.");
        }
        setUpMidi();
        buildGUI();
    } // close startUp

    public void buildGUI() {

        theFrame = new JFrame("Cyber BeatBox");
        BorderLayout layout = new BorderLayout();
        JPanel background = new JPanel(layout);
        background.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        checkboxList = new ArrayList<JCheckBox>();

        Box buttonBox = new Box(BoxLayout.Y_AXIS);
        JButton start = new JButton("Start");
        start.addActionListener(new MyStartListener());
        buttonBox.add(start);

        JButton stop = new JButton("Stop");
        stop.addActionListener(new MyStopListener());
        buttonBox.add(stop);

        JButton upTempo = new JButton("Tempo Up");
        upTempo.addActionListener(new MyUpTempoListener());
        buttonBox.add(upTempo);

        JButton downTempo = new JButton("Tempo Down");
        downTempo.addActionListener(new MyDownTempoListener());
        buttonBox.add(downTempo);

        JButton sendIt = new JButton("sendIt");
        sendIt.addActionListener(new MySendListener());
        buttonBox.add(sendIt);

        JButton saveIt = new JButton("serializeIt"); // new button
        saveIt.addActionListener(new MySavePatternListener());
        buttonBox.add(saveIt);

        JButton loadIt = new JButton("restore"); // new button
        loadIt.addActionListener(new MyLoadPatternListener());
        buttonBox.add(loadIt);

        JButton randomButton = new JButton("Random Pattern"); // new button
        randomButton.addActionListener(new MyRandomPatternListener());
        buttonBox.add(randomButton);

        // Foundation patterns combo
        JComboBox<String> foundationBox =
                new JComboBox<>(foundationPatterns.keySet().toArray(new String[0]));
        foundationBox.addActionListener(new MyFoundationPatternListener());
        buttonBox.add(foundationBox);

        userMessage = new JTextField();
        buttonBox.add(userMessage);

        incomingList = new JList();
        incomingList.addListSelectionListener(new MyListSelectionListener());
        incomingList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane theList = new JScrollPane(incomingList);
        buttonBox.add(theList);
        incomingList.setListData(listVector); // no data to start with

        Box nameBox = new Box(BoxLayout.Y_AXIS);
        for (int i = 0; i < 16; i++) {
            nameBox.add(new Label(instrumentNames[i]));
        }

        background.add(BorderLayout.EAST, buttonBox);
        background.add(BorderLayout.WEST, nameBox);

        theFrame.getContentPane().add(background);
        GridLayout grid = new GridLayout(16,16);
        grid.setVgap(1);
        grid.setHgap(2);
        mainPanel = new JPanel(grid);
        background.add(BorderLayout.CENTER, mainPanel);

        for (int i = 0; i < 256; i++) {
            JCheckBox c = new JCheckBox();
            c.setSelected(false);
            checkboxList.add(c);
            mainPanel.add(c);
        } // end loop

        theFrame.setBounds(50,50,300,300);
        theFrame.pack();
        theFrame.setVisible(true);
    } // close buildGUI

    private void loadFoundationPatterns() {

        boolean[] jazz = new boolean[256];
        boolean[] rock = new boolean[256];
        boolean[] reggae = new boolean[256];

        /*
         * Instrument indexes:
         * 0  - Bass Drum
         * 1  - Closed Hi-Hat
         * 3  - Acoustic Snare
         */

        // ---------- JAZZ ----------
        // Hi-hat: swing feel (every 2 beats)
        for (int beat = 0; beat < 16; beat += 2) {
            jazz[1 * 16 + beat] = true;
        } // end loop

        // Snare: beats 2 and 4
        jazz[3 * 16 + 4]  = true;
        jazz[3 * 16 + 12] = true;

        // Bass drum: light on beat 1
        jazz[0 * 16 + 0] = true;

        // ---------- ROCK ----------
        // Hi-hat: straight 8ths
        for (int beat = 0; beat < 16; beat += 2) {
            rock[1 * 16 + beat] = true;
        } // end loop

        // Bass drum: beats 1 and 3
        rock[0 * 16 + 0] = true;
        rock[0 * 16 + 8] = true;

        // Snare: beats 2 and 4
        rock[3 * 16 + 4]  = true;
        rock[3 * 16 + 12] = true;

        // ---------- REGGAE ----------
        // Hi-hat: off-beat
        for (int beat = 1; beat < 16; beat += 2) {
            reggae[1 * 16 + beat] = true;
        } // end loop

        // Snare: beat 3 (classic reggae feel)
        reggae[3 * 16 + 8] = true;

        // Bass drum: light on beat 1
        reggae[0 * 16 + 0] = true;

        foundationPatterns.put("Jazz", jazz);
        foundationPatterns.put("Rock", rock);
        foundationPatterns.put("Reggae", reggae);
    } // close loadFoundationPatterns

    public void loadPatternFromFile() {
        JFileChooser fc = new JFileChooser();
        if (fc.showOpenDialog(theFrame) == JFileChooser.APPROVE_OPTION) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fc.getSelectedFile()))) {
                boolean[] pattern = (boolean[]) ois.readObject();
                changeSequence(pattern);
            } catch(Exception e) {e.printStackTrace();}
        }
    } // close loadPatternFromFile

    public void saveCurrentPattern() {
        boolean[] checkboxState = new boolean[256];
        for (int i = 0; i < 256; i++) {
            checkboxState[i] = checkboxList.get(i).isSelected();
        } // end loop

        JFileChooser fc = new JFileChooser();
        if (fc.showSaveDialog(theFrame) == JFileChooser.APPROVE_OPTION) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fc.getSelectedFile()))) {
                oos.writeObject(checkboxState);
                System.out.println("Pattern saved!");
            } catch(Exception e) {e.printStackTrace();}
        }
    } // close saveCurrentPattern

    public void setUpMidi() {
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequence = new Sequence(Sequence.PPQ,4);
            track = sequence.createTrack();
            sequencer.setTempoInBPM(120);
        } catch(Exception e) {e.printStackTrace();}

    } // close setUpMidi

    public void buildTrackAndStart() {
        ArrayList<Integer> trackList = null; // this will hold the instruments for each
        sequence.deleteTrack(track);
        track = sequence.createTrack();

        for (int i = 0; i < 16; i++) {

            trackList = new ArrayList<Integer>();

            for (int j = 0; j < 16; j++) {
                JCheckBox jc = (JCheckBox) checkboxList.get(j + (16*i));
                if (jc.isSelected()) {
                    int key = instruments[i];
                    // trackList.add(new Integer(key)); // deprecated since Java 9
                    trackList.add(Integer.valueOf(key));
                } else {
                    trackList.add(null); // because this slot should be empty in the track
                }
            } // close inner loop
            makeTracks(trackList);
        } // close outer loop
        track.add(makeEvent(192,9,1,0,15)); // - so we always go to full 16 beats
        try {
            sequencer.setSequence(sequence);
            sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
            sequencer.start();
            sequencer.setTempoInBPM(120);
        } catch(Exception e) {e.printStackTrace();}
    } // close method

    public class MyStartListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            buildTrackAndStart();
        } // close actionPerformed
    } // close inner class

    public class MyStopListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            sequencer.stop();
        } // close actionPerformed
    } // close inner class

    public class MyUpTempoListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            float tempoFactor = sequencer.getTempoFactor();
            sequencer.setTempoFactor((float)(tempoFactor * 1.03));
        } // close actionPerformed
    } // close inner class

    public class MyDownTempoListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            float tempoFactor = sequencer.getTempoFactor();
            sequencer.setTempoFactor((float)(tempoFactor * .97));
        } // close actionPerformed
    } // close inner class

    public class MySendListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            // make an arraylist of just the STATE of the checkboxes
            boolean[] checkboxState = new boolean[256];
            for (int i = 0; i < 256; i++) {
                JCheckBox check = (JCheckBox) checkboxList.get(i);
                if (check.isSelected()) {
                    checkboxState[i] = true;
                }
            } // close loop
            try {
                out.writeObject(userName + nextNum++ + ": " + userMessage.getText());
                out.writeObject(checkboxState);
            } catch(Exception ex) {
                System.out.println("Sorry dude. Could not send it to the server.");
            }
            userMessage.setText("");
        } // close actionPerformed
    } // close inner class

    public class MySavePatternListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            saveCurrentPattern();
        } // close actionPerformed
    } // close inner class

    public class MyLoadPatternListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            loadPatternFromFile();
        } // close actionPerformed
    } // close inner class

    public class MyRandomPatternListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            for (JCheckBox c : checkboxList) {
                c.setSelected(Math.random() < 0.3);
            } // close loop
        } // close actionPerformed
    } // close inner class

    public class MyFoundationPatternListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            JComboBox cb = (JComboBox) ev.getSource();
            String style = (String) cb.getSelectedItem();
            if (style != null) {
                boolean[] pattern = foundationPatterns.get(style);
                changeSequence(pattern);
                sequencer.stop();
                buildTrackAndStart();
            }
        } // close actionPerformed
    } // close inner class

    public class MyListSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent le) {
            if (!le.getValueIsAdjusting()) {
                String selected = (String) incomingList.getSelectedValue();
                if (selected != null) {
                    // now go to the map, and change the sequence
                    boolean[] selectedState = (boolean[]) otherSeqsMap.get(selected);
                    changeSequence(selectedState);
                    sequencer.stop();
                    buildTrackAndStart();
                }
            }
        } // close valueChanged
    } // close inner class

    public class RemoteReader implements Runnable {
        boolean[] checkboxState = null;
        Object obj = null;
        public void run() {
            try {
                while((obj=in.readObject()) != null) {
                    System.out.println("got an object from server");
                    System.out.println(obj.getClass());
                    String nameToShow = (String) obj;
                    checkboxState = (boolean[]) in.readObject();
                    otherSeqsMap.put(nameToShow, checkboxState);
                    listVector.add(nameToShow);
                    incomingList.setListData(listVector);
                } // close while
            } catch(Exception ex) {ex.printStackTrace();}
        } // close run
    } // close inner class

    public void changeSequence(boolean[] checkboxState) {
        for (int i = 0; i < 256; i++) {
            JCheckBox check = (JCheckBox) checkboxList.get(i);
            if (checkboxState[i]) {
                check.setSelected(true);
            } else {
                check.setSelected(false);
            }
        } // close loop
    } // close changeSequence

    public void makeTracks(ArrayList list) {
        Iterator it = list.iterator();
        for (int i = 0; i < 16; i++) {
            Integer num = (Integer) it.next();
            if (num != null) {
                int numKey = num.intValue();
                track.add(makeEvent(144,9,numKey, 100, i));
                track.add(makeEvent(128,9,numKey,100, i + 1));
            }
        } // close loop
    } // close makeTracks()

    public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
        MidiEvent event = null;
        try {
            ShortMessage a = new ShortMessage();
            a.setMessage(comd, chan, one, two);
            event = new MidiEvent(a, tick);
        }catch(Exception e) { }
        return event;
    } // close makeEvent

} // close class
