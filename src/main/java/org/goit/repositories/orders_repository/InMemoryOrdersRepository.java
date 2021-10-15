package org.goit.repositories.orders_repository;

import java.util.*;
import org.goit.warehouse.*;

public class InMemoryOrdersRepository implements IOrdersRepository {

  private final Map<String, Orders> ordersMap = new HashMap<>();

  @Override
  public List<Orders> getAll() {
    return new ArrayList<>(ordersMap.values());
  }

  @Override
  public void put(String key, Orders orders) {
    ordersMap.put(key, orders);
  }

  @Override
  public void clearOrderList() {
    ordersMap.clear();
  }
}
