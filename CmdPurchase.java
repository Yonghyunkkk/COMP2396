
public class CmdPurchase implements Command {

	@Override
	public String execute(VendingMachine v, String cmdPart) {
		String output = v.checkProduct(cmdPart);
		
		// Return output
		return output;
	}

}
