// do not remove imports
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

class ArrayUtils {
    // define invert method here
    static <T> T[] invert(T[] array) {
        T tempVar;
        for (int i = 0; i < array.length/2; i++) {
            tempVar = array[i];
            array[i] = array[array.length-i-1];
            array[array.length-i-1] = tempVar;
        }
        return array;
    }
}