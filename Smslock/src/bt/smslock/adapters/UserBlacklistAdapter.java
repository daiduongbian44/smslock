package bt.smslock.adapters;

import java.util.ArrayList;

import bt.smslock.R;
import bt.smslock.data.daos.UserDAO;
import bt.smslock.data.entities.UserEntity;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class UserBlacklistAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private ArrayList<UserEntity> listUserEntities;

	public UserBlacklistAdapter(Context context) {
		loadListUser();
		inflater = (LayoutInflater) context
				.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
	}

	public void addUserEntity(UserEntity entity) {
		UserDAO dao = new UserDAO();
		dao.add(entity);
		listUserEntities = dao.getListData();
		this.notifyDataSetChanged();
	}

	public void loadListUser() {
		UserDAO dao = new UserDAO();
		listUserEntities = dao.getListData();
	}
	
	public void removeUserEntity(UserEntity entity){
		UserDAO dao = new UserDAO();
		dao.delete(entity.getUserId());
		listUserEntities = dao.getListData();
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		if (listUserEntities != null) {
			return listUserEntities.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if (listUserEntities != null && position >= 0
				&& position < listUserEntities.size()) {
			return listUserEntities.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if(view == null){
			view = inflater.inflate(R.layout.item_userblacklist, null, false);
		}
		
		Holder holder = new Holder();
		holder.initComponent(view);
		
		UserEntity entity = (UserEntity) getItem(position);
		if(entity != null ){
			holder.textUserContact.setText(entity.getUserContact());
			holder.textUserNumber.setText(entity.getUserNumber());
		}
		return view;
	}
	
	private class Holder {
		TextView textUserContact;
		TextView textUserNumber;
		
		public void initComponent(View view) {
			textUserContact = (TextView) view.findViewById(R.id.text_userContact);
			textUserNumber = (TextView) view.findViewById(R.id.text_userNumber);
		}
	}

}
