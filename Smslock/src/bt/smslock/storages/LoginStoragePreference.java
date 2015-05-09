package bt.smslock.storages;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class LoginStoragePreference {
	private static final String MyPREFERENCES = "MessageLock";
	private static final String Username = "Username";
	private static final String Password = "Password";

	private static final String UsernameDefault = "manh";
	private static final String PasswordDefault = "123";

	private SharedPreferences sharedpreferences;

	public LoginStoragePreference(Context context) {
		if (context != null) {
			sharedpreferences = context.getSharedPreferences(MyPREFERENCES,
					Context.MODE_PRIVATE);
		}
	}

	/**
	 * Phương thức này cần gọi ngay khi chương trình bắt đầu
	 */
	@SuppressLint("CommitPrefEdits")
	public void InitAccount() {
		if (!sharedpreferences.contains(Username)) {
			Editor editor = sharedpreferences.edit();
			editor.putString(Username, UsernameDefault);
			editor.putString(Password, PasswordDefault);
			editor.commit();
		}
	}

	/**
	 * Lưu lại thông tin người dùng
	 * 
	 * @param username
	 * @param password
	 */
	public void updateInfoAccount(String username, String password) {
		Editor editor = sharedpreferences.edit();
		editor.putString(Username, username);
		editor.putString(Password, password);
		editor.commit();
	}

	/**
	 * Kiểm tra thông tin người dùng xem
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean checkLoginSuccess(String username, String password) {
		String userDefault = sharedpreferences.getString(Username, "");
		String passDefault = sharedpreferences.getString(Password, "");

		if (userDefault.equals(username) && passDefault.equals(password)) {
			return true;
		}
		return false;
	}
}
