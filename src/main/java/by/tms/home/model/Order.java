package by.tms.home.model;

import by.tms.home.model.enums.OrderStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private long id;
    private long petId;
    private int quantity;
    private String shipDate;
    private OrderStatusEnum orderStatus;
    private boolean complete;
}
