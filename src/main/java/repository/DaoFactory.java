package repository;

import model.Customer;
import repository.custom.impl.CustomerDaoImpl;
import repository.custom.impl.ItemDaoImpl;
import util.DaoType;

import java.util.ArrayList;
import java.util.List;

public class DaoFactory {
    private static DaoFactory instance;
    private DaoFactory(){}
    public static DaoFactory getInstance() {
        return instance==null?instance=new DaoFactory():instance;
    }

    public <T extends SuperDao> T getDaoType(DaoType type){
                switch (type){
                    case CUSTOMER: return (T) new CustomerDaoImpl();
                    case ITEM:return (T) new ItemDaoImpl();
                }
    return null;
    }

}
