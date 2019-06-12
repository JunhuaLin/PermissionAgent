package cn.junhua.android.permissionagent;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import cn.junhua.android.permission.PermissionAgent;
import cn.junhua.android.permission.agent.AgentExecutor;
import cn.junhua.android.permission.agent.callback.OnDeniedCallback;
import cn.junhua.android.permission.agent.callback.OnGrantedCallback;
import cn.junhua.android.permission.agent.callback.OnRationaleCallback;
import cn.junhua.android.permission.special.SpecialPermission;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_check_permission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testCheckPermission(v);
            }
        });

        findViewById(R.id.tv_cwc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermission(v);
            }
        });
        findViewById(R.id.tv_location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestLocation();
            }
        });
        findViewById(R.id.tv_serial_requests).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestSerialRequests();
            }
        });
        findViewById(R.id.tv_amws).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestOverlay(v);
            }
        });
        findViewById(R.id.tv_ws).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestWriteSettings(v);
            }
        });
        findViewById(R.id.tv_amuas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestInstallApk(v);

            }
        });
        findViewById(R.id.tv_nofity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestNotify(v);
            }
        });
    }

    private void testCheckPermission(View v) {
        String stringBuilder = "ACCESS_FINE_LOCATION:" +
                PermissionAgent.getInstance().checkPermission(Manifest.permission.ACCESS_FINE_LOCATION) +
                "\n" +
                "CALL_PHONE,CAMERA:" +
                PermissionAgent.getInstance().checkPermission(Manifest.permission.CALL_PHONE,
                        Manifest.permission.CAMERA) +
                "\n" +
                "ACCESS_NOTIFICATION_POLICY:" +
                PermissionAgent.getInstance().checkPermission(SpecialPermission.ACCESS_NOTIFICATION_POLICY);
        toast(stringBuilder);
    }

    private void requestNotify(View v) {
        PermissionAgent.getInstance()
                .request(SpecialPermission.ACCESS_NOTIFICATION_POLICY)
                .onGranted(new OnGrantedCallback<SpecialPermission>() {
                    @Override
                    public void onGranted(SpecialPermission permissions) {
                        toast("onGranted() called with: permissions = [" + permissions + "]");
                    }
                })
                .onDenied(new OnDeniedCallback<SpecialPermission>() {
                    @Override
                    public void onDenied(SpecialPermission permissions) {
                        toast("onDenied() called with: permissions = [" + permissions + "]");
                    }
                })
                .apply();
    }


    public void requestPermission(View view) {
        PermissionAgent.getInstance()
                .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_CONTACTS)
                //.code(123)//与你自定义code冲突时可以设置，一般不用自己设置
                .onGranted(new OnGrantedCallback<List<String>>() {
                    @Override
                    public void onGranted(List<String> permissions) {
                        toast("onGranted() called with: permissions = [" + permissions + "]");
                    }
                })
                .onDenied(new OnDeniedCallback<List<String>>() {
                    @Override
                    public void onDenied(List<String> permissions) {
                        toast("onDenied() called with: permissions = [" + permissions + "]");
                    }
                })
                .onRationale(new OnRationaleCallback<List<String>>() {
                    @Override
                    public void onRationale(List<String> permissions, AgentExecutor executor) {
                        executor.execute();
                        toast("onRationale() called with: permissions = [" + permissions + "], executor = [" + executor + "]");
                    }
                })
                .apply();
    }


    public void requestLocation() {
        PermissionAgent.getInstance()
//                .request(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION)
                .request(Manifest.permission_group.LOCATION)
                //.code(123)//与你自定义code冲突时可以设置，一般不用自己设置
                .onGranted(new OnGrantedCallback<List<String>>() {
                    @Override
                    public void onGranted(List<String> permissions) {
                        toast("onGranted() called with: permissions = [" + permissions + "]");
                    }
                })
                .onDenied(new OnDeniedCallback<List<String>>() {
                    @Override
                    public void onDenied(List<String> permissions) {
                        toast("onDenied() called with: permissions = [" + permissions + "]");
                    }
                })
                .onRationale(new OnRationaleCallback<List<String>>() {
                    @Override
                    public void onRationale(List<String> permissions, AgentExecutor executor) {
                        executor.execute();
                        toast("onRationale() called with: permissions = [" + permissions + "], executor = [" + executor + "]");
                    }
                })
                .apply();
    }

    private void requestSerialRequests() {
        PermissionAgent.getInstance()
                .requestEach(Manifest.permission_group.CONTACTS, Manifest.permission.ACCESS_COARSE_LOCATION)
                .onGranted(new OnGrantedCallback<List<String>>() {
                    @Override
                    public void onGranted(List<String> permissions) {
                        toast("onGranted() called with: permissions = [" + permissions + "]");
                        Log.d(TAG, "onGranted() called with: permissions = [" + permissions + "]");
                    }
                })
                .onDenied(new OnDeniedCallback<List<String>>() {
                    @Override
                    public void onDenied(List<String> permissions) {
                        toast("onDenied() called with: permissions = [" + permissions + "]");
                        Log.d(TAG, "onDenied() called with: permissions = [" + permissions + "]");
                    }
                })
                .onRationale(new OnRationaleCallback<List<String>>() {
                    @Override
                    public void onRationale(List<String> permissions, AgentExecutor executor) {
                        executor.execute();
                        toast("onRationale() called with: permissions = [" + permissions + "]");
                        Log.d(TAG, "onRationale() called with: permissions = [" + permissions + "], executor = [" + executor + "]");
                    }
                })
                .apply();
    }

    public void requestOverlay(View view) {
        PermissionAgent.getInstance()
                .request(SpecialPermission.SYSTEM_ALERT_WINDOW)
                .onGranted(new OnGrantedCallback<SpecialPermission>() {
                    @Override
                    public void onGranted(SpecialPermission permissions) {
                        toast("onGranted() called with: permissions = [" + permissions + "]");
                    }
                })
                .onDenied(new OnDeniedCallback<SpecialPermission>() {
                    @Override
                    public void onDenied(SpecialPermission permissions) {
                        toast("onDenied() called with: permissions = [" + permissions + "]");
                    }
                })
                .apply();
    }


    private void requestWriteSettings(View v) {
        PermissionAgent.getInstance()
                .request(SpecialPermission.WRITE_SETTINGS)
                .onGranted(new OnGrantedCallback<SpecialPermission>() {
                    @Override
                    public void onGranted(SpecialPermission permissions) {
                        toast("onGranted() called with: permissions = [" + permissions + "]");
                    }
                })
                .onDenied(new OnDeniedCallback<SpecialPermission>() {
                    @Override
                    public void onDenied(SpecialPermission permissions) {
                        toast("onDenied() called with: permissions = [" + permissions + "]");
                    }
                })
                .apply();
    }


    private void requestInstallApk(View v) {
        PermissionAgent.getInstance()
                .request(SpecialPermission.REQUEST_INSTALL_PACKAGES)
                .code(123)//与你自定义code冲突时可以设置，一般不用自己设置
                .onGranted(new OnGrantedCallback<SpecialPermission>() {
                    @Override
                    public void onGranted(SpecialPermission permissions) {
                        toast("onGranted() called with: permissions = [" + permissions + "]");
                    }
                })
                .onDenied(new OnDeniedCallback<SpecialPermission>() {
                    @Override
                    public void onDenied(SpecialPermission permissions) {
                        toast("onDenied() called with: permissions = [" + permissions + "]");
                    }
                })
                .apply();
    }

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
