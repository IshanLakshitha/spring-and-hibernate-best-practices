package com.ijse.pro.util;

import com.ijse.pro.db.DBConnection;
import com.ijse.pro.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManageCustomer {

    public static ArrayList<Customer> getCustomer(){

        ArrayList<Customer> AllCustomer = new ArrayList<>();
        try{
            Connection connection = DBConnection.getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM customer");
            ResultSet resultSet = pstm.executeQuery();
            while (resultSet.next()){
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                Customer customer = new Customer(id, name);
                AllCustomer.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return AllCustomer;
    }

    public static void createCustomer(Customer customer){
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO customer VALUES (?,?)");
            pstm.setObject(1,customer.getId());
            pstm.setObject(2,customer.getName());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
