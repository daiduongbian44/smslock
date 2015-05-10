package bt.smslock.fragments;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import bt.smslock.R;
import bt.smslock.adapters.MessageAdapter;
import bt.smslock.data.entities.ThreadSMSEntity;

public class FragmentListMessages extends Fragment implements
		View.OnClickListener {

	private ThreadSMSEntity threadSMSEntity;
	private ListView lvMessage;

	// send message
	private TextView textInforSendMessage;
	private EditText textMessageContent;
	private Button btnSend;

	public FragmentListMessages(ThreadSMSEntity threadSMSEntity) {
		this.threadSMSEntity = threadSMSEntity;
	}

	private View rootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater
				.inflate(R.layout.fragment_message, container, false);
		initComponets();

		MessageAdapter adapter = new MessageAdapter(getActivity()
				.getApplicationContext(), threadSMSEntity.getListMessages());

		lvMessage.setAdapter(adapter);
		scrollMyListViewToBottom();

		return rootView;
	}

	private void scrollMyListViewToBottom() {
		lvMessage.post(new Runnable() {
			@Override
			public void run() {
				// Select the last row so it will scroll into view...
				lvMessage.setSelection(lvMessage.getCount() - 1);
			}
		});
	}

	public void initComponets() {
		lvMessage = (ListView) rootView.findViewById(R.id.lv_Message);
		textInforSendMessage = (TextView) rootView
				.findViewById(R.id.textInfoSendMessage);
		textMessageContent = (EditText) rootView
				.findViewById(R.id.editTextMessage);
		btnSend = (Button) rootView.findViewById(R.id.buttonSend);

		textMessageContent.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// xử lý thông tin của infor khi thay đổi ký tự
				if (!textMessageContent.getText().toString().isEmpty()) {
					SmsManager sms = SmsManager.getDefault();
					ArrayList<String> parts = sms.divideMessage(textMessageContent.getText().toString());
					textInforSendMessage.setText(parts.size() + "");
				} else if (textMessageContent.getText().toString().isEmpty()) {
					textInforSendMessage.setText(0 + "");
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
	}

	@Override
	public void onClick(View v) {
		if (v == btnSend) {
			// xử lý gửi tin nhắn ở đây
			sendSMSMessage();
		}
	}

	private void sendSMSMessage() {
		String messageContent = textMessageContent.getText().toString();
		String phoneNumber = threadSMSEntity.getAddress();
		if (!messageContent.isEmpty()) {
			try {
				SmsManager smsManager = SmsManager.getDefault();
				smsManager.sendTextMessage(phoneNumber, null, messageContent,
						null, null);
			} catch (Exception e) {
				Toast.makeText(getActivity().getApplicationContext(),
						"Không gửi tin nhắn thành công, vui long thử lại.", Toast.LENGTH_LONG)
						.show();
				e.printStackTrace();
			}
		} else {
			Toast.makeText(getActivity().getApplicationContext(),
					"Bạn cần nhập tin nhắn", Toast.LENGTH_LONG).show();
		}
	}
}