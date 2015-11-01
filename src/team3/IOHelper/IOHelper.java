package team3.IOHelper;


import java.io.*;

/**
 * Created by serhii on 25.10.15.
 */
public class IOHelper {


    public static void saveObj(String path, Object object) {

        // try-with-resources since 1.7
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(object);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static String readFrom(String path) throws FileNotFoundException {
        InputStream is = new FileInputStream(path);

        String res = "";
        try {
            int byteInfo = 0;
            while ((byteInfo = is.read()) != -1) {

                //System.out.print(byteInfo + " ");
                res += (char) byteInfo;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;


    }


    public static void writeTo(String path, String src, boolean append) throws FileNotFoundException {
        OutputStream os = new FileOutputStream(path, append);

        byte[] bytes = src.getBytes();

        try {
            os.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Object loadObj(String path) throws FileNotFoundException {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))){
            return ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
