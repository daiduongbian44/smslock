package bt.smslock.data.dataloaders;

import java.util.ArrayList;

import bt.smslock.data.daos.UserDAO;
import bt.smslock.data.entities.UserEntity;

public class UserSecureLoader {
	private ArrayList<UserEntity> mListUserEntity;

	public UserSecureLoader() {
		mListUserEntity = new ArrayList<>();
	}

	public void loadListUser() {
		UserDAO dao = new UserDAO();
		mListUserEntity = dao.getListData();
	}

	public ArrayList<UserEntity> getListUserEntity() {
		return mListUserEntity;
	}
}
