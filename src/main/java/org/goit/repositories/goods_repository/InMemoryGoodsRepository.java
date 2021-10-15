package org.goit.repositories.goods_repository;

import java.util.*;
import org.goit.warehouse.Goods;

public class InMemoryGoodsRepository implements IGoodsRepository {

  private final Map<Integer, Goods> goodsMap = new HashMap<>();

  @Override
  public Goods get(Integer goodsId) {
    return goodsMap.get(goodsId);
  }

  @Override
  public List<Goods> getAll() {
    return new ArrayList<>(goodsMap.values());
  }

  @Override
  public void put(Goods goods) {
    goodsMap.put(goods.getGoodsId(), goods);
  }

  @Override
  public void remove(Integer goodsId) {
    goodsMap.remove(goodsId);
  }
}