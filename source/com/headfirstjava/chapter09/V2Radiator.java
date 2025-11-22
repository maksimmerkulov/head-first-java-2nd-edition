package com.headfirstjava.chapter09;

import java.util.*;

class V2Radiator {
    V2Radiator(ArrayList list) {
        System.out.println("making a v2 radiator");
        for(int x=0; x<5; x++) {
            list.add(new SimUnit("V2Radiator"));
        }
    }
}
