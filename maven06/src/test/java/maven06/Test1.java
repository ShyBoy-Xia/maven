package maven06;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.neu.shop.dao.OrderMapper;
import com.neu.shop.pojo.Order;
import com.neu.shop.pojo.OrderExample;

public class Test1 {
	 @Autowired(required = false)
	 private OrderMapper orderMapper;

	@Test
	public void test() {
		
		System.out.println(orderMapper.selectTodayOrders());
	}

}
