package by.tux.spring160.repos;

import by.tux.spring160.models.ItemModel;
import by.tux.spring160.models.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepo extends JpaRepository<ItemModel, Long> {
    ItemModel findItemModelById(long id);
    List<ItemModel> findAllByType(Type type);
}