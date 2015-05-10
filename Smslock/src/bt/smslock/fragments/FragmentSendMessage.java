package bt.smslock.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import bt.smslock.R;

public class FragmentSendMessage extends Fragment implements OnClickListener {

	private EditText textPhoneNumber;
	private Button btnLoadContact;
	private EditText textMessage;
	private Button btnSend;
	private final int GET_PHONE_NUMBER = 100;
	private String phoneNumber;
	private String name;

	private View rootView;

	public FragmentSendMessage() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_send_message, container,
				false);

		return rootView;
	}

	public void initComponents() {
		textPhoneNumber = (EditText) rootView
				.findViewById(R.id.text_phone_number);
		btnLoadContact = (Button) rootView.findViewById(R.id.btn_load_contact);
		textMessage = (EditText) rootView.findViewById(R.id.text_message);
		btnSend = (Button) rootView.findViewById(R.id.btn_send);

		btnLoadContact.setOnClickListener(this);
		btnSend.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == btnLoadContact) {
			Intent intent = new Intent(Intent.ACTION_PICK,
					ContactsContract.Contacts.CONTENT_URI);
			startActivityForResult(intent, GET_PHONE_NUMBER);
		}

		if (v == btnSend) {
			// c xem lại phần gửi tin nhắn này giúp t nhé (kiểm tra độ dài tin nhắn,
			// chỉ cho phép gửi 1 tin gì đó,...t chưa viết được)
			String messageContent = textMessage.getText().toString();
			if (!messageContent.isEmpty()) {
				try {
					SmsManager smsManager = SmsManager.getDefault();
					smsManager.sendTextMessage(phoneNumber, null,
							messageContent, null, null);
				} catch (Exception e) {
					Toast.makeText(getActivity().getApplicationContext(),
							"Không gửi tin nhắn thành công, vui long thử lại.",
							Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
			} else {
				Toast.makeText(getActivity().getApplicationContext(),
						"Bạn cần nhập tin nhắn", Toast.LENGTH_LONG).show();
			}
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case (GET_PHONE_NUMBER):
			if (resultCode == Activity.RESULT_OK) {
				Uri contactData = data.getData();
				Cursor c = getActivity().getContentResolver().query(
						contactData, null, null, null, null);
				if (c.moveToFirst()) {
					try {
						String id = c
								.getString(c
										.getColumnIndexOrThrow(ContactsContract.Contacts._ID));

						String hasPhone = c
								.getString(c
										.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

						if (hasPhone.equalsIgnoreCase("1")) {
							Cursor phones = getActivity()
									.getContentResolver()
									.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
											null,
											ContactsContract.CommonDataKinds.Phone.CONTACT_ID
													+ " = " + id, null, null);
							phones.moveToFirst();

							name = c.getString(c
									.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
							phoneNumber = phones.getString(phones
									.getColumnIndex("data1"));

							if (phoneNumber != null) {
								textPhoneNumber.setText(phoneNumber);
							}

						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				c.close();
			}
			break;
		}
	}
}
