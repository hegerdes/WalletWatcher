package WalletWatcher.util;

public class AccountingTools {
    public AccountingTools() {
    }

    public static int[] breakDownIBAN(String iban) {
        int[] out = new int[]{Integer.parseInt(iban.substring(4, 12)), Integer.parseInt(iban.substring(12))};
        return out;
    }

    public static int getBLZbyIBAN(String IBAN) {
        return Integer.parseInt(IBAN.substring(4, 12));
    }

    public static int getKonNRbyIBAN(String IBAN) {
        return Integer.parseInt(IBAN.substring(12));
    }
}
