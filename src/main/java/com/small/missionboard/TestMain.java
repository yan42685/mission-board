package com.small.missionboard;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class TestMain {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();

        //定义一个String类型实体str保存你要的时间，格式如下(以2018年5月6号10点30分40秒为例)
        String str = "2018-05-06 10:30:40";
        //用Timestamp的valueOf方法转化为Timestamp实体

        Timestamp time = Timestamp.valueOf(str);


        //就如下一句代码，其中time为Timestamp类型的实体(就默认是上一个例子的time吧)，这就得到对应的String类型
        String timeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
        System.out.println(time);
        System.out.println(timeStr);
    }
}
