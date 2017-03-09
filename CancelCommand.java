
public class CancelCommand implements Command {

	private CommandReceiver command;
	
	public CancelCommand ( CommandReceiver command) {
		this.command = command;
	}
	    
	@Override
	public void executeCommand() {
		command.cancel();
	}
}
