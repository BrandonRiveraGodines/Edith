package com.trabajo.edith.panic.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by brand on 18/11/2017.
 */

public class SQLiteHandlerPet extends SQLiteOpenHelper{
    private static final String TAG = SQLiteHandlerPet.class.getSimpleName();

    // Al static variables
    // Database versión
    private static final int DATABASE_VERSION = 1;

    // Database name
    private static final String DATABASE_NAME = "edith";

    // Login table name
    private static final String TABLE_USER = "users";

    // Login Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_CONTACTO_1 = "contacto_1";
    private static final String KEY_TELEFONO_1 = "telefono_1";
    private static final String KEY_CONTACTO_2 = "contacto_2";
    private static final String KEY_TELEFONO_2 = "telefono_2";
    private static final String KEY_CONTACTO_3 = "contacto_3";
    private static final String KEY_TELEFONO_3 = "telefono_3";
    private static final String KEY_CONTACTO_MSJ = "contacto_mensaje";
    private static final String KEY_TELEFONO_MSJ = "telefono_mensaje";
    private static final String KEY_MENSAJE = "mensaje";

    public SQLiteHandlerPet(Context context){
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY " + KEY_NAME + " TEXT, "
                + KEY_CONTACTO_1 + " TEXT, " + KEY_TELEFONO_1 + " TEXT, "
                + KEY_CONTACTO_2 + " TEXT, " + KEY_TELEFONO_2 + " TEXT, "
                + KEY_CONTACTO_3 + " TEXT, " + KEY_TELEFONO_3 + " TEXT, "
                + KEY_CONTACTO_MSJ + " TEXT, " + KEY_TELEFONO_MSJ + " TEXT, "
                + KEY_MENSAJE + " TEXT " + ")";
        Log.d(TAG, "Database tables created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        // Create tables again
        onCreate(db);
    }

    public void addPet(){

    }

}
