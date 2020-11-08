package commitware.ayia.smsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import commitware.ayia.smsapp.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    private static final int PERMISSION_REQUEST_READ_SMS = 0;

    private String[] PERMISSIONS = {
            Manifest.permission.READ_SMS,
            Manifest.permission.RECEIVE_SMS
    };

    private View mLayout;

    Permissions permissionUtility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);

        mLayout = findViewById(R.id.container);

        if (savedInstanceState == null) {
            // BEGIN_INCLUDE(startSMSFragment)
            // Check if the read sms permission has been granted
//            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)
//                    == PackageManager.PERMISSION_GRANTED) {
//                // Permission is already available, start sms fragment
//                Snackbar.make(mLayout,
//                        R.string.read_sms_permission_available,
//                        Snackbar.LENGTH_SHORT).show();
//                startFragment();
//
//            } else {
//                // Permission is missing and must be requested.
//                requestReadSMSPermission();
//            }

            // END_INCLUDE(startSMSFragment)


            permissionUtility = new Permissions(this, PERMISSIONS);
            if(permissionUtility.arePermissionsEnabled()){
                Snackbar.make(mLayout,
                        R.string.read_sms_permission_available,
                        Snackbar.LENGTH_SHORT).show();
                startFragment();
            } else {
                permissionUtility.requestMultiplePermissions();
            }


        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        // BEGIN_INCLUDE(onRequestPermissionsResult)


//        if (requestCode == PERMISSION_REQUEST_READ_SMS) {
//            // Request for read sms permission.
//            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // Permission has been granted. Start sweeping preview Activity.
//                Snackbar.make(mLayout, R.string.read_sms_permission_granted,
//                        Snackbar.LENGTH_SHORT)
//                        .show();
//
//                startFragment();
//
//            } else {
//                // Permission request was denied.
//                Snackbar.make(mLayout, R.string.read_sms_permission_denied,
//                        Snackbar.LENGTH_SHORT)
//                        .show();
//            }
//        }

        if(permissionUtility.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            // Permission has been granted. Start sweeping preview Activity.
            Snackbar.make(mLayout, R.string.read_sms_permission_granted,
                    Snackbar.LENGTH_SHORT)
                    .show();

            startFragment();
        }
        else {
            // Permission request was denied.
            Snackbar.make(mLayout, R.string.read_sms_permission_denied,
                    Snackbar.LENGTH_SHORT)
                    .show();
        }

    }



    /**
     * Requests the {@link android.Manifest.permission#READ_SMS} permission.
     * If an additional rationale should be displayed, the user has to launch the request from
     * a SnackBar that includes additional information.
     */
    private void requestReadSMSPermission() {
        // Permission has not been granted and must be requested.
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_SMS)) {
            // Provide an additional rationale to the user if the permission was not granted
            // and the user would benefit from additional context for the use of the permission.
            // Display a SnackBar with cda button to request the missing permission.
            Snackbar.make(mLayout, R.string.read_sms_access_required,
                    Snackbar.LENGTH_INDEFINITE).setAction(R.string.ok, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Request the permission
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.READ_SMS},
                            PERMISSION_REQUEST_READ_SMS);
                }
            }).show();

        } else {
            Snackbar.make(mLayout, R.string.read_sms_permission_not_available, Snackbar.LENGTH_SHORT).show();
            // Request the permission. The result will be received in onRequestPermissionResult().
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_SMS}, PERMISSION_REQUEST_READ_SMS);
        }
    }

    public void startFragment()
    {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow();
    }


}
