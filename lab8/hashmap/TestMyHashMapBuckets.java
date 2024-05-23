package hashmap;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 由Brendan Hu在2015年春季编写的测试
 * 2016年由Josh Hug修订
 * 2021年由Neil Kulkarni修订
 */
public class TestMyHashMapBuckets {

    @Test
    public void sanityGenericsTest() {
        // 测试不同类型的MyHashMap实现
        MyHashMap<String, Integer> a = new MyHashMapALBuckets<>();
        MyHashMap<String, Integer> b = new MyHashMapALBuckets<>();
        MyHashMap<Integer, String> c = new MyHashMapALBuckets<>();
        MyHashMap<Boolean, Integer> d = new MyHashMapALBuckets<>();

        a = new MyHashMapLLBuckets<>();
        b = new MyHashMapLLBuckets<>();
        c = new MyHashMapLLBuckets<>();
        d = new MyHashMapLLBuckets<>();

        a = new MyHashMapTSBuckets<>();
        b = new MyHashMapTSBuckets<>();
        c = new MyHashMapTSBuckets<>();
        d = new MyHashMapTSBuckets<>();

        a = new MyHashMapHSBuckets<>();
        b = new MyHashMapHSBuckets<>();
        c = new MyHashMapHSBuckets<>();
        d = new MyHashMapHSBuckets<>();

        a = new MyHashMapPQBuckets<>();
        b = new MyHashMapPQBuckets<>();
        c = new MyHashMapPQBuckets<>();
        d = new MyHashMapPQBuckets<>();
    }

    // 假设put/size/containsKey/get功能正常
    @Test
    public void sanityClearTest() {
        // 测试不同类型的MyHashMap实现的clear方法
        TestMyHashMap.sanityClearTest(new MyHashMapALBuckets<>());
        TestMyHashMap.sanityClearTest(new MyHashMapLLBuckets<>());
        TestMyHashMap.sanityClearTest(new MyHashMapTSBuckets<>());
        TestMyHashMap.sanityClearTest(new MyHashMapHSBuckets<>());
        TestMyHashMap.sanityClearTest(new MyHashMapPQBuckets<>());
    }

    // 假设put功能正常
    @Test
    public void sanityContainsKeyTest() {
        // 测试不同类型的MyHashMap实现的containsKey方法
        TestMyHashMap.sanityContainsKeyTest(new MyHashMapALBuckets<>());
        TestMyHashMap.sanityContainsKeyTest(new MyHashMapLLBuckets<>());
        TestMyHashMap.sanityContainsKeyTest(new MyHashMapTSBuckets<>());
        TestMyHashMap.sanityContainsKeyTest(new MyHashMapHSBuckets<>());
        TestMyHashMap.sanityContainsKeyTest(new MyHashMapPQBuckets<>());
    }

    // 假设put功能正常
    @Test
    public void sanityGetTest() {
        // 测试不同类型的MyHashMap实现的get方法
        TestMyHashMap.sanityGetTest(new MyHashMapALBuckets<>());
        TestMyHashMap.sanityGetTest(new MyHashMapLLBuckets<>());
        TestMyHashMap.sanityGetTest(new MyHashMapTSBuckets<>());
        TestMyHashMap.sanityGetTest(new MyHashMapHSBuckets<>());
        TestMyHashMap.sanityGetTest(new MyHashMapPQBuckets<>());
    }

    // 假设put功能正常
    @Test
    public void sanitySizeTest() {
        // 测试不同类型的MyHashMap实现的size方法
        TestMyHashMap.sanitySizeTest(new MyHashMapALBuckets<>());
        TestMyHashMap.sanitySizeTest(new MyHashMapLLBuckets<>());
        TestMyHashMap.sanitySizeTest(new MyHashMapTSBuckets<>());
        TestMyHashMap.sanitySizeTest(new MyHashMapHSBuckets<>());
        TestMyHashMap.sanitySizeTest(new MyHashMapPQBuckets<>());
    }

    // 假设get/containsKey功能正常
    @Test
    public void sanityPutTest() {
        // 测试不同类型的MyHashMap实现的put方法
        TestMyHashMap.sanityPutTest(new MyHashMapALBuckets<>());
        TestMyHashMap.sanityPutTest(new MyHashMapLLBuckets<>());
        TestMyHashMap.sanityPutTest(new MyHashMapTSBuckets<>());
        TestMyHashMap.sanityPutTest(new MyHashMapHSBuckets<>());
        TestMyHashMap.sanityPutTest(new MyHashMapPQBuckets<>());
    }

    @Test
    public void sanityKeySetTest() {
        // 测试不同类型的MyHashMap实现的keySet方法
        TestMyHashMap.sanityKeySetTest(new MyHashMapALBuckets<>());
        TestMyHashMap.sanityKeySetTest(new MyHashMapLLBuckets<>());
        TestMyHashMap.sanityKeySetTest(new MyHashMapTSBuckets<>());
        TestMyHashMap.sanityKeySetTest(new MyHashMapHSBuckets<>());
        TestMyHashMap.sanityKeySetTest(new MyHashMapPQBuckets<>());
    }

    // 测试一般功能及Map属性保持不变
    @Test
    public void functionalityTest() {
        // 测试不同类型的MyHashMap实现的一般功能
        TestMyHashMap.functionalityTest(new MyHashMapALBuckets<>(), new MyHashMapALBuckets<>());
        TestMyHashMap.functionalityTest(new MyHashMapLLBuckets<>(), new MyHashMapLLBuckets<>());
        TestMyHashMap.functionalityTest(new MyHashMapTSBuckets<>(), new MyHashMapTSBuckets<>());
        TestMyHashMap.functionalityTest(new MyHashMapHSBuckets<>(), new MyHashMapHSBuckets<>());
        TestMyHashMap.functionalityTest(new MyHashMapPQBuckets<>(), new MyHashMapPQBuckets<>());
    }
}
