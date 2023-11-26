package edu.hw7;

import edu.hw7.person_database.CacheServiceDatabase;
import edu.hw7.person_database.LockedServiceDatabase;
import edu.hw7.person_database.Person;
import edu.hw7.person_database.PersonDatabase;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class PersonDBTest {
    private static Stream<Arguments> database() {
        return Stream.of(

                Arguments.of(new LockedServiceDatabase()),
                Arguments.of(new CacheServiceDatabase())
        );
    }

    @ParameterizedTest
    @MethodSource("database")
    public void cacheDbTest(PersonDatabase database) {

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        int n = (int) 1e3;
        executorService.submit(() -> {
            for (int i = 0; i < n; i++) {
                Person person = new Person(i, "Name_" + i, "Address_" + i, "Phone_" + i);
                database.add(person);
            }
        });
        executorService.submit(() -> {
            for (int i = 0; i < n; i += 3) {
                database.delete(i);
            }
        });
        executorService.submit(() -> {
            for (int i = 0; i < n; i++) {
                String name = "Name_" + i;
                String address = "Address_" + i;
                String phone = "Phone_" + i;
                var personByName = database.findByName(name);
                var personByAddress = database.findByAddress(address);
                var personByPhone = database.findByPhone(phone);
                if (!personByName.isEmpty()) {
                    assertEquals(1, personByName.size());
                }
                if (!personByAddress.isEmpty()) {
                    assertEquals(1, personByAddress.size());
                }
                if (!personByPhone.isEmpty()) {
                    assertEquals(1, personByPhone.size());
                }
            }
        });
        executorService.close();
    }
}
