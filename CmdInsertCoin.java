
public class CmdInsertCoin implements Command {

	@Override
	public String execute(VendingMachine v, String cmdPart) {
		Integer c = Integer.valueOf(cmdPart);
	    // Add the coin to Coin Slot
	    v.insertCoin(c);
	    // Get total coins
	    int total = v.totalCoins();
	    
	    return "Inserted a $" +c+ " coin. $" +total+ " in total.";
	    // return something. Format: "Inserted a $x coin. $y in total."
	}

}
