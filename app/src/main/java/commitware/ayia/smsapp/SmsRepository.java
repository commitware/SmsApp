package commitware.ayia.smsapp;

import android.app.Application;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;


import androidx.lifecycle.MutableLiveData;


import java.util.ArrayList;
import java.util.List;

public class SmsRepository {
    private static final String TAG = "SmsRepository";


    private Application application;

    private final MutableLiveData<List<Sms>> liveData = new MutableLiveData<>();

    public SmsRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Sms>> sweepSMSInbox() {

        Log.d(TAG, "Sweeping  Inbox");

        ContentResolver contentResolver = application.getContentResolver();

        Cursor smsInboxCursor = contentResolver.query(Uri.parse("content://sms/inbox"), null, null, null, null);


        if (smsInboxCursor != null) {

        int indexBody = smsInboxCursor.getColumnIndex("body");

        int indexAddress = smsInboxCursor.getColumnIndex("address");

        smsInboxCursor.moveToFirst();

        List<Sms> smsList = new ArrayList<>();

        while (!(smsInboxCursor.isAfterLast())){
//            String fromAddress = smsInboxCursor.getString(smsInboxCursor.getColumnIndexOrThrow("address"));
//            String body = smsInboxCursor.getString(smsInboxCursor.getColumnIndexOrThrow("body"));
            Sms sms = new Sms();

            String smsBody = smsInboxCursor.getString(indexBody) + "\n";

            String smsAddress = smsInboxCursor.getString(indexAddress);

            long date = Long.valueOf(smsInboxCursor.getString(smsInboxCursor.getColumnIndexOrThrow("date")));

            sms.setHeading(smsAddress);

            sms.setSubHeading(smsBody);

            DateFormatter myFormatter =  new DateFormatter();

            sms.setDate(myFormatter.calenderToDate(myFormatter.longToCalender(date)));

            smsList.add(sms);



            liveData.setValue(smsList);

            smsInboxCursor.moveToNext();
        }

        Log.d(TAG, "SmsList count : "+smsList.size());

        smsInboxCursor.close();
    }

        return liveData;
    }


}
