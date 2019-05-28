# Prermission
链式编程动态申请权限


```java
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
                        //继续执行请求
                        repeater.execute();
                    }
                })
                .apply();
```
