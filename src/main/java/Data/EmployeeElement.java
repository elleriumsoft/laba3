package Data;

/**
 * Created by Dmitriy on 04.02.2017.
 */
public class EmployeeElement
{
    private int id;
    private String name;
    private String occupation;
    private String date;
    private String dept;
    private int idDept;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getOccupation()
    {
        return occupation;
    }

    public void setOccupation(String occupation)
    {
        this.occupation = occupation;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getDept()
    {
        return dept;
    }

    public void setDept(String dept)
    {
        this.dept = dept;
    }

    public int getIdDept()
    {
        return idDept;
    }

    public void setIdDept(int idDept)
    {
        this.idDept = idDept;
    }

    public String getBeautifulDate()
    {
        if (date.length() < 10)
        {
            return date;
        }
        try
        {
            String bDate;
            if (Integer.valueOf(date.substring(8, 10)) < 10)
            {
                bDate = date.substring(9, 10);
            } else
            {
                bDate = date.substring(8, 10);
            }
            bDate = bDate + ' ' + getMonth(date.substring(5, 7)) + ' ';
            bDate = bDate + date.substring(0, 4) + "г.";
            return bDate;
        } catch (Exception ex)
        {
            return date;
        }
    }

    private String getMonth(String stMonth)
    {
        String[] months = {"ЯНВАРЯ", "ФЕВРАЛЯ", "МАРТА", "АПРЕЛЯ", "МАЯ", "ИЮНЯ", "ИЮЛЯ", "АВГУСТА", "СЕНТЯБРЯ", "ОКТЯБРЯ", "НОЯБРЯ", "ДЕКАБРЯ"};
        return months[Integer.valueOf(stMonth) - 1].toLowerCase();
    }

    public String getBeautifulName()
    {
        StringBuilder bName;
        bName = new StringBuilder();
        bName.append(name.substring(0,1).toUpperCase());
        int i = 1;
        while (i<name.length())
        {
            if (name.charAt(i-1) == ' ')
            {
                bName.append(name.substring(i, i+1).toUpperCase());
            }
            else
            {
                bName.append(name.substring(i, i+1));
            }
            i++;
        }
        return bName.toString();
    }
}
