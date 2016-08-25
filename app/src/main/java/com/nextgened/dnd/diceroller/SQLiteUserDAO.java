package com.nextgened.dnd.diceroller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 * Created by Administrator on 8/25/2016.
 */
public class SQLiteUserDAO implements UserDAO {
    private Context context;
    private UserSQLiteHelper helper;
    private SQLiteDatabase db;

    SQLiteUserDAO(Context context) {
        this.context = context;
        helper = new UserSQLiteHelper(context);
        db = helper.getWritableDatabase();
    }

    @Override
    public User insert(User user) {
        ContentValues values = new ContentValues();
        values.put("customerId", user.getCustomerId());
        values.put("firstName", user.getFirstName());
        values.put("lastName", user.getLastName());
        values.put("phoneNumber", user.getPhoneNumber());
        values.put("email", user.getEmail());
//        These fields need up/down conversion:
        values.put("lastUpdated", user.getLastUpdated().getTime());
        values.put("createdDate", user.getCreatedDate().getTime());
        values.put("birthDate", user.getBirthDate().getTime());

        db.insert("User", null, values);

        return user;
    }

    @Override
    public User delete(User user) {
        return null;
    }

    @Override
    public User findById(Long id) {
        Cursor c = db.query("User",
                    new String[]{"customerId", "firstName", "lastName", "phoneNumber",
                    "email", "lastUpdated", "createdDate", "birthDate"},
                    "customerId", // where clause fields
                    new String[] {new Long(id).toString()}, // values for where clause fields
                    null,           // group by fields
                    null,           // filter fields
                    null            // sort order
                );
        User u = null;
        if (c.moveToFirst()) {
            u = convertRowToUser(c);
        }
        return u;
    }

    @NonNull
    private User convertRowToUser(Cursor c) {
        User u = new User();
        u.setCustomerId(c.getLong(1));
        u.setFirstName(c.getString(2));
        u.setLastName(c.getString(2));
        u.setPhoneNumber(c.getString(2));
        u.setEmail(c.getString(2));
        u.setLastUpdated(new Date(c.getLong(1)));
        u.setCreatedDate(new Date(c.getLong(1)));
        u.setBirthDate(new Date(c.getLong(1)));
        return u;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new Vector<>();
        Cursor c = db.query("User",
                new String[]{"customerId", "firstName", "lastName", "phoneNumber",
                        "email", "lastUpdated", "createdDate", "birthDate"},
                null, // where clause fields
                null, // values for where clause fields
                null,           // group by fields
                null,           // filter fields
                null            // sort order
        );
        while(c.moveToNext()) {
            users.add(convertRowToUser(c));
        }
        return users;
    }

    @Override
    public List<User> findByEmail(String email) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }
}
