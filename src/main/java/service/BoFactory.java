package service;

import service.custom.impl.CustomerBoImpl;
import service.custom.impl.ItemBoImpl;
import util.BoType;

public class BoFactory {
    private static BoFactory instance;
    private BoFactory(){}
    public static BoFactory getInstance() {
        return instance==null?instance=new BoFactory():instance;
    }
    public <T extends SuperService> T getBoType(BoType type){

        switch (type){
            case CUSTOMER: return (T) new CustomerBoImpl();
            case ITEM:return (T) new ItemBoImpl();
        }
        return null;

    }
}
