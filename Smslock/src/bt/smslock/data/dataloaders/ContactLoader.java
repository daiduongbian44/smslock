package bt.smslock.data.dataloaders;

import java.util.ArrayList;

import bt.smslock.data.entities.ContactEntity;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.MatchType;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

public class ContactLoader {
	private Activity mActivity;
	private ArrayList<ContactEntity> listContacts;

	public ContactLoader(Activity activity) {
		this.mActivity = activity;
		listContacts = new ArrayList<>();
	}

	public void loadContacts() {
		listContacts.clear();

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

			ContactEntity entity = new ContactEntity();
			entity.setDisplayName(name);
			entity.setNumber(number);

			listContacts.add(entity);
		} while (people.moveToNext());
		people.close();
	}

	public String findContactNameWithNumber(String numberUser) {
		for (int i = 0; i < listContacts.size(); ++i) {
			String number = listContacts.get(i).getNumber();
			String name = listContacts.get(i).getDisplayName();

			// compare two number
			PhoneNumberUtil pnu = PhoneNumberUtil.getInstance();
			MatchType mt = pnu.isNumberMatch(numberUser, number);
			if (mt == MatchType.NSN_MATCH || mt == MatchType.EXACT_MATCH) {
				return name;
			}
		}
		return numberUser;
	}
}
