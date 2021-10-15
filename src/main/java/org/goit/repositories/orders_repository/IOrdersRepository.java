package org.goit.repositories.orders_repository;

import java.util.List;
import org.goit.warehouse.*;

public interface IOrdersRepository {

  List<Orders> getAll();
  void put(String key, Orders orders);
  void clearOrderList();
}