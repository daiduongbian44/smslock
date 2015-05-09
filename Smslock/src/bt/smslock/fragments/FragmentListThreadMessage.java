package bt.smslock.fragments;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import bt.smslock.R;
import bt.smslock.adapters.ThreadAdapter;
import bt.smslock.data.entities.ThreadSMSEntity;

public class FragmentListThreadMessage extends Fragment {

	private View rootView;
	private ListView lvThread;
	private ArrayList<ThreadSMSEntity> listThread;

	public FragmentListThreadMessage(ArrayList<ThreadSMSEntity> listThread) {
		this.listThread = listThread;
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

		return rootView;
	}

	public void initComponents() {
		lvThread = (ListView) rootView.findViewById(R.id.lv_thread);
		listThread = new ArrayList<>();
	}
}
