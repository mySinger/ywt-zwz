package com.gkzxhn.ywt_gkzx.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.gkzxhn.ywt_gkzx.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZengWenZhi on 2016/9/9 0009.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    /**
     * 数据库版本，需要升级数据库时只要加一即可
     */
    private static final int DATABASE_VERSION = 1;
    /**
     * 数据库名
     */
    private static final String DATABASE_NAME = "mySQLite.db";
    private List<Goods> list;

    /**
     * 构造方法
     * 每次创建DatabaseHelper对象时，若本应用无该数据库，则新建数据库并调用onCreate方法；
     * 若该数据库已创建则直接使用已存在的数据库且跳过onCreate方法
     * factory : 当打开的数据库执行查询语句的时候 会创建一个Cursor对象, 这时会调用Cursor工厂类 factory, 可以填写null默认值
     *
     * @param context 上下文
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * 创建数据库是时调用（只被调用一次）
     *
     * @param db 数据库
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建goods表，属性：id（用户id，主键）、name（商品名称）、price（商品价格）、image（商品图片）、introduce(商品简介)、num（商品购买数量）。
        db.execSQL("CREATE TABLE goods (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(10), price VARCHAR, image INTEGER, introduce VARCHAR, num INTEGER)");
    }

    /**
     * 跟新数据库时调用
     *
     * @param db         数据库
     * @param oldVersion 旧版本号
     * @param newVersion 新版本号
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //升级goods表，添加性别
        db.execSQL("ALTER TABLE user ADD COLUMN gender VARCHAR(2)");
    }

    /**
     * 插入一条数据
     *
     * @param goods 商品对象
     */
    public void insertAGoods(Goods goods) {
        //如果要对数据进行更改，就调用此方法得到用于操作数据库的实例,该方法以读和写方式打开数据库
        SQLiteDatabase database = getWritableDatabase();
        //向goods表插入一条数据
        database.execSQL(
                "INSERT INTO goods(name, price, image, introduce, num) VALUES(?,?,?,?,?)",
                new Object[]{goods.getName(), goods.getPrice(),  goods.getImage(), goods.getIntroduce(), goods.getNum()});
    }

    /**
     * 更新一条商品信息
     *
     * @param goods 商品对象
     */
    public void updateAGoods(Goods goods) {
        SQLiteDatabase database = getWritableDatabase();
        //根据id更新一条数据
        database.execSQL(
                "UPDATE goods SET num=? WHERE id=?",
                new Object[]{goods.getNum(), goods.getId()});
    }

    /**
     * 清空数据库中的商品的数量信息
     */
    public void clearNum(List<Goods> list){
        SQLiteDatabase database = getWritableDatabase();
        for(int i=0; i < list.size(); i++){
            database.execSQL(
                    "UPDATE goods SET num=? WHERE id=?",
                    new Object[]{0, list.get(i).getId()});
            Log.e("clearNum","========================");
        }
    }

    /**
     * 根据id删除一条数据
     *
     * @param id 商品id
     */
    public void deleteAUser(Integer id) {
        SQLiteDatabase database = getWritableDatabase();
        //根据id删除一条数据
        database.execSQL("DELETE FROM user WHERE id=?",
                new Object[]{id});
    }

    /**
     * 获取整个商品列表
     *
     * @return
     */
    public List<Goods> readAllGoods() {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM goods", new String[]{});
        List<Goods> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            Goods goods = new Goods();
            goods.setId(cursor.getInt(cursor.getColumnIndex("id")));
            goods.setName(cursor.getString(cursor.getColumnIndex("name")));
            goods.setIntroduce(cursor.getString(cursor.getColumnIndex("introduce")));
            goods.setImage(cursor.getInt(cursor.getColumnIndex("image")));
            goods.setNum(cursor.getInt(cursor.getColumnIndex("num")));
            goods.setPrice(cursor.getString(cursor.getColumnIndex("price")));
            list.add(goods);
        }
        cursor.close();
        return list;
    }

    /**
     *通过此方法从数据库中获取用户加入购物车中的商品信息
     */
    public void obtainClickGoodsMessage(List<Goods> BuyCarList){
        List<Goods> list =  readAllGoods();
        for(Goods goods: list){
            if(goods.getNum() != 0){
                BuyCarList.add(goods);
            }
        }
    }

    /**
     * 读取一条数据
     *
     * @param id 商品id
     * @return 商品对象
     */
    public Goods readAGoods(Integer id) {

        //如果只对数据进行读取，建议使用此方法
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(
                "SELECT * FROM goods WHERE id=?",
                new String[]{id.toString()});
        if (cursor.moveToFirst()) {
            //读取数据，并返回
            Goods goods = new Goods();
            goods.setId(cursor.getInt(cursor.getColumnIndex("id")));
            goods.setName(cursor.getString(cursor.getColumnIndex("name")));
            goods.setImage(cursor.getInt(cursor.getColumnIndex("image")));
            goods.setPrice(cursor.getString(cursor.getColumnIndex("price")));
            Log.i("price====",cursor.getString(cursor.getColumnIndex("price")));
            goods.setIntroduce(cursor.getString(cursor.getColumnIndex("introduce")));
            goods.setNum(cursor.getInt(cursor.getColumnIndex("num")));
            cursor.close();
            return goods;
        } else {
            //未读出数据，返回空数据
            return null;
        }
    }
}

