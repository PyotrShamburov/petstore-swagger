package by.tms.home.storage.store;

import by.tms.home.model.Order;
import by.tms.home.model.enums.OrderStatusEnum;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StoreStorage {
    private static long id = 1;
    private List<Order> orderList = new ArrayList<>();

    public boolean save(Order order) {
        order.setId(id++);
        setShipDate(order);
        return orderList.add(order);
    }
    public void setShipDate(Order order) {
        Instant instant = Instant.now();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        String date = localDateTime.toString();
        order.setShipDate(date);
    }

    public Order getById(long id) {
        for (Order order : orderList) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    public boolean delete(long id) {
       return orderList.removeIf(order -> order.getId() == id);
    }

    public Map<String, Integer> getStatuses() {
        Map<String, Integer> statuses = new HashMap<>();
        int placedCounter = 0;
        int approvedCounter = 0;
        int deliveredCounter = 0;
        for (Order order : orderList) {
            switch (order.getOrderStatus()){
                case PLACED:
                     ++placedCounter;
                    break;
                case APPROVED:
                    ++approvedCounter;
                    break;
                case DELIVERED:
                    ++deliveredCounter;
                    break;
            }
        }
        statuses.put("placed", placedCounter );
        statuses.put("approved", approvedCounter);
        statuses.put("delivered", deliveredCounter);
        return statuses;
    }
}
