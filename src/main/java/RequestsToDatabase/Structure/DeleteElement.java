package RequestsToDatabase.Structure;

import Data.Structure;
import Data.StructureElement;
import RequestsToDatabase.DatabaseRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Dmitriy on 03.02.2017.
 */
public class DeleteElement implements DatabaseRequest
{
    private int element;
    private Structure structure;

    public DeleteElement(Structure structure, String element)
    {
        this.structure = structure;
        this.element = Integer.valueOf(element);
    }

    @Override
    public void sendRequest(Connection connection) throws SQLException
    {
        //connection.createStatement().executeUpdate("DELETE FROM structure WHERE id=" + element + ";\n");
        deleteElement(connection, element);

    }

    private void deleteElement(Connection connection, int id) throws SQLException
    {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM structure WHERE id=?");
        statement.setInt(1, id);
        statement.executeUpdate();
        statement.close();

        for (StructureElement structureElement : structure.getStructure())
        {
            if (structureElement.getParent_id() == id)
            {
                deleteElement(connection, structureElement.getId());
            }
        }
    }

}
