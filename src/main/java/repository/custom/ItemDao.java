package repository.custom;

import model.Item;
import repository.CrudRepository;

public interface ItemDao extends CrudRepository<Item,String> {
}
