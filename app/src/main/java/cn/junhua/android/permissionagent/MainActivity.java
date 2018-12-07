package cn.junhua.android.permissionagent;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import cn.junhua.android.agent.AgentManager;
import cn.junhua.android.agent.OnPermissionDeniedListener;
import cn.junhua.android.agent.OnPermissionGrantedListener;
import cn.junhua.android.agent.OnPermissionRationaleListener;
import cn.junhua.android.agent.PermissionAgent;
import cn.junhua.android.agent.manager.PermissionAgentRepeater;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = AgentManager.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void requestPermission(View view) {
        PermissionAgent.with(view)
                .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_CONTACTS)
                .onGranted(new OnPermissionGrantedListener() {
                    @Override
                    public void onGranted(String[] permissions) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (String per : permissions) {
                            stringBuilder.append(per).append(",");
                        }
                        Log.d(TAG, "onGranted() called with: permissions = [" + stringBuilder.toString() + "]");
                    }
                })
                .onDenied(new OnPermissionDeniedListener() {
                    @Override
                    public void onDenied(String[] permissions) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (String per : permissions) {
                            stringBuilder.append(per).append(",");
                        }
                        Log.d(TAG, "onDenied() called with: permissions = [" + stringBuilder.toString() + "]");
                    }
                })
                .onShouldShowRationale(new OnPermissionRationaleListener() {
                    @Override
                    public void onShowRationale(String[] permissions, PermissionAgentRepeater repeater) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (String per : permissions) {
                            stringBuilder.append(per).append(",");
                        }
                        Log.d(TAG, "onShowRationale() called with: permissions = [" + stringBuilder.toString() + "], repeater = [" + repeater + "]");
                        repeater.repeat();
                    }
                })
                .apply();
    }
}
