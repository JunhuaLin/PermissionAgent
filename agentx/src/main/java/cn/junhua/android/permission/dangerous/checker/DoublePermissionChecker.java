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

import android.content.Context;

import java.util.List;

import cn.junhua.android.permission.agent.check.PermissionChecker;

/**
 * 执行权限操作接口<br/>
 * <p>
 * 参考：
 * https://github.com/yanzhenjie/AndPermission/tree/master/support/src/main/java/com/yanzhenjie/permission/checker
 *
 * @author junhua.lin@jinfuzi.com<br/>
 * CREATED 2019/6/6 10:37
 */
public final class DoublePermissionChecker implements PermissionChecker {

    private static final PermissionChecker STANDARD_CHECKER = new StandardPermissionChecker();
    private static final PermissionChecker STRICT_CHECKER = new StrictPermissionChecker();

    @Override
    public boolean hasPermissions(Context context, String... permissions) {
        return STRICT_CHECKER.hasPermissions(context, permissions) &&
                STANDARD_CHECKER.hasPermissions(context, permissions);
    }

    @Override
    public boolean hasPermissions(Context context, List<String> permissions) {
        return STRICT_CHECKER.hasPermissions(context, permissions) &&
                STANDARD_CHECKER.hasPermissions(context, permissions);
    }
}