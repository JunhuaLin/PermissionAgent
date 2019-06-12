
# Permission Agent

[![GitHub license](https://img.shields.io/github/license/JunhuaLin/PermissionAgent.svg?style=plastic)](https://github.com/JunhuaLin/PermissionAgent/blob/master/LICENSE)
[![latest release](https://img.shields.io/github/release/JunhuaLin/PermissionAgent.svg?style=plastic)](https://github.com/JunhuaLin/PermissionAgent/releases)
[![pull requests welcome](https://img.shields.io/badge/pull%20requests-welcome-brightgreen.svg?style=plastic)](https://github.com/JunhuaLin/PermissionAgent/pulls)
[![issues welcome](https://img.shields.io/badge/issues-welcome-brightgreen.svg?style=plastic)](https://github.com/JunhuaLin/PermissionAgent/issues)


一次初始化处处可用的链式编程动态权限请求库

- 链式编程
- 运行时权限申请
- 运行时权限组申请
- 运行时权限和权限组混合申请
- 支持多个权限并行申请
- 支持多个权限串行申请
- 支持特殊权限申请，如REQUEST_INSTALL_PACKAGES，SYSTEM_ALERT_WINDOW，ACCESS_NOTIFICATION_POLICY，WRITE_SETTINGS
- 最小支持android 14
- 多ROM多版本适配

### 使用

#### 添加依赖
```groovy
implementation 'cn.junhua.android:permission-agent:<latest-release>'
```


#### 初始化
```java
//Application的onCreate中初始化
PermissionAgent.setDebug(BuildConfig.DEBUG);//开启debug
PermissionAgent.getInstance().init(this);
```

#### 单个权限申请

```java
PermissionAgent.getInstance()
                .request(Manifest.permission.CAMERA)
                //.code(123)//与你自定义code冲突时可以设置，一般不用自己设置
                .onGranted(new OnGrantedCallback<List<String>>() {
                    @Override
                    public void onGranted(List<String> permissions) {
                        //成功
                    }
                })
                .onDenied(new OnDeniedCallback<List<String>>() {
                    @Override
                    public void onDenied(List<String> permissions) {
                        //拒绝
                    }
                })
                .onRationale(new OnRationaleCallback<List<String>>() {
                    @Override
                    public void onRationale(List<String> permissions, AgentExecutor executor) {
                        //提示用户
                        executor.execute();//继续
                        executor.cancel();//取消
                        //do something
                    }
                })
                .apply();
```
#### 并行请求多个权限

并行请求时，结果会同时返回。当用户取消单个权限时，其他权限也不会请求并回调拒绝。
```java
PermissionAgent.getInstance()
                .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_CONTACTS)
                //.code(123)//与你自定义code冲突时可以设置，一般不用自己设置
                .onGranted(new OnGrantedCallback<List<String>>() {
                    @Override
                    public void onGranted(List<String> permissions) {
                         //成功
                    }
                })
                .onDenied(new OnDeniedCallback<List<String>>() {
                    @Override
                    public void onDenied(List<String> permissions) {
                       //拒绝
                    }
                })
                .onRationale(new OnRationaleCallback<List<String>>() {
                    @Override
                    public void onRationale(List<String> permissions, AgentExecutor executor) {
                       //提示用户
                       executor.execute();//继续
                       executor.cancel();//取消
                       //do something
                    }
                })
                .apply();
```

#### 串行请求多个权限

串行请求时，权限会顺序请求，当前一个请求处理完成后才会请求后一个权限。
注意：``AgentExecutor``必须回调``execute()``或者``cancel()``之一，才能执行后续请求。
```java
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
```

#### 特殊权限

使用``SpecialPermission``枚举选择需要申请的特殊权限，其他操作不变
```java
PermissionAgent.getInstance()
                .request(SpecialPermission.REQUEST_INSTALL_PACKAGES)
                //.code(123)//与你自定义code冲突时可以设置，一般不用自己设置
                .onGranted(new OnGrantedCallback<SpecialPermission>() {
                    @Override
                    public void onGranted(SpecialPermission permissions) {
                        //成功
                    }
                })
                .onDenied(new OnDeniedCallback<SpecialPermission>() {
                    @Override
                    public void onDenied(SpecialPermission permissions) {
                         //拒绝
                    }
                })
                .onRationale(new OnRationaleCallback<List<String>>() {
                    @Override
                    public void onRationale(List<String> permissions, AgentExecutor executor) {
                       //提示用户
                       executor.execute();//继续
                       executor.cancel();//取消
                       //do something
                    }
                })
                .apply();
```

#### 检测永久拒绝权限

```java
//当权限被拒绝时候调用此方法，检测权限是否永远被拒绝
PermissionAgent.getInstance().hasAlwaysDeniedPermission(Manifest.permission.ACCESS_FINE_LOCATION)
```

#### 检测权限

权限检测的工具方法，供单独使用时调用。

```java
//检测单个权限
PermissionAgent.getInstance().checkPermission(Manifest.permission.ACCESS_FINE_LOCATION)

//检测多个权限，如果存在权限没有授予就返回false
PermissionAgent.getInstance().checkPermission(Manifest.permission.CALL_PHONE,
                        Manifest.permission.CAMERA)

//检测特殊权限，通过SpecialPermission选择权限
PermissionAgent.getInstance().checkPermission(SpecialPermission.ACCESS_NOTIFICATION_POLICY)
```

#### Debug日志输出

log标签分类两级：

- 一级:TAG1=PermissionAgent可以过滤出本库所有的log日志，通过标签过滤可得。
- 二级:TAG2=xxx.class.getSimpleName()可以过滤出xxx类的log日志，通过内容过滤可得。

log格式：D/{TAG1}: {TAG2}/{log msg}

### 感谢

感谢以下库或文章提供的思路和适配方案：

- [https://github.com/yanzhenjie/AndPermission](https://github.com/yanzhenjie/AndPermission)
- [https://github.com/zhaozepeng/FloatWindowPermission](https://github.com/zhaozepeng/FloatWindowPermission)
