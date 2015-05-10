package bt.smslock;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import bt.smslock.adapters.UserBlacklistAdapter;
import bt.smslock.data.entities.UserEntity;
import de.timroes.swipetodismiss.SwipeDismissList;
import de.timroes.swipetodismiss.SwipeDismissList.UndoMode;
import de.timroes.swipetodismiss.SwipeDismissList.Undoable;

public class UserBlackListActivity extends Activity {

	private final int PICK_CONTACT = 1;

	private ListView listViewUserBlackList;
	private ImageView btnAddContact;
	private UserBlacklistAdapter userBlacklistAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_black_list);

		listViewUserBlackList = (ListView) findViewById(R.id.lvUserBlacklist);
		btnAddContact = (ImageView) findViewById(R.id.imageViewAddContact);

		userBlacklistAdapter = new UserBlacklistAdapter(getApplicationContext());

		btnAddContact.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_PICK,
						ContactsContract.Contacts.CONTENT_URI);
				startActivityForResult(intent, PICK_CONTACT);
			}
		});

		listViewUserBlackList.setAdapter(userBlacklistAdapter);

		SwipeDismissList.OnDismissCallback callback = new SwipeDismissList.OnDismissCallback() {
			@Override
			public Undoable onDismiss(AbsListView listView, int position) {
				// TODO Auto-generated method stub

				final UserEntity entity = (UserEntity) userBlacklistAdapter
						.getItem(position);
				userBlacklistAdapter.removeUserEntity(entity);
				return new SwipeDismissList.Undoable() {
		            public void undo() {
		               userBlacklistAdapter.addUserEntity(entity);
		            }
		        };
			}
		};

		UndoMode mode = UndoMode.SINGLE_UNDO;
		new SwipeDismissList(listViewUserBlackList, callback, mode);
	}

	public void onActivityResult(int reqCode, int resultCode, Intent data) {
		super.onActivityResult(reqCode, resultCode, data);
		switch (reqCode) {
		case (PICK_CONTACT):
			if (resultCode == Activity.RESULT_OK) {
				Uri contactData = data.getData();
				Cursor c = getContentResolver().query(contactData, null, null,
						null, null);
				if (c.moveToFirst()) {
					try {
						String id = c
								.getString(c
										.getColumnIndexOrThrow(ContactsContract.Contacts._ID));

						String hasPhone = c
								.getString(c
										.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

						if (hasPhone.equalsIgnoreCase("1")) {
							Cursor phones = getContentResolver()
									.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
											null,
											ContactsContract.CommonDataKinds.Phone.CONTACT_ID
													+ " = " + id, null, null);
							phones.moveToFirst();

							String name = c
									.getString(c
											.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
							String cNumber = phones.getString(phones
									.getColumnIndex("data1"));

							UserEntity entity = new UserEntity();
							entity.setUserContact(name);
							entity.setUserNumber(cNumber);

							userBlacklistAdapter.addUserEntity(entity);
						}

					} catch (Exception e) {
					}
				}
				c.close();
			}
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_black_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
