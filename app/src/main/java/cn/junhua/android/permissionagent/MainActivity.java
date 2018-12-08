package cn.junhua.android.permissionagent;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import cn.junhua.android.permission.PermissionAgent;
import cn.junhua.android.permission.core.Action;
import cn.junhua.android.permission.core.AgentExecutor;
import cn.junhua.android.permission.core.Rationale;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void requestPermission(View view) {
        PermissionAgent.with(view)
                .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_CONTACTS)
                .onGranted(new Action() {
                    @Override
                    public void onAction(String[] permissions) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (String per : permissions) {
                            stringBuilder.append(per).append(",");
                        }
                        Log.d(TAG, "onGranted() called with: permissions = [" + stringBuilder.toString() + "]");
                    }
                })
                .onDenied(new Action() {
                    @Override
                    public void onAction(String[] permissions) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (String per : permissions) {
                            stringBuilder.append(per).append(",");
                        }
                        Log.d(TAG, "onDenied() called with: permissions = [" + stringBuilder.toString() + "]");
                    }
                })
                .onRationale(new Rationale() {
                    @Override
                    public void onShowRationale(String[] permissions, AgentExecutor repeater) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (String per : permissions) {
                            stringBuilder.append(per).append(",");
                        }
                        Log.d(TAG, "onShowRationale() called with: permissions = [" + stringBuilder.toString() + "], repeater = [" + repeater + "]");
                        repeater.execute();
                    }
                })
                .apply();
    }

    public void requestLocation(View view) {
        PermissionAgent.with(view)
                .request(Manifest.permission.ACCESS_FINE_LOCATION)
                .onDenied(new Action() {
                    @Override
                    public void onAction(String[] permissions) {

                    }
                })
                .onGranted(new Action() {
                    @Override
                    public void onAction(String[] permissions) {

                    }
                })
                .onRationale(new Rationale() {
                    @Override
                    public void onShowRationale(String[] permissions, AgentExecutor repeater) {

                    }
                })
                .apply();
    }
}
