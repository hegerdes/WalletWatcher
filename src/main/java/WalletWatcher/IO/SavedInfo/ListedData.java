package WalletWatcher.IO.SavedInfo;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class ListedData implements Serializable {

    private boolean changed;
    private final static String DATA = "DATA";
    private ArrayList<YearMonth> saved;
    private HashMap<Integer,YearMonth> years;
    private File dataInfo;

    public ListedData() throws IOException {
        saved = new ArrayList<>();
        years = new HashMap<>();

        changed = false;
        dataInfo = new File(".\\" + DATA + "\\" + "savedInfo.dat");
        if(!dataInfo.exists()){
            dataInfo.createNewFile();
        }else{
            read();
        }

    }

    public boolean addDate(LocalDate date){
        if(years.containsKey(date.getYear())){
            return years.get(date.getYear()).addMonth(date.getMonth());
        }else {
            years.put(date.getYear(),new YearMonth(date));
            return true;
        }
    }

    public boolean removeDate(LocalDate date){
        if(years.containsKey(date.getYear())){
            return years.get(date.getYear()).removeMonth(date.getMonth());
        }else return false;
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
}
