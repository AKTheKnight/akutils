package com.aktheknight.akutils.util;

import java.util.Random;

public class FixedRandom extends Random {
    // Guaranteed to be random
    @Override
    public int nextInt(int i)
    {
        return 0;
    }
}
