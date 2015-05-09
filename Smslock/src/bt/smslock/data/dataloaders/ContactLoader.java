package bt.smslock.data.dataloaders;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

public class ContactLoader {
	private Activity mActivity;

	public ContactLoader(Activity activity) {
		this.mActivity = activity;
	}

	public String loadContactFromNumber(String numberUser) {
		Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
		String[] projection = new String[] {
				ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
				ContactsContract.CommonDataKinds.Phone.NUMBER };

		Cursor people = mActivity.getContentResolver().query(uri, projection,
				null, null, null);

		int indexName = people
				.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
		int indexNumber = people
				.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

		people.moveToFirst();
		do {
			String name = people.getString(indexName);
			String number = people.getString(indexNumber);

			// compare two number

		} while (people.moveToNext());
		return numberUser;
	}
}
