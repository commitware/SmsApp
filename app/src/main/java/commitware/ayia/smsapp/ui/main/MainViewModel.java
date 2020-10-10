package commitware.ayia.smsapp.ui.main;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import commitware.ayia.smsapp.Sms;
import commitware.ayia.smsapp.SmsRepository;


public class MainViewModel extends AndroidViewModel {

    private  MutableLiveData<List<Sms>> smsList;

    private static final String TAG = "ViewModel";

    public MainViewModel(@NonNull Application application) {

        super(application);

        SmsRepository smsRepository = new SmsRepository(application);

        smsList = smsRepository.sweepSMSInbox();

        Log.d(TAG, "SmsList count : "+smsList.hasObservers());

    }

    public LiveData<List<Sms>> getSMS() {
        return smsList;
    }

}
