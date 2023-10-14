package edu.hw2.Task3;

import edu.hw2.Task3.connection.Connection;
import edu.hw2.Task3.manager.ConnectionManager;
import edu.hw2.Task3.manager.DefaultConnectionManager;
import edu.hw2.Task3.manager.FaultyConnectionManager;

public class Server {
    private PopularCommandExecutor executor;

//    private ConnectionManager connectionManager;

    public Server(PopularCommandExecutor executor) {
        this.executor = executor;
    }

    public void execute() {
        try {
            executor.updatePackages();
        } catch (Exception e) {
            System.out.println("Catched "+ e.getMessage() +" " +e.getCause());
        }
    }

    public static void main(String[] args) {
        ConnectionManager manager = new FaultyConnectionManager();
        int maxAttempts = 10;
        PopularCommandExecutor popularCommandExecutor = new PopularCommandExecutor(manager,maxAttempts);
        Server server = new Server(popularCommandExecutor);
        server.execute();
    }
}
