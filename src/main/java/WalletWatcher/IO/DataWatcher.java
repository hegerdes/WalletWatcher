package WalletWatcher.IO;

import WalletWatcher.Data.ImportedTransaction;
import WalletWatcher.Data.Datatypes.Transaction;

import java.io.*;
import java.util.ArrayList;

public class DataWatcher {

    private final static String DATA = "DATA";

    public static void writeInput(ImportedTransaction it){

        String path = ".\\" + DATA + "\\" + it.getMinDate().getYear() + "\\" + it.getMinDate().getMonth();
        System.out.println(path);
        File file = new File(path);

        if(!file.exists()){
            file.mkdirs();
        }
        for(File f: file.listFiles()){
            System.out.println(f);
        }

        File out = new File(path + "\\1.ser");

        if(!out.exists()){
            try {
                out.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(out))){
            for(Transaction t: it.getAll()){
                oos.writeObject(t);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(out))) {
            ArrayList<Transaction> at = new ArrayList<>();

            Transaction t = (Transaction) ois.readObject();
            while (t != null) {
                System.out.println(t);
                t = (Transaction) ois.readObject();
            }
        } catch (EOFException e){

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
