
public class ExitCommand implements Command {

private CommandReceiver command;
	
	public ExitCommand ( CommandReceiver command) {
		this.command = command;
	}
	    
	@Override
	public void executeCommand() {
		command.exit();
	}

}
