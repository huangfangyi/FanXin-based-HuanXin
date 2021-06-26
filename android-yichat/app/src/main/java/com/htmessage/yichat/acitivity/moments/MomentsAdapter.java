package com.htmessage.yichat.acitivity.moments;

import android.app.Activity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.htmessage.update.data.UserManager;
import com.htmessage.yichat.R;
import com.htmessage.yichat.acitivity.moments.widget.MomentsItemView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MomentsAdapter extends BaseAdapter {

    private Activity context;
    private List<JSONObject> jsonArray;
    private RelativeLayout re_unread;
    private ImageView iv_moment_bg;
    private String backgroud = "";

    public MomentsAdapter(Activity context1, List<JSONObject> jsonArray, String backgroud) {
        this.context = context1;
        this.jsonArray = jsonArray;
        this.backgroud = backgroud;
    }

    @Override
    public int getCount() {
        return jsonArray.size() + 1;
    }


    @Override
    public JSONObject getItem(int position) {
        if (position == 0) {
            return null;
        } else {
            return jsonArray.get(position - 1);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }

    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(final int position, View convertView,
                        ViewGroup parent) {
        if (getItemViewType(position) == 0) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_moments_header, null, false);
            ImageView iv_avatar = (ImageView) convertView.findViewById(R.id.iv_avatar);
            re_unread = (RelativeLayout) convertView.findViewById(R.id.re_unread);
            iv_moment_bg = (ImageView) convertView.findViewById(R.id.iv_moment_bg);
            initHeaderView();
            UserManager.get().loadUserAvatar(context, UserManager.get().getMyAvatar(), iv_avatar);
            iv_moment_bg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (adapterListener != null) {
                        adapterListener.onMomentTopBackGroundClock();
                    }
                }
            });
            setBackground(backgroud);
        } else {
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_moments, parent, false);
            }
            ViewHolder holder = (ViewHolder) convertView.getTag();
            if (holder == null) {
                holder = new ViewHolder();
                holder.momentsItemView = (MomentsItemView) convertView.findViewById(R.id.main);
                convertView.setTag(holder);
            }

            JSONObject json = jsonArray.get(position - 1);
            final int realPosition = position - 1;
            holder.momentsItemView.initView(json);
            holder.momentsItemView.setOnMenuClickListener(new MomentsItemView.OnMenuClickListener() {
                @Override
                public void onUserClicked(String userId) {
                    if (adapterListener != null) {
                        adapterListener.onUserClicked(realPosition, userId);
                    }
                }

                @Override
                public void onGoodIconClicked(String aid) {
                    if (adapterListener != null) {
                        adapterListener.onPraised(realPosition, aid);
                    }
                }

                @Override
                public void onCommentIconClicked(String aid) {
                    if (adapterListener != null) {
                        adapterListener.onCommented(realPosition, aid);
                    }
                }

                @Override
                public void onCancelGoodClicked(String aid) {
                    if (adapterListener != null) {
                        adapterListener.onCancelPraised(realPosition, aid);
                    }
                }

                @Override
                public void onCommentDeleteCilcked(String cid) {
                    if (adapterListener != null) {
                        adapterListener.onCommentDelete(realPosition, cid);
                    }
                }

                @Override
                public void onDeleted(String aid) {
                    if (adapterListener != null) {
                        adapterListener.onDeleted(realPosition, aid);
                    }
                }

                @Override
                public void onVideoClick(String object) {
                    if (adapterListener != null) {
                        adapterListener.onVideoViewCLick(realPosition, object);
                    }
                }

                @Override
                public void onSeeClick(String aid, String userIds, String type) {
                    if (adapterListener != null) {
                        adapterListener.onSeeClick(realPosition, aid, userIds, type);
                    }
                }

                @Override
                public void onImageListClicked(int index, ArrayList<String> images) {
                    if (adapterListener != null) {
                        adapterListener.onImageClicked(realPosition, index, images);
                    }
                }
            });
            setItemView(realPosition, (MomentsItemView) convertView);
        }

        return convertView;
    }

    private class ViewHolder {
        MomentsItemView momentsItemView;

    }

    public void setBackground(String url) {
        this.backgroud = url;
        if (iv_moment_bg == null) {
            return;
        }
        if(TextUtils.isEmpty(url)){
            iv_moment_bg.setImageResource(R.drawable.app_logo);
        }

        Glide.with(context).load(url).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.app_logo).error(R.drawable.app_logo).into(iv_moment_bg);


    }

    public void initHeaderView() {
        if (re_unread == null) {
            return;
        }
//        int count = momentsMessageDao.getUnreadMoments();
//        if (count > 0) {
//            MomentsMessage momentsMessage = momentsMessageDao.getLastMomentsMessage();
//            re_unread.setVisibility(View.VISIBLE);
//            re_unread.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    context.startActivity(new Intent(context, MomentsNoticeActivity.class));
//                }
//            });
//            ImageView imageView = (ImageView) re_unread.findViewById(R.id.msg_avatar);
//            TextView tvCount = (TextView) re_unread.findViewById(R.id.tv_count);
//            tvCount.setText(String.format(context.getString(R.string.msg_count), String.valueOf(count)));
//            CommonUtils.loadUserAvatar(context, momentsMessage.getUserAvatar(), imageView);
//        } else {
//            re_unread.setVisibility(View.GONE);
//        }
    }

    public void hideHeaderView() {
        if (re_unread == null) {
            return;
        }
        re_unread.setVisibility(View.GONE);
    }

    private Map<Integer, MomentsItemView> views = new HashMap<>();

    public MomentsItemView getItemView(int position) {
        return views.get(position);
    }

    private void setItemView(int position, MomentsItemView momentsItemView) {
        views.put(position, momentsItemView);
    }





    private AdapterListener adapterListener;

    public void setListener(AdapterListener adapterListener) {
        this.adapterListener = adapterListener;
    }
}
