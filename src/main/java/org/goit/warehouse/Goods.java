package org.goit.warehouse;

import java.text.DecimalFormat;
import org.goit.repositories.goods_repository.InMemoryGoodsRepository;

public class Goods {

  DecimalFormat formatter = new DecimalFormat("#0.00");

  private final Integer goodsId;
  private final String nameGood;
  private final Double price;
  private final Integer actionCount;
  private final Double actionPrice;

  public Goods(Integer goodsId, String nameGood, Double price, Integer actionCount, Double actionPrice) {
    this.goodsId = goodsId;
    this.nameGood = nameGood;
    this.price = price;
    this.actionCount = actionCount;
    this.actionPrice = actionPrice;
  }

  public Integer getGoodsId() {
    return goodsId;
  }

  public String getNameGood() {
    return nameGood;
  }

  public Double getPrice() {
    return price;
  }

  public Integer getActionCount() {
    return actionCount;
  }

  public Double getActionPrice() {
    return actionPrice;
  }

  @Override
  public String toString() {
    return "Goods{" +
        "goodsId=" + goodsId +
        ", nameGood=" + nameGood +
        ", price=" + formatter.format(price) +
        ", actionCount=" + actionCount +
        ", actionPrice=" + formatter.format(actionPrice) +
        '}';
  }

  public static class GoodsBuilder {

    private static Integer sequenceStartId = 1;
    private static Integer sequenceStartDefaultName = 1;

    private Integer goodsId;
    private String nameGood;
    private Double price;
    private Integer actionCount;
    private Double actionPrice;

    public GoodsBuilder(InMemoryGoodsRepository goodsRepository) {

      this.goodsId = sequenceStartId++;
      this.nameGood = "G"+sequenceStartDefaultName;
      this.price = 0.0;
      this.actionCount = 0;
      this.actionPrice = 0.0;
    }

    public GoodsBuilder setGoodsId(Integer goodsId) {
      this.goodsId = goodsId;
      return this;
    }

    public GoodsBuilder setNameGood(String nameGood) {
      this.nameGood = nameGood;
      return this;
    }

    public GoodsBuilder setPrice(Double price) {
      this.price = price;
      return this;
    }

    public GoodsBuilder setActionCount(Integer actionCount) {
      this.actionCount = actionCount;
      return this;
    }

    public GoodsBuilder setActionPrice(Double actionPrice) {
      this.actionPrice = actionPrice;
      return this;
    }

    public Goods build() {
      Goods goods = new Goods(this.goodsId == null ? sequenceStartId++ : this.goodsId,
          this.nameGood == null ? "G"+sequenceStartDefaultName++ : this.nameGood,
          this.price == null ? 0.0 : this.price,
          this.actionCount == null ? 0 : this.actionCount,
          this.actionPrice == null ? 0.0 : this.actionPrice);
      this.goodsId = null;
      this.nameGood = null;
      this.price = null;
      this.actionCount = null;
      this.actionPrice = null;
      return goods;
    }
  }
}