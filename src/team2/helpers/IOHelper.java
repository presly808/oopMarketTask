package ua.artcode.utils.io;

import ua.artcode.week6.serial.Robocop;

import java.io.*;
import java.util.Scanner;

/**
 * Created by serhii on 25.10.15.
 */
public class IOHelper {


    public static<T> void formatSave(String path, T o, MySerializator<T> serilaizator){
        try (PrintWriter pw = new PrintWriter(path)){
            String strObj = serilaizator.format(o);
            pw.print(strObj);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static<T> T formatLoad(String path, MySerializator<T> serializator) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(path));
        String res = "";
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            res += line + "\n";
        }

        return serializator.parse(res);

    }

    public static Robocop readRobocop(String path) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(path));
        Robocop robocop = new Robocop();

        while(sc.hasNextLine()){
            String line = sc.nextLine();
            String[] parts = line.split("=");
            String fieldName = parts[0];
            String fieldValue = parts[1];
            if("model".equals(fieldName)){
                robocop.setModel(fieldValue);
            } else if("health".equals(fieldName)){
                robocop.setHealth(Integer.parseInt(fieldValue));
            } //...
        }


        return robocop;

    }

    public static void saveRobocop(String path, Robocop robocop){
        try (PrintWriter pw = new PrintWriter(path)){

            pw.printf("%s=%s\n", "model", robocop.getModel());
            pw.printf("%s=%s\n", "health", robocop.getHealth());
            pw.printf("%s=%s\n", "power", robocop.getPower());
            pw.printf("%s=%s\n", "price", robocop.getPrice());

            pw.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


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
