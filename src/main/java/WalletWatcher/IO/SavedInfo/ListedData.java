package WalletWatcher.IO.SavedInfo;

import java.io.*;
import java.util.ArrayList;

public class ListedData implements Serializable {

    private final static String DATA = "DATA";
    private ArrayList<YearMonth> saved;
    private File dataInfo;

    public ListedData() throws IOException {
        saved = new ArrayList<>();

        dataInfo = new File(".\\" + DATA + "\\" + "savedInfo.dat");
        if(!dataInfo.exists()){
            dataInfo.createNewFile();
        }else{
            read();
        }

    }

    private void read() {

        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(dataInfo))){
            saved = (ArrayList<YearMonth>)in.readObject();
            dataInfo = (File)in.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void write(){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(dataInfo))){
            out.writeObject(saved);
            out.writeObject(dataInfo);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean add(YearMonth yearMonth){
        return saved.add(yearMonth);
    }
}
