package main.java.controller;

import main.java.database.entities.Employer;
import main.java.database.helpers.EmployerDBHelper;

import java.sql.SQLException;
import java.util.List;

public class EmployerController {

    public EmployerController() {
    }

    public List<Employer> getAllEmployees() {
        try {
//            List<Employer> list = EmployerDBHelper.findAll();
//            if (!list.isEmpty()) {
//
//                for (Employer e : list) {
//                    System.out.println(e.getName());
//                }
//            } else {
//                System.out.println("List is empty");
//
//            }
            return EmployerDBHelper.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Employer createEmployer(String firstName, String lastName, String street, String zip, String city, String country) {
        Employer employer = new Employer(firstName, lastName, street, zip, city, country);

        try {
            EmployerDBHelper.insertEntity(employer);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employer;
    }

    // Test functionalities
    public static void main(String[] args) {

        try {
            System.out.println(EmployerDBHelper.getNextId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
