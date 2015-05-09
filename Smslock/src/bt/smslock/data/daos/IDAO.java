package bt.smslock.data.daos;

import java.util.ArrayList;

/**
 * Created by MNQ on 5/9/2015.
 */
public interface IDAO<T> {
    public ArrayList<T> getListData();
    public void add(T object);
    public void update(T object);
    public void delete(int id);
}
