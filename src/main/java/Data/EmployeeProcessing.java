package Data;

import RequestsToDatabase.ConnectionToDb;
import RequestsToDatabase.Employee.GenerateElement;

/**
 * Created by Dmitriy on 19.02.2017.
 */
public class EmployeeProcessing
{
    public Employee loadEmployee(int idElement)
    {
        GenerateElement generateElement = new GenerateElement(idElement);
        new ConnectionToDb().connectToDb(generateElement);
        return generateElement.getEmployee();
    }
}
