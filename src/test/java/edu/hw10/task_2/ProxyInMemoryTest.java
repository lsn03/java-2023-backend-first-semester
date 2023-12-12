package edu.hw10.task_2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ProxyInMemoryTest {


    @Test
    public void inMemoryTest1() {


        SummatorImpl summator = new SummatorImpl();
        Summator summatorProxy = CacheProxy.create(summator, Summator.class);
        int a = 1, b = 5;
        Object[] args = new Object[]{a, b};
        summatorProxy.summ(a, b);
        CacheProxy cacheProxy = (CacheProxy) Proxy.getInvocationHandler(summatorProxy);


        Map<Object, Object> expectedMap = Map.of(Arrays.hashCode(args), 6);
        assertEquals(expectedMap, cacheProxy.getInMemoryCache());
    }

    @Test
    public void inMemoryTest2() {
        Map<Object, Object> expectedMap = new HashMap<>();

        SummatorImpl summator = new SummatorImpl();
        Summator summatorProxy = CacheProxy.create(summator, Summator.class);
        CacheProxy cacheProxy = (CacheProxy) Proxy.getInvocationHandler(summatorProxy);
        int a = 1, b = 5;
        Object[] args = new Object[]{a, b};
        expectedMap.put(Arrays.hashCode(args), a + b);
        summatorProxy.summ(a, b);

        a = 2;
        b = 7;
        args = new Object[]{a, b};
        expectedMap.put(Arrays.hashCode(args), a + b);
        summatorProxy.summ(a, b);
        assertTrue(cacheProxy.isLastRequestFromInvoke());

        summatorProxy.summ(a, b);

        assertFalse(cacheProxy.isLastRequestFromInvoke());

        assertEquals(expectedMap, cacheProxy.getInMemoryCache());

    }

}
