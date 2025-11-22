package com.headfirstjava.chapter09;

import java.util.*;

public class TestLifeSupportSim {
    public static void main(String [] args) {
        ArrayList aList = new ArrayList();
        V2Radiator v2 = new V2Radiator(aList);
        V3Radiator v3 = new V3Radiator(aList);
        for(int z=0; z<20; z++) {
            RetentionBot ret = new RetentionBot(aList);
        }

        // adding this to make sure the power is correct:
        int totalPower = 0;
        for(Object o : aList) {
            totalPower += ((SimUnit) o).powerUse();
        }
        System.out.println("Total power: " + totalPower);
    }
}
