package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry1 = "create table users(username text, email text, password text)";
        db.execSQL(qry1);
        String qry2 = "create table cart(username text, product text, price float, other text)";
        db.execSQL(qry2);
        String qry3 = "create table orders(username text, fullname text, address text, phone text, pincode text, date text, time text, amount float, other text)";
        db.execSQL(qry3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void register(String username, String email, String password) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users", null, cv);
        db.close();

    }

    public boolean login(String username, String password) {


        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from users where username = ? and password = ?", new String[] {username, password});

        boolean res = c.moveToFirst();
        c.close();
        db.close();
        return res;
    }

    public void addCart(String username, String product, float price, String other) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("product", product);
        contentValues.put("price", price);
        contentValues.put("other", other);
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.insert("cart", null, contentValues);
        writableDatabase.close();

    }
    public boolean checkCart(String username, String product) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor c = readableDatabase.rawQuery("select * from cart where username = ? and product = ?", new String[] {username, product});
        boolean res = c.moveToFirst();
        c.close();
        readableDatabase.close();
        return res;
    }


    public void removeCart(String username, String other) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        readableDatabase.delete("cart", "username = ? and other = ?", new String[] {username, other});

        readableDatabase.close();

    }
    public ArrayList getCartData(String username, String other) {
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from cart where username = ? and other = ?", new String[] {username, other});
        ArrayList<String> res = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                String product = c.getString(1);
                String price = c.getString(2);
                res.add(product + "$" + price);

            } while (c.moveToNext());
        }

        c.close();
        db.close();
        return res;
    }

    public boolean checkAppointment(String username, String fullName, String address, String contact, String data, String time) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor c = readableDatabase.rawQuery(
                "select * from orders where username = ? and fullname = ? and address = ? and phone = ? and date = ? and time = ? and other = ?",
                new String[] {username, fullName, address, contact, data, time, "appointment"});
        boolean res = c.moveToFirst();
        c.close();
        readableDatabase.close();
        return res;
    }
    public void createAppointment(String username, String fullName, String address, String contact, String date, String time) {
        //username text, fullname text, address text, contact text,date text, time text
        addOrder(username, fullName, address, contact, "0000", date, time, 0, "appointment");
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("username", username);
//        contentValues.put("fullname", fullName);
//        contentValues.put("address", address);
//        contentValues.put("contact", contact);
//        contentValues.put("data", data);
//        contentValues.put("time", time);
//        SQLiteDatabase writableDatabase = getWritableDatabase();
//        writableDatabase.insert("appointments", null, contentValues);
//        writableDatabase.close();
    }
    public void addOrder(String username, String fullname, String address, String contact, String pincode, String date, String time, float price, String other) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("fullname", fullname);
        cv.put("address", address);
        cv.put("phone", contact);
        cv.put("pincode", pincode);
        cv.put("date", date);
        cv.put("time", time);
        cv.put("amount", price);
        cv.put("other", other);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("orders", null, cv);
        db.close();
    }

    public ArrayList getOrderData(String username) {
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery("select * from orders where username = ?", new String[] {username});
        if (c.moveToFirst()) {
            do {
                String record = "";
                for (int i = 1; i < 9; i++) {
                   record += c.getString(i) + "$";
                }
                arr.add(record);
            } while (c.moveToNext());
        }
        db.close();
        return arr;
    }
}
