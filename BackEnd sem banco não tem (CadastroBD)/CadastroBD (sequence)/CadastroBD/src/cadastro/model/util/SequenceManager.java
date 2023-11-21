package cadastro.model.util;


// importações
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SequenceManager {
    public static int getValue(String sequenceName) {
        int value = -1;
        try {
            String query = "SELECT NEXT VALUE FOR " + sequenceName;
            PreparedStatement preparedStatement = ConectorBD.getPrepared(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                value = resultSet.getInt(1);
            }
            
            resultSet.close();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
}
