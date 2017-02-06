package Data;

/**
 * Created by Dmitriy on 07.02.2017.
 */
public class OccupationElement
{
    private int id;
    private String name;

    public OccupationElement(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

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
}
