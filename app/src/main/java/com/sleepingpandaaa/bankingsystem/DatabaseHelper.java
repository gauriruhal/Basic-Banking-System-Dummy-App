package com.sleepingpandaaa.bankingsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(9090989990,'Roshesh',17892.00,'roshesh@gmail.com','XXXXXXXXXXXX2345','ABD09756236')");
        db.execSQL("insert into user_table values(9190764120,'Sahil',2020.21,'sahil@gmail.com','XXXXXXXXXXXX5678','BCE78965432')");
        db.execSQL("insert into user_table values(9212121212,'Indravadhan',1675.96,'indravadhan@gmail.com','XXXXXXXXXXXX8976','CAB876544789')");
        db.execSQL("insert into user_table values(9338281290,'Maya',3030.30,'maya@gmail.com','XXXXXXXXXXXX8282','ABC76567676')");
        db.execSQL("insert into user_table values(9568975178,'Monisha',678.48,'monisha@gmail.com','XXXXXXXXXXXX6567','BCW55478076')");
        db.execSQL("insert into user_table values(9988189281,'Dushyant',1000.16,'dushyant@gmail.com','XXXXXXXXXXXX5437','UAB54327890')");
        db.execSQL("insert into user_table values(9346788990,'Madhusughan',5936.00,'madhusughan@gmail.com','XXXXXXXXXXXX5987','ABC432190576')");
        db.execSQL("insert into user_table values(9010201020,'Teejay',6789.92,'teejay@gmail.com','XXXXXXXXXXXX7689','BCA32109321')");
        db.execSQL("insert into user_table values(9928191001,'Karanvir',4398.46,'karanvir@gmail.com','XXXXXXXXXXXX8932','CAB21098890')");
        db.execSQL("insert into user_table values(9233948190,'Vivek',2908.90,'vivek@gmail.com','XXXXXXXXXXXX8754','ABC10986231')");
        db.execSQL("insert into user_table values(9929394891,'Kusha',898.89,'kusha@gmail.com','XXXXXXXXXXXX7643','ABC10999101')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
