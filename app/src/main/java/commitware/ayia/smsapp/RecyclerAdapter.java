package commitware.ayia.smsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context mContext;

    private List<Sms> mValues = new ArrayList<>();

    public RecyclerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void swapItems(List<Sms> mValues) {
        // compute diffs
        final ListDiffCallback diffCallback = new ListDiffCallback(this.mValues, mValues);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        // clear contacts and add
        if(mValues != null)
        {
            if(mValues.size() > 0)
            {
                this.mValues.clear();
            }
            this.mValues.addAll(mValues);
        }
        diffResult.dispatchUpdatesTo(this); // calls adapter's notify methods after diff is computed
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {

        public TextView textView, textView2, textView3;

        Sms item;

        public ViewHolder(View v) {

            super(v);

            v.setTag(this);


            textView =  v.findViewById(R.id.textView);
            textView2 = v.findViewById(R.id.textView2);
            textView3 = v.findViewById(R.id.textView3);



        }

        public void setData(Sms item) {

            this.item = item;

            textView.setText(item.getHeading());

            String body = item.getSubHeading();

            if (!body.equals(""))
                if (body.length() > 28)
                    body = body.substring(0, 28) + "..";

            textView2.setText(body);
            textView3.setText(item.getDate());

        }


    }


    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {

        holder.setData(mValues.get(position));

    }

    @Override
    public int getItemCount() {
        return (null != mValues ? mValues.size() : 0);
    }


}
