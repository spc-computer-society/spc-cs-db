/*
 *     Comp-Soc-DB
 *     Copyright (C) 2020  Colin Chow
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package spc.compsoc.example;

import java.util.Random;

/**
 * The source of randomness.
 * All users of random number generation should use this class to share the same seed.
 */
public class RandomUtil {

    private static Random SRC;
    private static boolean init;


    private RandomUtil() {
        throw new AssertionError();
    }

    /**
     * Generates a random integer.
     * @return The random number generated
     */
    public static synchronized int nextInt() {
        if(SRC == null){
            SRC = new Random();
        }
        return SRC.nextInt();
    }

    /**
     * Generates a random double.
     * @return The random double generated
     */
    public static synchronized double nextDouble() {
        if(SRC == null){
            SRC = new Random();
        }
        return SRC.nextDouble();
    }

    /**
     * Sets the seed of the random number generator.
     * @param seed The seed to set
     */
    public static void setSeed(long seed){
        if(init) {
            return;
        }
        SRC = new Random(seed);
        init = true;
    }

    /**
     * Gets the next int between 0 and upper.
     * @param upper THe upper limit
     * @return THe random number
     */
    public static synchronized int getNextBoundedInt(int upper){
        if(SRC == null){
            SRC = new Random();
        }
        return SRC.nextInt(upper);
    }
}
