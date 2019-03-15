package com.example.shopmall.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataOpenHelper extends SQLiteOpenHelper {

    //数据库版本号
    private static final Integer DB_VERSION = 1;

    //数据库名
    private static final String DB_NAME = "shopmall.db";

    public DataOpenHelper(Context context,String name,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("创建数据库和表");

//        String sql_user = "CREATE TABLE user (id int(11)   NOT NULL , name varchar(255) DEFAULT NULL,password varchar(255) DEFAULT NULL,PRIMARY KEY(id))";
//        String sql_user = "Create table user (Integer int (11) primary key autoincrement,)"

        //用户表
        String sql_user = "CREATE TABLE user (id int(11) PRIMARY KEY autoincrement Not null, " +
                "name varchar(255) DEFAULT NULL," +
                "password varchar(255) DEFAULT NULL)";

        //分类表
        String sql_category = "CREATE TABLE category(id int(11) PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " name varchar(255) DEFAULT NULL)";

        //属性表
        String sql_property = "CREATE TABLE property ( id int(11) PRIMARY KEY AUTOINCREMENT NOT NULL , " +
                "cid int(11) DEFAULT NULL, " +
                "name varchar(255) DEFAULT NULL," +
                "CONSTRAINT fk_property_category FOREIGN KEY (cid) REFERENCES category (id))";

        //产品表
        String sql_prodct ="CREATE TABLE product (\n" +
                "  id int(11) NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "  name varchar(255) DEFAULT NULL,\n" +
                "  subTitle varchar(255) DEFAULT NULL,\n" +                                     //小标题
                "  orignalPrice float DEFAULT NULL,\n" +                                        //原始价格
                "  promotePrice float DEFAULT NULL,\n" +                                        //优惠价格
                "  stock int(11) DEFAULT NULL,\n" +                                             //库存
                "  cid int(11) DEFAULT NULL,\n" +
                "  createDate datetime DEFAULT NULL,\n" +                                       //创建日期
                "  CONSTRAINT fk_product_category FOREIGN KEY (cid) REFERENCES category (id)\n" +
                ") ";

        //属性值表
        String sql_proertyvalue ="CREATE TABLE propertyvalue (\n" +
                "  id int(11) NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "  pid int(11) DEFAULT NULL,\n" +
                "  ptid int(11) DEFAULT NULL,\n" +
                "  value varchar(255) DEFAULT NULL,\n" +
                "  CONSTRAINT fk_propertyvalue_property FOREIGN KEY (ptid) REFERENCES property (id),\n" +
                "  CONSTRAINT fk_propertyvalue_product FOREIGN KEY (pid) REFERENCES product (id)\n" +
                ") ";

        //产品图片表
        String sql_productimage ="CREATE TABLE productimage (\n" +
                "  id int(11) NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "  pid int(11) DEFAULT NULL,\n" +
                "  type varchar(255) DEFAULT NULL,\n" +
                "  CONSTRAINT fk_productimage_product FOREIGN KEY (pid) REFERENCES product (id)\n" +
                ")";

        //评价表
        String sql_review ="CREATE TABLE review (\n" +
                "  id int(11) NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "  content varchar(4000) DEFAULT NULL,\n" +
                "  uid int(11) DEFAULT NULL,\n" +
                "  pid int(11) DEFAULT NULL,\n" +
                "  createDate datetime DEFAULT NULL,\n" +
                "  CONSTRAINT fk_review_product FOREIGN KEY (pid) REFERENCES product (id),\n" +
                "  CONSTRAINT fk_review_user FOREIGN KEY (uid) REFERENCES user (id)\n" +
                ")";

        //订单表
        String sql_order = "CREATE TABLE order_ (\n" +
                "  id int(11) NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "  orderCode varchar(255) DEFAULT NULL,\n" +
                "  address varchar(255) DEFAULT NULL,\n" +
                "  post varchar(255) DEFAULT NULL,\n" +
                "  receiver varchar(255) DEFAULT NULL,\n" +
                "  mobile varchar(255) DEFAULT NULL,\n" +
                "  userMessage varchar(255) DEFAULT NULL,\n" +
                "  createDate datetime DEFAULT NULL,\n" +
                "  payDate datetime DEFAULT NULL,\n" +
                "  deliveryDate datetime DEFAULT NULL,\n" +
                "  confirmDate datetime DEFAULT NULL,\n" +
                "  uid int(11) DEFAULT NULL,\n" +
                "  status varchar(255) DEFAULT NULL,\n" +
                "  CONSTRAINT fk_order_user FOREIGN KEY (uid) REFERENCES user (id)\n" +
                ")";

        //订单项表
        String sql_orderitem = "CREATE TABLE orderitem (\n" +
                "  id int(11) NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "  pid int(11) DEFAULT NULL,\n" +
                "  oid int(11) DEFAULT NULL,\n" +
                "  uid int(11) DEFAULT NULL,\n" +
                "  number int(11) DEFAULT NULL,\n" +
                "  CONSTRAINT fk_orderitem_user FOREIGN KEY (uid) REFERENCES user (id),\n" +
                "  CONSTRAINT fk_orderitem_product FOREIGN KEY (pid) REFERENCES product (id)\n" +
                ")";



        db.execSQL(sql_user);
        db.execSQL(sql_category);
        db.execSQL(sql_property);
        db.execSQL(sql_prodct);
        db.execSQL(sql_proertyvalue);
        db.execSQL(sql_productimage);
        db.execSQL(sql_review);
        db.execSQL(sql_order);
        db.execSQL(sql_orderitem);






    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
