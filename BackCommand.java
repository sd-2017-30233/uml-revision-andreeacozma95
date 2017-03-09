
public class BackCommand implements Command {

private CommandReceiver command;
	
	public BackCommand ( CommandReceiver command) {
		this.command = command;
	}
	    
	@Override
	public void executeCommand() {
		command.back();
	}
}