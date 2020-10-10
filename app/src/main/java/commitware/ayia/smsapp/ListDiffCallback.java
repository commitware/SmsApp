package commitware.ayia.smsapp;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;


public class ListDiffCallback extends DiffUtil.Callback {

    private List<Sms> mOldList;
    private List<Sms> mNewList;

    public ListDiffCallback(List<Sms> oldList, List<Sms> newList) {
        this.mOldList = oldList;
        this.mNewList = newList;
    }
    @Override
    public int getOldListSize() {
        return mOldList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        // add a unique ID property on Contact and expose a getId() method
        return mOldList.get(oldItemPosition).getSubHeading().equals(mNewList.get(newItemPosition).getSubHeading());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Sms oldList = mOldList.get(oldItemPosition);
        Sms newList = mNewList.get(newItemPosition);

        if (oldList.getSubHeading().equals(newList.getSubHeading()) && oldList.getHeading().equals(newList.getHeading())) {
            return true;
        }
        return false;
    }
}
