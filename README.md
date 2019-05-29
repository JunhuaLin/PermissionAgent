# Prermission
链式编程动态申请权限


```java
//Application中初始化
PermissionAgent.setDebug(BuildConfig.DEBUG);
PermissionAgent.getInstance().init(this);

//动态权限
PermissionAgent.getInstance()
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


//特殊权限
PermissionAgent.getInstance()
                .request(SpecialPermission.SYSTEM_ALERT_WINDOW)
                .onGranted(new OnGrantedCallback() {
                    @Override
                    public void onGranted(String[] permissions) {
                        Log.d(TAG, "onGranted() called with: permissions = [" + permissions + "]");
                    }
                })
                .onDenied(new OnDeniedCallback() {
                    @Override
                    public void onDenied(String[] permissions) {
                        Log.d(TAG, "onDenied() called with: permissions = [" + permissions + "]");
                    }
                })
                .apply();

```
