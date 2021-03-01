package by.tms.home.service.store;

import by.tms.home.model.Order;
import by.tms.home.storage.store.StoreStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StoreService {

    @Autowired
    private StoreStorage storeStorage;

    public boolean addOrder(Order order) {
        return storeStorage.save(order);
    }

    public Order findById(long id) {
        return storeStorage.getById(id);
    }

    public boolean deleteById(long id) {
        return storeStorage.delete(id);
    }

    public Map<String, Integer> getInventoriesByStatus() {
        return storeStorage.getStatuses();
    }
}
