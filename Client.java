public class Client 
{
    public static void main(String[] args) {
    	
        CommandReceiver command = new CommandReceiver();
       
        CancelCommand cancelCommand = new CancelCommand(command);
        ExitCommand exitCommand = new ExitCommand(command);
        BackCommand backCommand = new BackCommand(command);
        
        Invoker invoker = new Invoker();

        invoker.setCommand(exitCommand);
        invoker.execute();
        
        invoker.setCommand(cancelCommand);
        invoker.execute();
        
        invoker.setCommand(backCommand);
        invoker.execute();
    }
}