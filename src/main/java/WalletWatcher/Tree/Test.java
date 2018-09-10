package WalletWatcher.Tree;

import WalletWatcher.Data.ImportedTransaction;
import WalletWatcher.Data.Datatypes.Transaction;
import WalletWatcher.IO.TransactionReaderVB;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Test {

    public static void main(String[] args) {

        File f = new File("bk.csv");
        ImportedTransaction ts = null;
        if (!f.exists()) {
            System.out.println("...does not exist");
        } else {
            try (TransactionReaderVB trVB = new TransactionReaderVB(new FileReader(f))) {
                ts = trVB.getTransaction();
                System.out.println(ts);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        AVLTree<Transaction> tree = new AVLTree<>();
        for(Transaction t:ts.getAll()){
            tree.insert(t);
        }

        tree.PrintTree();
    }
}
