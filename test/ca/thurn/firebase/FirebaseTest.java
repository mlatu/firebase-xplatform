package ca.thurn.firebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.Random;

public class FirebaseTest extends GWTTestCase {

  static abstract class TestValueEventListener implements ValueEventListener {
    @Override
    public void onCancelled() {
      fail("Unexpected cancellation");
    }
  }

  static class TestChildEventListener implements ChildEventListener {

    @Override
    public void onCancelled() {
      fail("unexpected cancellation");
    }

    @Override
    public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
      fail("unexpected add");
    }

    @Override
    public void onChildChanged(DataSnapshot snapshot, String previousChildName) {
      fail("unexpected change");
    }

    @Override
    public void onChildMoved(DataSnapshot snapshot, String previousChildName) {
      fail("unexpected move");
    }

    @Override
    public void onChildRemoved(DataSnapshot snapshot) {
      fail("unexpected remove");
    }

  }

  Firebase firebase;

  @Override
  public String getModuleName() {
    return "ca.thurn.Firebase";
  }

  @Override
  protected void gwtSetUp() throws Exception {
    delayTestFinish(10000);
    if (firebase == null) {
      ScriptInjector.fromUrl("https://cdn.firebase.com/v0/firebase.js")
          .setCallback(new Callback<Void, Exception>() {
            @Override
            public void onFailure(Exception reason) {}

            @Override
            public void onSuccess(Void result) {
              firebase = new Firebase("https://www.example.com/foo");
              finishTest();
            }
          }).inject();
    } else {
      finishTest();
    }
  }

  private static void assertDeepEquals(String msg, Object o1, Object o2) {
    if (o1 instanceof Iterable && o2 instanceof Iterable) {
      @SuppressWarnings("unchecked")
      Iterator<Object> ite1 = ((Iterable<Object>) o1).iterator();
      @SuppressWarnings("unchecked")
      Iterator<Object> ite2 = ((Iterable<Object>) o2).iterator();
      while (ite1.hasNext() && ite2.hasNext()) {
        assertDeepEquals(msg, ite1.next(), ite2.next());
      }
      assertFalse("Iterable sizes differ", ite1.hasNext() || ite2.hasNext());
    } else if (o1 instanceof Map && o2 instanceof Map) {
      @SuppressWarnings("unchecked")
      Map<Object, Object> map1 = (Map<Object, Object>) o1;
      @SuppressWarnings("unchecked")
      Map<Object, Object> map2 = (Map<Object, Object>) o2;
      assertEquals("Map sizes differ", map1.size(), map2.size());
      for (Map.Entry<Object, Object> entry : map1.entrySet()) {
        assertTrue(map2.containsKey(entry.getKey()));
        assertDeepEquals(msg, entry.getValue(), map2.get(entry.getKey()));
      }
    } else {
      assertEquals(msg, o1, o2);
    }
  }

  public void testGetName() {
    assertEquals("foo", firebase.getName());
  }

  public void testChild() {
    String name = randomName();
    Firebase child = firebase.child(name);
    assertEquals(name, child.getName());
    assertEquals("foo", child.getParent().getName());
  }

  public void testNullRootName() {
    assertEquals(null, firebase.getParent().getName());
    assertEquals(null, firebase.getRoot().getName());
  }

  public void testUpdateReadOnce() {
    delayTestFinish(5000);
    Firebase child = firebase.child(randomName());
    Firebase alpha = child.child("alpha");
    alpha.addListenerForSingleValueEvent(new TestValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot snapshot) {
        System.out.println("onDataChange");
        assertEquals("fred", snapshot.getValue());
        finishTest();
      }
    });
    Map<String, Object> update = new HashMap<String, Object>();
    update.put("alpha", "fred");
    update.put("beta", 2);
    child.updateChildren(update);
  }

  private void runTestSet(Object object) {
    runTestSetOnce(object, null);
    if (object != null) {
      runTestSetOnce(object, "string");
      runTestSetOnce(object, 123.2);
    } else {
      try {
        runTestSetOnce(object, "123.0");
        fail("expected setting a priority on an null value to cause an exception");
      } catch (IllegalArgumentException expected) {
        finishTest();
      }
    }
  }

  private void runTestSetOnce(final Object object, final Object priority) {
    delayTestFinish(5000);
    final String name = randomName();
    Firebase child = firebase.child(name);
    child.addListenerForSingleValueEvent(new TestValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot snapshot) {
        assertDeepEquals("values do not match", object, snapshot.getValue());
        assertEquals(name, snapshot.getName());
        assertEquals("priorities do not match", priority, snapshot.getPriority());
        assertEquals(name, snapshot.getRef().getName());
        finishTest();
      }
    });
    if (priority == null) {
      child.setValue(object);
    } else {
      child.setValue(object, priority);
    }
  }

  public void testSetString() {
    runTestSet("str");
  }

  public void testSetDouble() {
    runTestSet(41.0000000000001);
  }

  public void testSetInt() {
    runTestSet(42);
  }

  public void testSetBoolean() {
    runTestSet(true);
  }

  public void testSetNull() {
    runTestSet(null);
  }

  public void testSetMapDoubles() {
    Map<String, Double> doubles = new HashMap<String, Double>();
    doubles.put("one", 11.1);
    doubles.put("two", 22.2);
    runTestSet(doubles);
  }

  public void testSetMixedMap() {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("one", true);
    map.put("two", 3);
    map.put("three", "four");
    runTestSet(map);
  }

  public void testNullMapException() {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("one", true);
    map.put("two", null);
    try {
      runTestSet(map);
      fail("Expected exception from null map value");
    } catch (IllegalArgumentException expected) {
      finishTest();
    }
  }

  public void testSetEmptyList() {
    List<Double> doubles = new ArrayList<Double>();
    delayTestFinish(5000);
    Firebase child = firebase.child(randomName());
    child.addListenerForSingleValueEvent(new TestValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot snapshot) {
        assertEquals(null, snapshot.getValue());
        finishTest();
      }
    });
    child.setValue(doubles);
  }

  public void testSetEmptyMap() {
    Map<String, String> map = new HashMap<String, String>();
    delayTestFinish(5000);
    Firebase child = firebase.child(randomName());
    child.addListenerForSingleValueEvent(new TestValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot snapshot) {
        assertEquals(null, snapshot.getValue());
        finishTest();
      }
    });
    child.setValue(map);
  }

  public void testSetList() {
    List<Double> doubles = new ArrayList<Double>();
    doubles.add(12.2);
    doubles.add(24.3);
    runTestSet(doubles);
  }

  public void testSetMixedList() {
    List<Object> list = new ArrayList<Object>();
    list.add("one");
    list.add(21);
    list.add(true);
    runTestSet(list);
  }

  public void testSetNullsListException() {
    List<Object> list = new ArrayList<Object>();
    list.add("one");
    list.add(2.340);
    list.add(null);
    try {
      runTestSet(list);
      fail("Expected null value in a list to cause an exception");
    } catch (IllegalArgumentException expected) {
      finishTest();
    }
  }

  public void testSetNestedLists() {
    List<Object> list = new ArrayList<Object>();
    list.add("one");
    List<Object> sublist = new ArrayList<Object>();
    sublist.add(2.1);
    sublist.add(false);
    list.add(sublist);
    runTestSet(list);
  }

  public void testSetListInsideMap() {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("one", "one");
    List<Object> list = new ArrayList<Object>();
    list.add("one");
    list.add(2.222);
    map.put("list", list);
    runTestSet(map);
  }

  public void testSetNestedMaps() {
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> one = new HashMap<String, Object>();
    one.put("one", 1);
    Map<String, Object> two = new HashMap<String, Object>();
    two.put("two", "two");
    map.put("one", one);
    map.put("two", two);
    runTestSet(map);
  }

  public void testSetMapInsideList() {
    List<Object> list = new ArrayList<Object>();
    list.add("one");
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("two", 2.230);
    map.put("three", true);
    list.add(map);
    runTestSet(list);
  }

  public void testGetGenericTypeIndicator() {
    delayTestFinish(5000);
    Firebase child = firebase.child(randomName());
    final List<Object> list = new ArrayList<Object>();
    list.add("one");
    list.add(2.3);
    list.add(true);
    child.addListenerForSingleValueEvent(new TestValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot snapshot) {
        List<Object> result = snapshot.getValue(new GenericTypeIndicator<List<Object>>() {});
        assertDeepEquals("list value was wrong", list, result);
        finishTest();
      }
    });
    child.setValue(list);
  }

  public void testGetClassLiteral() {
    delayTestFinish(5000);
    Firebase child = firebase.child(randomName());
    child.addListenerForSingleValueEvent(new TestValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot snapshot) {
        Integer value = snapshot.getValue(Integer.class);
        assertEquals(new Integer(123), value);
        finishTest();
      }
    });
    child.setValue(123);
  }

  public void testSetByteException() {
    try {
      runTestSet((byte) 12);
      fail("Expected exception setting byte");
    } catch (IllegalArgumentException expected) {
      finishTest();
    }
  }

  public void testSetLongException() {
    try {
      runTestSet(123L);
      fail("Expected exception setting long");
    } catch (IllegalArgumentException expected) {
      finishTest();
    }
  }

  public void testSetIntegerList() {
    List<Integer> list = new ArrayList<Integer>();
    list.add(123);
    runTestSet(list);
  }

  public void testSetIntegerMap() {
    Map<String, Integer> map = new HashMap<String, Integer>();
    map.put("123", 123);
    runTestSet(map);
  }

  public void testAddChild() {
    delayTestFinish(5000);
    Firebase child = firebase.child(randomName());
    firebase.addChildEventListener(new TestChildEventListener() {
      @Override
      public void onChildAdded(DataSnapshot snapshot, String previousName) {
        assertEquals("va", snapshot.getValue());
        assertNull(previousName);
        finishTest();
      }
    });
    child.setValue("va");
  }

  public void xtestRemoveChild() {
    delayTestFinish(5000);
    final Firebase child = firebase.child(randomName());
    child.setValue("xa");
    firebase.addChildEventListener(new TestChildEventListener() {
      @Override
      public void onChildAdded(DataSnapshot snapshot, String previousName) {
        child.removeValue();
      }

      @Override
      public void onChildRemoved(DataSnapshot snapshot) {
        System.out.println("removed");
        finishTest();
      }
    });
  }

  private String randomName() {
    return Math.abs(Random.nextInt()) + "";
  }

}
