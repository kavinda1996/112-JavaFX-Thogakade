package repository.custom.impl;

import db.DBConnection;
import model.Customer;
import repository.custom.CustomerDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean save(Customer entity) {
        String SQL = "INSERT INTO customer VALUES(?,?,?,?)";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            psTm.setObject(1,entity.getId());
            psTm.setObject(2,entity.getName());
            psTm.setObject(3,entity.getAddress());
            psTm.setObject(4,entity.getSalary());
            return psTm.executeUpdate()>0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean update(String s, Customer entity) {
        return false;
    }

    @Override
    public Customer search(String s) {
        return null;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public List<Customer> getAll() {
        return null;
    }
}
