package WalletWatcher.IO;

import WalletWatcher.Data.ImportedTransaction;
import WalletWatcher.Data.Datatypes.Transaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.regex.Pattern;

public class TransactionReaderVB extends BufferedReader {

    private Pattern deliminator;
    private boolean dataLines = false;
    /**
     * Create a new line-numbering reader, using the default input-buffer
     * size.
     *
     * @param in A Reader object to provide the underlying stream
     */
    public TransactionReaderVB(Reader in, Pattern deliminator) {
        super(in);
        this.deliminator = deliminator;
    }

    public TransactionReaderVB(Reader in) {
        this(in,Pattern.compile(";"));
    }

    public ImportedTransaction getTransaction() throws IOException{
        String s = readTransactionLine();
        ImportedTransaction in = new ImportedTransaction();

        while(true) {
                if (s.contains("false")) {
                    break;
                }
                if (s.contains("ABSCHLUSS PER")) {
                } else {
                    String[] action = s.split(deliminator.toString());
                    in.add(new Transaction(action));
                }
            s =readTransactionLine();
        }
        return in;
    }

    private String readTransactionLine() throws IOException{

        StringBuilder sb = new StringBuilder();

        for(;;){
            String s = readRelevantLine();
            if(s !=null) {
                if(s.contains("Anfangssaldo")){
                    return "false";
                }
                if (s.contains("\"S\"")) {
                    sb.append(s);
                    break;
                }
                if (s.contains("\"H\"")) {
                    sb.append(s);
                    break;
                }
                sb.append(s);
            }else return "false";
        }
        return sb.toString();
    }


    /**
     * Read a line of text.  Whenever a <a href="#lt">line terminator</a> is
     * read the current line number is incremented.
     *
     * @return A String containing the contents of the line, not including
     * any <a href="#lt">line termination characters</a>, or
     * {@code null} if the end of the stream has been reached
     * @throws IOException If an I/O error occurs
     */
    private String readRelevantLine() throws IOException {


        if(dataLines){
            return super.readLine();
        }else {
            for (; ; ) {
                String s = super.readLine();
                if (s.contains("\"Valuta\"")){
                    dataLines = true;
                    break;
                }
            }
        }
        return super.readLine();
    }
}
