package RequestsToDatabase.Finder;

import Data.Employee;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Dmitriy on 20.02.2017.
 */
public abstract class DatabaseRequestForFinder
{
    protected Employee employee;
    private String parameter1;
    private String parameter2;

    abstract public void sendRequest(Connection connection) throws SQLException;

    public void setParameters(String parameter1, String parameter2)
    {
        this.parameter1 = parameter1;
        this.parameter2 = parameter2;
    }

    public String getParameter1()
    {
        return parameter1;
    }

    public String getParameter2()
    {
        return parameter2;
    }

    public Employee getEmployee()
    {
        return employee;
    }

    public void setEmployee(Employee employee)
    {
        this.employee = employee;
    }
}
