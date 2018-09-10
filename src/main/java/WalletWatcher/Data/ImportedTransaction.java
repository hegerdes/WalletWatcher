package WalletWatcher.Data;

import WalletWatcher.Data.Datatypes.Transaction;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class ImportedTransaction {

    private LocalDate minDate;
    private LocalDate maxDate;

    private ArrayList<Transaction> all;
    private ArrayList<Transaction>[] perMonth;

    public ImportedTransaction(){
        all = new ArrayList<>();
    }

    public ImportedTransaction(Transaction transaction){
        all = new ArrayList<>();
        add(transaction);
    }

    public ImportedTransaction(@org.jetbrains.annotations.NotNull Collection<Transaction> collection){
        all = new ArrayList<>();
        for(Object o: collection){
                add((Transaction)o);
        }
    }

    /**
     * Adds a new Transaction
     * @param transaction The Trasaction to be added
     * @return if add was successful
     */
    public boolean add(Transaction transaction){
        checkDate(transaction);
        return all.add(transaction);
    }

    public ArrayList<Transaction>[] sort(){
        int yearspan = getMaxDate().getYear() - getMinDate().getYear() + 1;
        int size = 12 * yearspan;
        perMonth = new ArrayList[size];
        for(int i = 0;i<size;i++){
            perMonth[i] = new ArrayList<Transaction>();
        }
        if(yearspan == 1) yearspan = 0;
        for(Transaction t: all){
            int index = t.getDate().getMonthValue() - getMinDate().getMonthValue();
            perMonth[yearspan + t.getDate().getMonthValue() -1 ].add(t);
        }
        return perMonth;
    }

    /**
     * Updates the Min and Max Date if the addes Transaction is older than min or newer than max
     * @param t The Transaction to be added
     */
    private void checkDate(Transaction t){
        if(minDate == null && maxDate == null){
            minDate = t.getDate();
            maxDate = t.getDate();
        }

        if(t.getDate().isAfter(maxDate)){
            maxDate = t.getDate();
        }

        if(t.getDate().isBefore(minDate)){
            minDate = t.getDate();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Von " + minDate + " bis " + maxDate + "\n");
        for(Transaction t:all){
            sb.append(t + "\n");
        }
        return sb.toString();
    }

    public LocalDate getMinDate() {
        return minDate;
    }

    public LocalDate getMaxDate() {
        return maxDate;
    }

    public ArrayList<Transaction> getAll(){
        return all;
    }
}
