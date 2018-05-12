package controller.employees;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.employee.Employee;

import java.util.Comparator;

public class EmployeesManager {

    private ObservableList<Employee> listEmployees;

    private String currentEmployeeID;

    public EmployeesManager()
    {
        listEmployees = FXCollections.observableArrayList();
        currentEmployeeID = "EE0000";
    }

    public ObservableList<Employee> getListEmployees() {
        return listEmployees;
    }

    public void setListEmployees(ObservableList<Employee> listEmployees) {
        this.listEmployees = listEmployees;
    }

    public String getNextEmployeeID()
    {
        int currentNumber = Integer.valueOf(currentEmployeeID.substring(2));

        String nextNumber = String.valueOf(currentNumber + 1);

        if(nextNumber.length() < 4)
        {
            int currentLength = nextNumber.length();
            for(int i = 0; i < 4 - currentLength; i++)
            {
                nextNumber = "0".concat(nextNumber);
            }
        }

        String nextEmployeeID = "EE".concat(nextNumber);
        return nextEmployeeID;
    }

    public void addEmployee(Employee _employee)
    {
        getListEmployees().add(_employee);
        updateCurrentEmployeeID(_employee.getEmployeeID());
    }

    private void updateCurrentEmployeeID(String newEmployeeID)
    {
        int currentNumber = Integer.valueOf(currentEmployeeID.substring(2));
        int next = Integer.valueOf(newEmployeeID.substring(2));

        if(currentNumber < next)
        {
            currentEmployeeID = newEmployeeID;
        }
    }

    public void addUpdateEmployee(Employee _employee)
    {
        listEmployees.removeIf(p -> p.getEmployeeID().equals(_employee.getEmployeeID()));
        getListEmployees().add(_employee);

        listEmployees.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                int id1 = Integer.valueOf(o1.getEmployeeID().substring(2));
                int id2 = Integer.valueOf(o2.getEmployeeID().substring(2));

                if(id1 == id2) return 0;
                else if(id1 < id2) return -1;
                else return 1;
            }
        });
    }

    public void removeEmployee(Employee p)
    {
        getListEmployees().remove(p);
    }
}

