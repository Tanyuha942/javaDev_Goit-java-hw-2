package org.goit.warehouse;

public class Orders {

  private final Goods goods;
  private final String orderGoodsName;
  private final Integer totalCount;

  public Orders(Goods goods, String orderGoodsName, Integer totalCount) {
    this.goods = goods;
    this.orderGoodsName = orderGoodsName;
    this.totalCount = totalCount;
  }

  public Goods getGoods() {
    return goods;
  }

  public String getOrderGoodsName() {
    return orderGoodsName;
  }

  public Integer getTotalCount() {
    return totalCount;
  }

  @Override
  public String toString() {
    return "Orders{" +
        "goods=" + goods +
        ", orderGoodsName='" + orderGoodsName + '\'' +
        ", totalCount=" + totalCount +
        '}';
  }
}