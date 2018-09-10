package WalletWatcher.IO.SavedInfo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;


public class YearMonth implements Serializable {

    private final static short NUMBEROFMONTHS = 12;
    private int year;
    private boolean[] months;

    public YearMonth(LocalDate date){
        this.months = new boolean[NUMBEROFMONTHS];
        this.year = date.getYear();
        months[date.getMonthValue()-1] = true;
    }

    public void addMonth(LocalDate date){
        months[date.getMonthValue()-1] = true;
    }

    public void addMonth(Month month){
        months[month.getValue()-1] = true;
    }

    public void removeMonth(LocalDate date){
        months[date.getMonthValue()-1] = false;
    }

    public void removeMonth(Month month){
        months[month.getValue()-1] = false;
    }

    public boolean containsMonth(LocalDate date){
        return months[date.getMonthValue()-1];
    }

    public boolean containsMonth(Month month){
        return months[month.getValue()-1];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(int i = 0;i<months.length;i++){
            sb.append("For Month " + i + " = " + months[i] + "\n");
        }
        return sb.toString();
    }
}
