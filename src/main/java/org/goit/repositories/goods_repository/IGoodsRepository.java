package org.goit.repositories.goods_repository;

import java.util.List;
import org.goit.warehouse.Goods;

public interface IGoodsRepository {

  Goods get(Integer goodsId);
  List<Goods> getAll();
  void  put(Goods goods);
  void  remove(Integer goodsId);
}