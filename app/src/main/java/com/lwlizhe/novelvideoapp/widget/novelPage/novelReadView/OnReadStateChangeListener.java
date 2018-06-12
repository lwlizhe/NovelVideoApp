/**
 * Copyright 2016 JustWayward Team
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lwlizhe.novelvideoapp.widget.novelPage.novelReadView;

/**
 * @author yuyh.
 * @date 2016/9/21.
 */
public interface OnReadStateChangeListener {

    void onChapterChanged(int chapter, int volume);

    void onPageChanged(int chapter, int volume, int page);

    void onVolumeChanged(int volume);

    void onLoadChapterFailure(int chapter, int volume);

    void onCenterClick();

    void onFlip();
}
