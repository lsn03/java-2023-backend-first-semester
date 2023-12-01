package edu.hw8.server_package;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private final int port;
    private final int connections;


    private boolean isRunning = true;
    private ExecutorService executorService;
    private ServerSocket serverSocket;


    public Server() {
        this(Configuration.DEFAULT_PORT, Configuration.DEFAULT_CONNECTIONS);
    }


    public Server(Integer port, Integer connections) {
        validate(port, connections);
        this.port = port;
        this.connections = connections;
    }

    public void launchServer() {

        executorService = Executors.newFixedThreadPool(connections);


        try {
            serverSocket = new ServerSocket(port);

            while (isRunning) {

                Socket socket = serverSocket.accept();

                executorService.submit(() -> {
                    handleClient(socket);
                });

            }
        } catch (IOException e) {
            if (isRunning) {
                throw new RuntimeException(e);
            }
        } finally {
            shutdownServer();
        }
    }


    public void shutdownServer() {
        isRunning = false;
        try {
            serverSocket.close();
        } catch (IOException ignored) {

        }

        executorService.shutdown();
        executorService.close();

    }

    private void handleClient(Socket socket) {
        InputStream inputStream;
        OutputStream outputStream;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            ByteBuffer buffer = ByteBuffer.allocate(Configuration.DEFAULT_BUFFER_CAPACITY);
            int readBytes = inputStream.read(buffer.array(), buffer.position(), buffer.remaining());
            if (readBytes > 0) {
                buffer.position(buffer.position() + readBytes);
                String clientRequest = new String(buffer.array(), 0, buffer.position());
                String response = processRequest(clientRequest);
                outputStream.write(response.getBytes());

            }
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private String processRequest(String clientRequest) {
        return switch (clientRequest.toLowerCase()) {
            case "личности" -> "Не переходи на личности там, где их нет";
            case "оскорбления" ->
                    "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами";
            case "глупый" ->
                    "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.";
            case "интеллект" -> "Чем ниже интеллект, тем громче оскорбления";
            default -> "Неизвестный запрос";
        };

    }

    private void validate(Integer portValidate, Integer connectionsValidate) {
        Objects.requireNonNull(portValidate);
        Objects.requireNonNull(connectionsValidate);

        int port = portValidate;
        int connection = connectionsValidate;
        if (port < 0 || port > Configuration.MAX_PORT || connection < 0 || connection > Configuration.DEFAULT_CONNECTIONS) {
            throw new IllegalArgumentException();
        }
    }
}
