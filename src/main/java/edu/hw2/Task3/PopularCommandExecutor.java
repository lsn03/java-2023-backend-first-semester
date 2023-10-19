package edu.hw2.Task3;

import edu.hw2.Task3.connection.Connection;
import edu.hw2.Task3.manager.ConnectionManager;


public final class PopularCommandExecutor {

    private final ConnectionManager manager;
    private final Retrier retrier;

    public PopularCommandExecutor(ConnectionManager manager, Retrier retrier) {
        this.manager = manager;

        this.retrier = retrier;
    }

    public void updatePackages() throws Exception {
        tryExecute("apt update && apt upgrade -y");
    }

    public int getAttempts() {
        return retrier.getAttempts();
    }

    void tryExecute(String command) throws Exception {

        Connection connection = manager.getConnection();

        retrier.procceed(connection, command);

    }


}
