package by.tms.home.resource;

import by.tms.home.model.Order;
import by.tms.home.service.store.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(path = "/store")
public class StoreResource {

    @Autowired
    private StoreService storeService;

    @PostMapping(path = "/order")
    public ResponseEntity<Order> placePetOrder(@Valid @RequestBody Order order) {
        if (storeService.addOrder(order)) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/order/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable long orderId) {
        if (orderId >= 1 && orderId <= 10) {
            Order byId = (Order) storeService.findById(orderId);
            if (byId != null) {
                return new ResponseEntity<>(byId, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(path = "/order/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable long orderId) {
        if ( orderId > 0 ) {
            if (storeService.deleteById(orderId)) {
                return new ResponseEntity<>("Order with ID:"+orderId+" - DELETED!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Order not found!", HttpStatus.NOT_FOUND);
            }

        }
        return new ResponseEntity<>("Invalid ID supplied",HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/inventory")
    public ResponseEntity<Map<String, Integer>> getStatusesAmount() {
        Map<String, Integer> inventoriesByStatus = (Map<String, Integer>) storeService.getInventoriesByStatus();
        return new ResponseEntity<>(inventoriesByStatus, HttpStatus.OK);
    }

}
