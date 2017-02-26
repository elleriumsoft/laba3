package view.finder;

import Data.Employee;

import java.util.Map;

public interface Command
{
    Employee execute(Map<String, String[]> parameters);
}
