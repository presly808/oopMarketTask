package serhii;

import java.io.File;

/**
 * Created by serhii on 08.11.15.
 */
public class PathHelper {

    public static String findCurrentLocation(){
        return new File(".").getAbsolutePath();
    }


}
