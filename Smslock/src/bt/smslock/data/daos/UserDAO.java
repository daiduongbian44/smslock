package bt.smslock.data.daos;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import bt.smslock.data.entities.UserEntity;

public class UserDAO implements IDAO<UserEntity> {

	public static String TABLE = "User";
	public static String USER_ID = "UserId";
	public static String USER_CONTACT = "UserContact";
	public static String USER_NUMBER = "UserNumber";

	@Override
	public ArrayList<UserEntity> getListData() {
		SQLiteDatabase db = DBHelper.getInstance().getReadableDatabase();
        String query = "Select * from " + TABLE;
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<UserEntity> listEntity = new ArrayList<>();
        if (cursor.getCount() > 0) {
            boolean isMove = false;
            for (isMove = cursor.moveToFirst(); isMove; isMove = cursor
                    .moveToNext()) {
                UserEntity entity = new UserEntity();
                entity.setUserId(cursor.getInt(cursor.getColumnIndex(USER_ID)));
                entity.setUserContact(cursor.getString(cursor.getColumnIndex(USER_CONTACT)));
                entity.setUserNumber(cursor.getString(cursor.getColumnIndex(USER_NUMBER)));

                listEntity.add(entity);
            }
        }
        return  listEntity;
	}

	@Override
	public void add(UserEntity object) {
		SQLiteDatabase db = DBHelper.getInstance().getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USER_CONTACT, object.getUserContact());
        cv.put(USER_NUMBER, object.getUserNumber());

        db.insert(TABLE, null, cv);
        db.close();
	}

	@Override
	public void update(UserEntity object) {
		SQLiteDatabase db = DBHelper.getInstance().getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USER_CONTACT, object.getUserContact());
        cv.put(USER_NUMBER, object.getUserNumber());

        db.update(TABLE, cv, USER_ID + "=" + object.getUserId(), null);
        db.close();
	}

	@Override
	public void delete(int id) {
		SQLiteDatabase db = DBHelper.getInstance().getWritableDatabase();
        db.delete(TABLE, USER_ID + "=" + id, null);
        db.close();
	}
}
