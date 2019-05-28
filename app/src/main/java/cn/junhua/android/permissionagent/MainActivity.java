package cn.junhua.android.permissionagent;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.Arrays;

import cn.junhua.android.permission.PermissionAgent;
import cn.junhua.android.permission.core.AgentExecutor;
import cn.junhua.android.permission.core.callback.OnDeniedCallback;
import cn.junhua.android.permission.core.callback.OnGrantedCallback;
import cn.junhua.android.permission.core.callback.OnRationaleCallback;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


    }

    public void requestPermission(View view) {
        PermissionAgent.with(view)
                .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_CONTACTS)
                .onGranted(new OnGrantedCallback() {
                    @Override
                    public void onGranted(String[] permissions) {
                        Log.d(TAG, "onGranted() called with: permissions = " + Arrays.toString(permissions));
                    }
                })
                .onDenied(new OnDeniedCallback() {
                    @Override
                    public void onDenied(String[] permissions) {
                        Log.d(TAG, "onDenied() called with: permissions = " + Arrays.toString(permissions));
                    }
                })
                .onRationale(new OnRationaleCallback() {
                    @Override
                    public void onRationale(String[] permissions, AgentExecutor repeater) {
                        Log.d(TAG, "onRationale() called with: permissions = " + Arrays.toString(permissions));
                        repeater.execute();
                    }
                })
                .apply();
    }

    public void requestLocation(View view) {
        PermissionAgent.with(view)
                .request(Manifest.permission.ACCESS_FINE_LOCATION)
                .onGranted(new OnGrantedCallback() {
                    @Override
                    public void onGranted(String[] permissions) {
                        Log.d(TAG, "onGranted() called with: permissions = " + Arrays.toString(permissions));
                    }
                })
                .onDenied(new OnDeniedCallback() {
                    @Override
                    public void onDenied(String[] permissions) {
                        Log.d(TAG, "onDenied() called with: permissions = " + Arrays.toString(permissions));
                    }
                })
                .onRationale(new OnRationaleCallback() {
                    @Override
                    public void onRationale(String[] permissions, AgentExecutor repeater) {
                        Log.d(TAG, "onRationale() called with: permissions = " + Arrays.toString(permissions));
                        repeater.execute();
                    }
                })
                .apply();
    }

    public void requestOverlay(View view) {
        PermissionAgent.with(view)
                .overlay()
                .onGranted(new OnGrantedCallback() {
                    @Override
                    public void onGranted(String[] permissions) {
                        Log.d(TAG, "onGranted() called with: permissions = " + Arrays.toString(permissions));
                    }
                })
                .onDenied(new OnDeniedCallback() {
                    @Override
                    public void onDenied(String[] permissions) {
                        Log.d(TAG, "onDenied() called with: permissions = " + Arrays.toString(permissions));
                    }
                })
                .onRationale(new OnRationaleCallback() {
                    @Override
                    public void onRationale(String[] permissions, AgentExecutor repeater) {
                        Log.d(TAG, "onRationale() called with: permissions = " + Arrays.toString(permissions));
                        repeater.execute();
                    }
                })
                .apply();
    }
}
