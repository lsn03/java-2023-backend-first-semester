package edu.hw8.server_package;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Client {
    private final int port;
    private final String address;
    private String[] userInput;
    private int currentPosition = 0;
    private boolean isRunning = true;

    private Map<String, String> serverResponse;

    public Client() {
        this(Configuration.DEFAULT_PORT, "localhost");
    }

    public Client(int port, String address) {
        this.port = port;
        this.address = address;
        serverResponse = new HashMap<>();
        userInput = new String[]{"личности", "оскорбления", "глупый", "интеллект"};
    }

    public Map<String, String> getServerResponse() {
        return serverResponse;
    }

    public void launchClient() {
        Socket socket = null;
        try {

            while (isRunning && currentPosition < userInput.length) {
                socket = new Socket(address, port);

                String line = userInput[currentPosition++];


                OutputStream outputStream = socket.getOutputStream();
                outputStream.write(line.getBytes());


                InputStream inputStream = socket.getInputStream();
                byte[] buffer = new byte[Configuration.DEFAULT_BUFFER_CAPACITY];
                int bytesRead = inputStream.read(buffer);
                String response = new String(buffer, 0, bytesRead);
                serverResponse.put(line, response);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
