/*
 * Copyright © Zhenjie Yan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.junhua.android.permission.dangerous.checker;

import android.os.Environment;
import android.text.TextUtils;

import java.io.File;

import cn.junhua.android.permission.agent.check.PermissionTester;

/**
 * 执行权限操作接口<br/>
 * <p>
 * 参考：
 * https://github.com/yanzhenjie/AndPermission/tree/master/support/src/main/java/com/yanzhenjie/permission/checker
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/6/6 10:37
 */
class StorageWriteTester implements PermissionTester {

    StorageWriteTester() {
    }

    @Override
    public boolean test() throws Throwable {
        if (!TextUtils.equals(Environment.MEDIA_MOUNTED, Environment.getExternalStorageState())) return true;

        File directory = Environment.getExternalStorageDirectory();
        if (!directory.exists()) return true;

        File parent = new File(directory, "Android");
        if (parent.exists() && parent.isFile()) {
            if (!parent.delete()) return false;
        }
        if (!parent.exists()) {
            if (!parent.mkdirs()) return false;
        }
        File file = new File(parent, "ANDROID.PERMISSION.TEST");
        if (file.exists()) {
            return file.delete();
        } else {
            return file.createNewFile();
        }
    }
}