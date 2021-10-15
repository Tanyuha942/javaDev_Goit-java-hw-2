package org.goit.grocer_store;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.stream.Collectors;
import org.goit.repositories.goods_repository.InMemoryGoodsRepository;
import org.goit.repositories.orders_repository.InMemoryOrdersRepository;
import org.goit.warehouse.Goods;
import org.goit.warehouse.Goods.GoodsBuilder;
import org.junit.*;

public class GrocerStoreTest {

  static GrocerStore grocerStore = new GrocerStore(new InMemoryGoodsRepository(), new InMemoryOrdersRepository());
  Goods.GoodsBuilder builder = new GoodsBuilder(new InMemoryGoodsRepository());

  @Before
  public void init() {

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

    grocerStore.put(
        builder.setGoodsId(123)
            .setNameGood("K")
            .setPrice(2.29)
            .build());

  }

  @Test
  public void getGoodsActionCountTest() {

    List<Goods> goodsPriceLowerThen = GrocerStore.getGoodsActionCount();
    Assert.assertTrue("GetGoodsActionCount is null ", goodsPriceLowerThen.size() != 2);
  }

  public void generateEmptyGoods() {

    grocerStore.put(
        builder.build());

    grocerStore.put(
        builder.setGoodsId(123)
            .setNameGood("K")
            .setPrice(2.29)
            .build());
  }

  @Test
  public void initEmptyGoodsTest() {
    generateEmptyGoods();
    Assert.assertNotNull(grocerStore.getAll().stream()
        .filter(f -> f.getGoodsId() == null).collect(Collectors.toList()));
  }

  @Test
  public void searchItemWithIdTest()
  {
    long id = grocerStore.getAll().stream()
        .map(Goods::getGoodsId)
        .filter(Id -> Id.equals(123)).count();
    assertEquals("There is no item with the pointer 123", 1, id);
  }

  @Test
  public void calculatePriceOrderTest() {
    Assert.assertTrue("The order amount is less than zero ",
        GrocerStore.calculatePriceOrder(0, 3, 5.00, 0.00) > 0.00);

    assertEquals("The order amount is zero ", 0.00,
        GrocerStore.calculatePriceOrder(0, 0, 0.00, 0.00), 0.0);

  }

  @Test
  public void calculateTotalCostWithContainsGoodsTest() {

    String basket = "ABCDABA";
    Assert.assertEquals("Method calculateTotalCost does not correctly calculate the amount of the order ",
        13.25, GrocerStore.calculateTotalCost(basket), 0.0);

    String basketOrderGoodsActionA = "AAA";
    Assert.assertEquals("Method calculateTotalCost does not correctly calculate the amount of the order ",
        3.00, GrocerStore.calculateTotalCost(basketOrderGoodsActionA), 0.0);

    String basketOrderGoodsActionC = "CCCCCC";
    Assert.assertEquals("Method calculateTotalCost does not correctly calculate the amount of the order ",
        5.00, GrocerStore.calculateTotalCost(basketOrderGoodsActionC), 0.0);
  }

}