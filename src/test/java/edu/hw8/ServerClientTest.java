package edu.hw8;

import edu.hw8.server_package.Client;
import edu.hw8.server_package.Server;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

public class ServerClientTest {

    @Test
    public void testServerResponse() {
        Server server = new Server();
        Thread serverThread = new Thread(() -> server.launchServer());
        serverThread.start();


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Client client = new Client();
        Thread clientThread = new Thread(() -> {
            client.launchClient();
        });
        clientThread.start();


        try {
            clientThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        server.shutdownServer();
        try {
            serverThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        var actualdMap = client.getServerResponse();
        var expectedMap = new HashMap<>();

        expectedMap.put("личности", "Не переходи на личности там, где их нет");
        expectedMap.put("оскорбления", "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами");
        expectedMap.put("глупый", "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.");
        expectedMap.put("интеллект", "Чем ниже интеллект, тем громче оскорбления");

        assertEquals(expectedMap, actualdMap);
    }

    @Test
    public void testExceptions() {

        assertThrows(IllegalArgumentException.class, () -> new Server(-1, 4));
        assertThrows(IllegalArgumentException.class, () -> new Server(1000000, 4));
        assertThrows(IllegalArgumentException.class, () -> new Server(3, -1));
        assertThrows(IllegalArgumentException.class, () -> new Server(3, 2000));


        assertThrows(NullPointerException.class, () -> new Server(null, 2000));
        assertThrows(NullPointerException.class, () -> new Server(null, null));
        assertThrows(NullPointerException.class, () -> new Server(2000, null));
    }


}
