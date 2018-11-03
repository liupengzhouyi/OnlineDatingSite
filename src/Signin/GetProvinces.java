package Signin;

import Tools.GetTime.GetNowTime;
import Tools.LinkDatabase.linkDatabases;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetProvinces {
    public String[] getPrivices() throws ClassNotFoundException, SQLException {

        linkDatabases lpLinkDatabases = new linkDatabases();

        String sql = "select * from privince_table;";

        ResultSet resultSet = lpLinkDatabases.getInformation(sql);

        String string = "";
        while (resultSet.next()) {

            String provices_name = resultSet.getString("provices_name");
            System.out.println(provices_name);
            string = string + provices_name + ",";
        }

        String[] strings = string.split(",");
        return strings;
    }

    public String[] getNumber() throws ClassNotFoundException, SQLException {
        linkDatabases lpLinkDatabases = new linkDatabases();

        String sql = "select * from privince_table;";

        ResultSet resultSet = lpLinkDatabases.getInformation(sql);

        String string = "";
        while (resultSet.next()) {
            String provices_name = resultSet.getString("provices_id");
            System.out.println(provices_name);
            string = string + provices_name + ",";
        }

        String[] strings = string.split(",");
        return strings;
    }

    public String getPrivinceByNumber(String number) throws SQLException, ClassNotFoundException {
        linkDatabases lpLinkDatabases = new linkDatabases();

        String sql = "select * from privince_table where provices_id = " + number + ";";

        ResultSet resultSet = lpLinkDatabases.getInformation(sql);

        String string = "";
        String provices_name = "";
        while (resultSet.next()) {
            provices_name = resultSet.getString("provices_name");
        }
        return provices_name;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new GetProvinces().getPrivices();
        new GetProvinces().getNumber();
    }
}
