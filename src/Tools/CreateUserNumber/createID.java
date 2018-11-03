package Tools.CreateUserNumber;


import Tools.GetTime.GetNowTime;

import java.sql.SQLException;

public class createID {

    public createID(String provice, String sex) throws SQLException, ClassNotFoundException {


        String number = new UpdateProviceNumber(provice).getNumber();

        String ID = null;
        //省份： 内蒙古自治区
        //第几个： 56
        //获取日期
        String date = new GetNowTime().getUsedStringForID();
        ID = date;

        //获取省份
        ID = new GetProviceNumber().getID(ID, provice);

        //获取number
        AddNumber addNumber = new AddNumber(ID, number);

        addNumber.init(number);

        ID = addNumber.getID();

        //性别
        ID = new AddSexNumber(ID, sex).getIDNumber();

        //校验
        ID = new VerifyCode(ID).getID();

        this.setID(ID);
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    private String ID = null;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.print(new createID("内蒙古自治区", "女").getID());
    }
}
