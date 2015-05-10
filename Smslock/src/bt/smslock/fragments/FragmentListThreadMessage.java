package bt.smslock.fragments;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import bt.smslock.R;
import bt.smslock.adapters.ThreadAdapter;
import bt.smslock.data.entities.ThreadSMSEntity;
import bt.smslock.interfaces.ITranferToContactMessage;

public class FragmentListThreadMessage extends Fragment {

	private View rootView;
	private ListView lvThread;
	private ArrayList<ThreadSMSEntity> listThread;
	private ITranferToContactMessage callbackToMessage;

	public FragmentListThreadMessage(ArrayList<ThreadSMSEntity> listThread, ITranferToContactMessage callbackToMessage) {
		this.listThread = listThread;
		this.callbackToMessage = callbackToMessage;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_listthread_message,
				container, false);

		initComponents();
		
		ThreadAdapter adapter = new ThreadAdapter(getActivity()
				.getApplicationContext(), listThread);
		lvThread.setAdapter(adapter);
		
		lvThread.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getActivity().getApplicationContext(), "click", Toast.LENGTH_LONG).show();
				
				ThreadSMSEntity entity = listThread.get(position);
				if (entity != null && callbackToMessage != null) {
					callbackToMessage.tranferToContactMessage(entity);
				}
			}
		});
		return rootView;
	}

	public void initComponents() {
		lvThread = (ListView) rootView.findViewById(R.id.lv_thread);
	}
}
