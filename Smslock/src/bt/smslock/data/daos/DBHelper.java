package bt.smslock.data.daos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MNQ on 5/9/2015.
 */
public class DBHelper extends SQLiteOpenHelper {

	private static String DB_NAME = "smslock";
	private final Context context;
	private String DB_PATH;

	// // Single ton DB
	private static DBHelper instance;

	@SuppressLint("SdCardPath")
	private DBHelper(Context context) {
		super(context, DB_NAME, null, 1);
		this.context = context;
		DB_PATH = "/data/data/" + context.getPackageName() + "/" + "databases/";
	}

	public static DBHelper getInstance() {
		return instance;
	}

	/**
	 * Hàm gọi khi mở đầu chương trình để thực hiện khởi tạo singleton
	 * 
	 * @param context
	 */
	public static void InitContext(Context context) {
		instance = new DBHelper(context);
	}

	// ////

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}

	private boolean checkDataBase() {
		File dbFile = new File(DB_PATH + DB_NAME);
		return dbFile.exists();
	}

	private void copyDataBase() throws IOException {
		InputStream myInput = context.getAssets().open(DB_NAME);
		String outFileName = DB_PATH + DB_NAME;
		OutputStream myOutput = new FileOutputStream(outFileName);
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}

		myOutput.flush();
		myOutput.close();
		myInput.close();
	}

	public void createDataBase() throws IOException {
		boolean dbExist = checkDataBase();
		if (dbExist) {
		} else {
			this.getReadableDatabase();
			try {
				copyDataBase();
			} catch (IOException e) {
				throw new Error("Error copying database");
			}
			this.close();
		}
	}
}