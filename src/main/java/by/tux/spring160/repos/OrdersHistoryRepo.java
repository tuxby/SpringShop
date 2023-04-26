package by.tux.spring160.repos;

import by.tux.spring160.models.OrdersHistoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersHistoryRepo extends JpaRepository<OrdersHistoryModel, Long> {
}
