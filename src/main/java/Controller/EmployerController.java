package main.java.Controller;

import main.java.Database.Entities.Employer;
import main.java.Database.Helpers.EmployerDBHelper;

import java.sql.SQLException;
import java.util.List;

public class EmployerController {

    public EmployerController() {
    }

    public List<Employer> getAllEmployees() {
        try {
            return EmployerDBHelper.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Employer createEmployer(String firstName, String lastName, String street, String zip, String city, String country){
        Employer employer = new Employer(firstName, lastName, street, zip, city, country);

        try {
            EmployerDBHelper.insertEntity(employer);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employer;
    }
}
