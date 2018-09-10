package WalletWatcher.Data.Datatypes;

import WalletWatcher.util.AccountingTools;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Transaction implements Comparable<Transaction>, Serializable{

    private static HashMap<String,Client> ClientCollection = new HashMap<>();
    private static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private LocalDate date;
    private LocalDate valuta;
    private Client fromClient;
    private Client toClient;
    private String iban;
    private String bic;
    private String use;
    private String currentcy;
    private double amount;
    private int kontNR;
    private int bankNR;
    private String type;

    public Transaction(LocalDate date, LocalDate valuta, Client fromClient, Client toClient, String iban, String bic, String use, String currentcy, double amount, String type) {
        this(date, valuta, fromClient, toClient, iban, bic, use, currentcy, amount, AccountingTools.getKonNRbyIBAN(iban), AccountingTools.getBLZbyIBAN(iban), type);
    }

    public Transaction(LocalDate date, LocalDate valuta, Client fromClient, Client toClient, String iban, String bic, String use, String currentcy, double amount, int kontNR, int bankNR, String type) {
        this.date = date;
        this.valuta = valuta;
        this.fromClient = fromClient;
        this.toClient = toClient;
        this.iban = iban;
        this.bic = bic;
        this.use = use;
        this.currentcy = currentcy;
        this.amount = amount;
        this.kontNR = kontNR;
        this.bankNR = bankNR;
        this.type = type;
    }

    public Transaction(String[] sTs){
        if(sTs.length>13) throw new IllegalArgumentException("Too many columns: " + sTs.length );

        for(int i = 0;i<sTs.length;i++){
           sTs[i] = sTs[i].replace("\"","");
        }

        this.date = LocalDate.parse(sTs[0],DTF);
        this.valuta = LocalDate.parse(sTs[1],DTF);
        setClients(sTs[2],sTs[3],sTs[12]);

        this.bankNR = AccountingTools.getBLZbyIBAN(sTs[5]);
        this.kontNR = AccountingTools.getKonNRbyIBAN(sTs[5]);
        this.iban = sTs[5];
        this.bic = sTs[7];
        this.use = sTs[8];
        this.currentcy = sTs[10];

        this.amount = Double.parseDouble(sTs[11].replace(",","."));
        this.type = sTs[12];

    }

    /**
     * Sets the Clients depending if its a Transaction to or from me
     * @param c1
     *              Client No. 1
     * @param c2
     *              Client No. 1
     * @param type
     *              The type of Transaction
     */
    private void setClients(String c1, String c2, String type){
        Client cl1 = checkHashMap(c1);
        Client cl2 = checkHashMap(c2);

        if(type.contains("S")){
            this.fromClient = cl1;
            this.toClient = cl2;
        }else if(type.contains("H")){
            this.toClient = cl1;
            this.fromClient =cl2;
        }
    }

    /**
     * Checks if Client is in HashMap and gets the reference or creates a new Client and returns the
     * new Client reference
     * @param s
     *              String to check if in HashMap
     * @return      Reference of a Client
     */
    private Client checkHashMap(String s) {
        if (ClientCollection.containsKey(s)) {
            return ClientCollection.get(s);
        } else {
            ClientCollection.put(s, new Client(s));
            return ClientCollection.get(s);
        }
    }

    public LocalDate getDate() {
        return date;
    }
    @Override
    public String toString() {
        return "Datum: " + date + ", From: " + fromClient.toString() + ", To: " + toClient + ", Betrag: " + amount
                + " , Typ: " + type;
    }

    @Override
    public int compareTo(@NotNull Transaction o) {
        return this.getDate().compareTo(o.getDate());
    }
}
