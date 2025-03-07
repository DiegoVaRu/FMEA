package model.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import model.CausaFalla;
import model.ModoFalla;

public class DATA implements Serializable {
    
    public static void saveData(ArrayList<ModoFalla> list) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("fallas.data"))) {
            oos.writeObject(list);
            System.out.println("--Data Saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void saveExtraData(ArrayList<CausaFalla> list) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("fallas.data"))) {
            oos.writeObject(list);
            System.out.println("--Data Saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<ModoFalla> loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("fallas.data"))) {
            ArrayList<ModoFalla> list = (ArrayList<ModoFalla>) ois.readObject();
            System.out.println("--Data Load");
            return list;
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }        
    }

}
