package bt.smslock.data.daos;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import bt.smslock.data.entities.LoginEntity;

public class LoginDAO implements IDAO<LoginEntity> {
	public static String TABLE = "Login";
	public static String LOGIN_ID = "LoginId";
	public static String USERNAME = "UserName";
	public static String PASSWORD = "Password";

	@Override
	public ArrayList<LoginEntity> getListData() {
		SQLiteDatabase db = DBHelper.getInstance().getReadableDatabase();
		String query = "Select * from " + TABLE;
		Cursor cursor = db.rawQuery(query, null);

		ArrayList<LoginEntity> listEntity = new ArrayList<>();
		if (cursor.getCount() > 0) {
			boolean isMove = false;
			for (isMove = cursor.moveToFirst(); isMove; isMove = cursor
					.moveToNext()) {
				LoginEntity entity = new LoginEntity();
				entity.setLoginId(cursor.getInt(cursor.getColumnIndex(LOGIN_ID)));
				entity.setUsername(cursor.getString(cursor
						.getColumnIndex(USERNAME)));
				entity.setPassword(cursor.getString(cursor
						.getColumnIndex(PASSWORD)));

				listEntity.add(entity);
			}
		}
		return listEntity;
	}

	@Override
	public void add(LoginEntity object) {
		SQLiteDatabase db = DBHelper.getInstance().getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(USERNAME, object.getUsername());
		cv.put(PASSWORD, object.getPassword());

		db.insert(TABLE, null, cv);
		db.close();
	}

	@Override
	public void update(LoginEntity object) {
		SQLiteDatabase db = DBHelper.getInstance().getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(USERNAME, object.getUsername());
		cv.put(PASSWORD, object.getPassword());

		db.update(TABLE, cv, LOGIN_ID + "=" + object.getLoginId(), null);
		db.close();
	}

	@Override
	public void delete(int id) {
		SQLiteDatabase db = DBHelper.getInstance().getWritableDatabase();
		db.delete(TABLE, LOGIN_ID + "=" + id, null);
		db.close();
	}
}
