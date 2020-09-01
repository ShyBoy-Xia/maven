package com.neu.shop.service;

import com.neu.shop.pojo.*;

import java.util.List;

public interface OrderService {
    public void insertOrder(Order order);

    public void deleteById(Integer orderid);


    public List<Order> selectOrderByExample(OrderExample orderExample);

    public List<OrderItem> getOrderItemByExample(OrderItemExample orderItemExample);

    public Address getAddressByKey(Integer addressid);

    public void updateOrderByKey(Order order);

    public Order selectByPrimaryKey(Integer orderid);

    void insertOrderItem(OrderItem orderItem);
    
    //查询本日订单
    public List<Order> selectToday();
    
    //查询本周订单
    public List<Order> selectWeek();
    
    //查询本月订单
    public List<Order> selectMonth();
    
    //热卖商品数量
    public List<GoodsAndSum> selectGoodsAndSum(List<Integer> orderIds);
    
    //本日热卖商品
    public List<Goods> selectTodayHotGoods();
    
    //本周热卖商品
    public List<Goods> selectWeekHotGoods();
    
    //本月热卖商品
    public List<Goods> selectMonthHotGoods();
    
    //统计本周销售额
    public List<DateAndSales> selectSale(); 
    
    //统计本周商品销售量
    public List<Goods> selectGoodsAndSalesInWeek();
    
}
