
public class CmdRejectCoins implements Command {

	@Override
	public String execute(VendingMachine v, String cmdPart) {
		
		String output = v.rejectCoins();

		return output;
	}

}
