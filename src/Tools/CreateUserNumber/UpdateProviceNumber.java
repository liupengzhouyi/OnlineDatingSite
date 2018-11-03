package Tools.CreateUserNumber;

import Tools.GetTime.GetNowTime;
import Tools.LinkDatabase.linkDatabases;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateProviceNumber {
    public UpdateProviceNumber(String provice) throws SQLException, ClassNotFoundException {
        init(provice);


    }

    public String getNumber() throws ClassNotFoundException, SQLException {
        int returnNumber = -1;
        String sql = "select * from privince_number_table where privince_id = \'" + this.getProviceNumber() + "\';";
        linkDatabases linkdatabases = (linkDatabases) new linkDatabases();
        ResultSet resultSet = linkdatabases.getInformation(sql);
        String today = "";
        int number = -100;
        while( resultSet.next()) {
            today = resultSet.getString("today");
            number = resultSet.getInt("number");
        }
        //准备更新数据
        if (this.getDate().equals(today)) {
            returnNumber = number;
            number = number + 1;
        } else {
            today = new GetNowTime().getDate();
            number = 1;
            returnNumber = 0;
        }
        String sqlI = "update privince_number_table set today = \'" + today + "\', number = " + number + " where privince_id = " + this.getProviceNumber() + ";";

        linkdatabases.updateData(sqlI);

        return returnNumber +"";
    }

    public void init(String provice) throws SQLException, ClassNotFoundException {
        GetProviceNumber getProviceNumber = new GetProviceNumber();
        String numberStr = getProviceNumber.getProviceNumber(provice);
        this.setProviceNumber(Integer.parseInt(numberStr));
        this.date = new String();
        this.setDate();
    }

    public int getProviceNumber() {
        return proviceNumber;
    }

    public void setProviceNumber(int proviceNumber) {
        this.proviceNumber = proviceNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate() {
        this.date = new GetNowTime().getDate();
    }

    private int proviceNumber = -1;

    private String date = null;
}
