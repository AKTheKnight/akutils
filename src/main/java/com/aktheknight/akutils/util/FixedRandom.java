package com.aktheknight.akutils.util;

import java.util.Random;

public class FixedRandom extends Random {

    //return 0 regardless
    //used for always growing plant
    @Override
    public int nextInt(int i)
    {
        return 0;
    }
}
