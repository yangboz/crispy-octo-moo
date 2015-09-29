package crispy_octo_moo.dto;

/**
 * Created by yangboz on 9/30/15.
 */
public class DealObject {
    public int rank;
    public String content;
    public long balance;

    public DealObject(int rank, String content, long balance) {
        this.rank = rank;
        this.content = content;
        this.balance = balance;
    }
}
