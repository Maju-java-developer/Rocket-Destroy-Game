package core;

import java.util.Random;

public class RandomRange {

    public static int randomInt(int min, int max){
        Random rand = new Random();
        return rand.nextInt(max + 1 - min) + min;
    }

    public static float randomFloat(float min, float max){
        Random rand = new Random();
        return min + rand.nextFloat() * (max - min);
    }
    
    public static double randomDouble(double min, double max){
        Random rand = new Random();
        return min + rand.nextDouble() * (max - min);
    }
    
}