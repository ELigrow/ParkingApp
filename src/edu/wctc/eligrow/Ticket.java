package edu.wctc.eligrow;

/**
 * @author eplig
 * @version 1.0
 */
public class Ticket {

    public boolean wasLost;
    public int inTime;
    public int outTime;
    public int cost;

    /**
     * Constructor for Ticket object when reading from file
     * @param inTime Int: Ticket check-in time
     * @param outTime Int: Ticket check-out time
     * @param cost Int: Ticket cost
     */
    public Ticket(int inTime, int outTime, int cost){
        this.inTime = inTime;
        this.outTime = outTime;
        this.cost = cost;
    }

    /**
     * Constructor for Ticket object writing in program. Calculates cost.
     * @param inTime Int: Ticket check-in time
     * @param outTime Int: Ticket check-out time
     */
    public Ticket(int inTime, int outTime){
        this.inTime = inTime;
        this.outTime = outTime;
        if((outTime - inTime) < 3){
            cost = 5;
        }else if ((outTime - inTime) < 11){
            cost = ((outTime - inTime)-3) + 5;
        }else{
            cost = 15;
        }
    }

    /**
     * Constructor for Ticket object if lost
     * @param lost Boolean: Is or is not lost
     */
    public Ticket(boolean lost){
        wasLost = true;
        cost = 25;

    }

    /**
     * Converts Ticket object to writable text
     * @return String: String conversion of Ticket object
     */
    public String Write() {
        String str;
        if(cost != 25) {
            str = inTime + "," + outTime + "," + cost;
        }else{
            str = wasLost + "," + cost;
        }
        return str;
    }
}
