package bt.smslock;

import java.io.IOException;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import bt.smslock.data.daos.DBHelper;
import bt.smslock.data.dataloaders.ThreadSMSLoader;
import bt.smslock.data.entities.ThreadSMSEntity;
import bt.smslock.fragments.FragmentListMessages;
import bt.smslock.fragments.FragmentListThreadMessage;
import bt.smslock.interfaces.ITranferToContactMessage;

public class SmsLockActivity extends Activity implements ITranferToContactMessage{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sms_lock);
		
		// kiểm tra và tạo db
		try {
			DBHelper.InitContext(getApplicationContext());
			DBHelper.getInstance().createDataBase();
		} catch (IOException e) {
			Toast.makeText(getApplicationContext(),
					"Having at least an error in dbsystem", Toast.LENGTH_LONG)
					.show();
			e.printStackTrace();
		}
		
		//Intent intent = new Intent(this, UserBlackListActivity.class);
		//startActivity(intent);
		
		// load tin nhắn
		ThreadSMSLoader threadSMSLoader = new ThreadSMSLoader(this);
        threadSMSLoader.loadAllThreadSMS();
        
        Toast.makeText(getApplicationContext(), "Count: " + 
        threadSMSLoader.getListThreadMessages().size(),Toast.LENGTH_LONG).show();
		
		if (savedInstanceState == null) {
			changeFragmentContent(new FragmentListThreadMessage(
							threadSMSLoader.getListThreadMessages(), this));
		}
	}

	public void changeFragmentContent(Fragment fragment) {
        String backStateName = fragment.getClass().getName();

        FragmentManager manager = getFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);

        if (!fragmentPopped){
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(R.id.container, fragment);
            ft.addToBackStack(backStateName);
            ft.commit();
        }
    }
	
	@Override
	public void tranferToContactMessage(ThreadSMSEntity entity) {
		changeFragmentContent(new FragmentListMessages(entity));
	}
	
	@Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }
}
