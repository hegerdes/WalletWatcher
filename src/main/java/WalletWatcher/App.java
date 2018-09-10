package WalletWatcher;

import WalletWatcher.IO.DataWatcher;
import WalletWatcher.Data.Datatypes.Transaction;
import WalletWatcher.Data.ImportedTransaction;
import WalletWatcher.IO.TransactionReaderVB;
import java.io.*;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        File f = new File("bk.csv");

        ImportedTransaction ts = null;
        ArrayList<Transaction>[] sorted;

        if (!f.exists()) {
            System.out.println("...does not exist");
        } else {
           try(TransactionReaderVB trVB = new TransactionReaderVB(new FileReader(f))){

               ts = trVB.getTransaction();
               System.out.println(ts);

           } catch (FileNotFoundException e) {
               e.printStackTrace();
           } catch (IOException e) {
               e.printStackTrace();
           }
        }

        sorted = ts.sort();

        for(Transaction t: sorted[8]){
            System.out.println(t);
        }
    }
}
