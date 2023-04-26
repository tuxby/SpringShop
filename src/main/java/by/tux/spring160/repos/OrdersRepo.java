package by.tux.spring160.repos;

import by.tux.spring160.models.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepo extends JpaRepository<OrderModel, Long> {
    OrderModel findOrderRequestModelById(long id);
}
