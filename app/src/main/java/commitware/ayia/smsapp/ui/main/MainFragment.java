package commitware.ayia.smsapp.ui.main;

import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import commitware.ayia.smsapp.R;
import commitware.ayia.smsapp.RecyclerAdapter;
import commitware.ayia.smsapp.Sms;

public class MainFragment extends Fragment implements ActivityCompat.OnRequestPermissionsResultCallback{

    private RecyclerAdapter adapter;

    private static final String TAG = "MainFragment";

    private Context mActivity;

    public static MainFragment newInstance() {
        return new MainFragment();
    }
    @Override
    public void onAttach(@NonNull Context context) {

        super.onAttach(context);
        mActivity = context;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.main_fragment, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager reLayoutManager = new LinearLayoutManager(mActivity);
        recyclerView.setLayoutManager(reLayoutManager);


        adapter = new RecyclerAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(adapter);

        return root;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MainViewModel mViewModel = new ViewModelProvider(this).get(MainViewModel.class);


        mViewModel.getSMS().observe(getViewLifecycleOwner(), new Observer<List<Sms>>() {
            @Override
            public void onChanged(List<Sms> sms) {

                Log.d(TAG, "SmsList count : "+sms.size());

                adapter.swapItems(sms);

            }
        });

    }



}
