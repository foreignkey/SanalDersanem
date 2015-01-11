package com.softwareG06.sanaldersanem.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CreateDBQuiz extends SQLiteOpenHelper{


	public static String TABLE_USER="USER";
	public static  String COL_ID="_ID";
	public static  String COL_login = "login";
	public  static String COL_motdepasse = "motdepasse";
	public static  String COL_nom = "nom";
	public static String COL_prenom = "prenom";
	public static String COL_niveauetude = "niveauetude";



	public CreateDBQuiz(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + TABLE_USER + " (" + COL_ID + " INTEGER PRIMARY KEY," + COL_login + " TEXT,"+ COL_motdepasse + " TEXT," + COL_nom + " TEXT," + COL_prenom + " TEXT," + COL_niveauetude + " TEXT" + ");");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER +";");
		onCreate(db);

	}


}
