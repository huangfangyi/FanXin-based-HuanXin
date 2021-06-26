package com.htmessage.yichat.acitivity.moments;

import java.util.ArrayList;

/**
 * Created by huangfangyi on 2017/7/11.
 * qq 84543217
 */

public interface AdapterListener {
    void onUserClicked(int position, String userId);

    void onPraised(int position, String aid);

    void onCommented(int position, String aid);

    void onCancelPraised(int position, String aid);

    void onCommentDelete(int position, String cid);

    void onDeleted(int position, String aid);
    void onSeeClick(int position, String aid, String userIds, String type);

    void onVideoViewCLick(int position, String videoPath);
    void onImageClicked(int position, int index, ArrayList<String> images);

    void onMomentTopBackGroundClock();
}
