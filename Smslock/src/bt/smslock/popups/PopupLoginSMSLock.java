package bt.smslock.popups;

import bt.smslock.R;
import bt.smslock.storages.LoginStoragePreference;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PopupLoginSMSLock extends DialogFragment implements
		View.OnClickListener {
	private Button btnLogin;
	private Button btnCancel;
	private EditText txtUserName;
	private EditText txtPassword;

	public static PopupLoginSMSLock newInstance() {
		PopupLoginSMSLock f = new PopupLoginSMSLock();
		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.getDialog().setTitle("Login");

		View v = inflater.inflate(R.layout.popup_login, container, false);
		btnLogin = (Button) v.findViewById(R.id.buttonLogin);
		btnCancel = (Button) v.findViewById(R.id.buttonCancel);
		txtUserName = (EditText) v.findViewById(R.id.editTextUsername);
		txtPassword = (EditText) v.findViewById(R.id.editTextPassword);

		btnLogin.setOnClickListener(this);
		btnCancel.setOnClickListener(this);
		return v;
	}

	@Override
	public void onClick(View v) {
		if (v == btnLogin) {
			String userName = txtUserName.getText().toString();
			String password = txtPassword.getText().toString();

			if (userName.trim().equals("") || password.trim().equals("")) {
				Toast.makeText(getActivity().getApplicationContext(),
						"Username or password is empty, please enter text",
						Toast.LENGTH_LONG).show();
			} else {
				LoginStoragePreference loginStorage = new LoginStoragePreference(
						getActivity().getApplicationContext());
				if (loginStorage.checkLoginSuccess(userName, password)) {
					// redirect page
					// if (this.getActivity() instanceof MessageLockAppActivity)
					// {
					// Toast.makeText(getActivity().getApplicationContext(),
					// "Is ok", Toast.LENGTH_LONG).show();
					// }
					//
				} else {
					Toast.makeText(getActivity().getApplicationContext(),
							"Username or password is invalid, re-enter text",
							Toast.LENGTH_LONG).show();
				}
			}
		} else if (v == btnCancel) {
			txtUserName.setText("");
			txtPassword.setText("");
		}
	}
}
