package Tools.CreateUserNumber;

import Tools.LinkDatabase.linkDatabases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GetProviceNumber {
    private Map <String, Object> proviceMap = null;

    public GetProviceNumber() throws SQLException, ClassNotFoundException {
        init();
    }

    public void init() throws ClassNotFoundException, SQLException {
        proviceMap = new HashMap<String, Object>();

        linkDatabases linkDatabases = new linkDatabases();
        ResultSet resultSet = linkDatabases.getInformation("select * from privince_table;");
        while(resultSet.next()) {
            int i = resultSet.getInt("provices_id");
            String provices = resultSet.getString("provices_name");
            this.proviceMap.put(provices, i);
        }
    }

    //测试
    public void testToPrint() {
        Set <String> keySet = proviceMap.keySet();

        for (String key : keySet
             ) {
            System.out.print(key);
            Object i = proviceMap.get(key);
            System.out.println(i.toString());
        }

    }

    public String getProviceNumber(String provice) {
        Object number = this.proviceMap.get(provice);
        if (number == null) {
            return "-1";
        } else {
            String numberString = number.toString();
            if (numberString.length() == 1) {
                numberString = "0" + numberString;
            }
            return numberString;
        }
    }

    public String getID(String ID, String provice) {
        String number = this.getProviceNumber(provice);
        return ID + number;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        GetProviceNumber getProviceNumber = new GetProviceNumber();
        //getProviceNumber.testToPrint();
        System.out.println("------------------------");
        System.out.print(getProviceNumber.getProviceNumber("台湾省"));
    }
}

/*
 private String[] provices = {
            "北京市",
            "天津市",
            "上海市",
            "重庆市",
            "河北省",
            "山西省",
            "辽宁省",
            "吉林省",
            "黑龙江省",
            "江苏省",
            "浙江省",
            "安徽省",
            "福建省",
            "江西省",
            "山东省",
            "河南省",
            "湖北省",
            "湖南省",
            "广东省",
            "海南省",
            "四川省",
            "贵州省",
            "云南省",
            "陕西省",
            "甘肃省",
            "青海省",
            "台湾省",
            "内蒙古自治区",
            "广西壮族自治区",
            "西藏自治区",
            "宁夏回族自治区",
            "新疆维吾尔自治区",
            "香港特别行政区",
            "澳门特别行政区"
    };
 */