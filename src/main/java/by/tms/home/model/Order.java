package by.tms.home.model;

import by.tms.home.model.enums.OrderStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private long id;

    @Positive(message = "Can be only positive number!")
    private long petId;

    @Positive(message = "Can be only positive number!")
    private int quantity;
    private String shipDate;
    private OrderStatusEnum orderStatus;
    private boolean complete;
}
