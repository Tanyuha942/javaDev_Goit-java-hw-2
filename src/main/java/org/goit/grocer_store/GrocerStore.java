package org.goit.grocer_store;

import com.google.common.collect.*;
import com.google.common.collect.Multiset.Entry;
import java.util.*;
import java.util.stream.Collectors;
import org.goit.repositories.goods_repository.*;
import org.goit.repositories.orders_repository.*;
import org.goit.warehouse.*;
import org.goit.warehouse.Goods.GoodsBuilder;

public class GrocerStore {

  private final IGoodsRepository goodsRepository;
  private final IOrdersRepository ordersRepository;
  static GrocerStore grocerStore = new GrocerStore(new InMemoryGoodsRepository(), new InMemoryOrdersRepository());
  static Goods.GoodsBuilder builder = new GoodsBuilder(new InMemoryGoodsRepository());

  public GrocerStore(IGoodsRepository goodsRepository, IOrdersRepository ordersRepository) {
    this.goodsRepository = goodsRepository;
    this.ordersRepository = ordersRepository;
  }

  public static List<Goods> generateGoods() {
    grocerStore.put(
        builder
            .setNameGood("A")
            .setPrice(1.25)
            .setActionCount(3)
            .setActionPrice(3.00)
            .build());

    grocerStore.put(
        builder
            .setNameGood("B")
            .setPrice(4.25)
            .build());

    grocerStore.put(
        builder
            .setNameGood("C")
            .setPrice(1.00)
            .setActionCount(6)
            .setActionPrice(5.00)
            .build());

    grocerStore.put(
        builder
            .setNameGood("D")
            .setPrice(0.75).build());

    return grocerStore.goodsRepository.getAll();
  }

  public void put(Goods goods) {
    put(goods, true);
  }

  public void put(Goods goods, boolean override) {
    if (override) {
      this.goodsRepository.put(goods);
    }
  }

  public List<Goods> getAll() {
    return new ArrayList<>(this.goodsRepository.getAll());
  }

  public static List<Goods> getGoodsActionCount() {
    return grocerStore.getAll()
        .stream()
        .filter(g -> g.getActionCount() != 0)
        .collect(Collectors.toList());
  }

  public static List<Orders> calculateOrderedItems(String basket) {

    grocerStore.ordersRepository.clearOrderList();
    Multiset<String> countSet = HashMultiset.create();
    List<Goods> goods = generateGoods();
    List<String> items = Arrays.asList(basket.split("\\s*"));
    countSet.addAll(items);

    for (Entry<String> entry : countSet.entrySet()) {
      for (Goods g : goods) {
        if (g.getNameGood().equals(entry.getElement())) {
          grocerStore.ordersRepository.put(entry.getElement(), new Orders(g, entry.getElement(), entry.getCount()));
        }
      }
    }
    return grocerStore.ordersRepository.getAll();
  }

  public static Double calculatePriceOrder(
      Integer actionCount, Integer totalCount, Double price, Double actionPrice) {

    if (actionCount > 0 && actionPrice > 0 && totalCount >= actionCount) {

      int orderGoodsWithAction = totalCount/actionCount;
      int orderGoodsWithoutAction = totalCount%actionCount;
      return actionPrice*orderGoodsWithAction + orderGoodsWithoutAction*price;

    } else {
      return price*totalCount;
    }
  }

  public static Double calculateTotalCost(String basket) {

    List<Orders> ordersList = calculateOrderedItems(basket);
    double totalPrice = 0.0;

    for (Orders o: ordersList) {

      totalPrice += calculatePriceOrder(
          o.getGoods().getActionCount(),
          o.getTotalCount(),
          o.getGoods().getPrice(),
          o.getGoods().getActionPrice()
      );
    }
    return totalPrice;
  }
}