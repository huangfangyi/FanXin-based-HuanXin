package com.htmessage.yichat.acitivity.friends.addfriend;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.htmessage.yichat.R;
import com.htmessage.yichat.acitivity.details.UserDetailActivity;

/**
 * 项目名称：HTOpen
 * 类描述：AddFriendNextFragment 描述:
 * 创建人：songlijie
 * 创建时间：2017/7/7 17:33
 * 邮箱:814326663@qq.com
 */
public class AddFriendNextFragment extends Fragment implements AddFriendNextView, TextWatcher, View.OnClickListener {
    private RelativeLayout re_search;
    private TextView tv_search;
    private EditText et_search;
    private AddFriendNextPrestener prestener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View nextView = inflater.inflate(R.layout.activity_addfriends_next, container, false);
        return nextView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
         initView(getView());
         setListener();
    }


    private void initView(View nextView) {
        re_search = (RelativeLayout) nextView.findViewById(R.id.re_search);
        tv_search = (TextView) nextView.findViewById(R.id.tv_search);
        et_search = (EditText) nextView.findViewById(R.id.et_search);
    }

    private void setListener() {
        et_search.addTextChangedListener(this);
        re_search.setOnClickListener(this);
    }



    @Override
    public void onSearchSuccess(JSONObject object) {
        startActivity(new Intent(getActivity(), UserDetailActivity.class).putExtra("type",1).putExtra("data",object.toJSONString()));
     }

    @Override
    public void onSearchFailed(int resId) {
        Toast.makeText(getActivity(),resId,Toast.LENGTH_LONG).show();

    }

    @Override
    public void setPresenter(AddFriendNextPrestener presenter) {
        this.prestener = presenter;
    }

    @Override
    public Context getBaseContext() {
        return getContext();
    }

    @Override
    public Activity getBaseActivity() {
        return getActivity();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() > 0) {
            re_search.setVisibility(View.VISIBLE);
            tv_search.setText(et_search.getText().toString().trim());
        } else {
            re_search.setVisibility(View.GONE);
            tv_search.setText("");
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public void back(View view) {
        getBaseActivity().finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.re_search:
                String content=et_search.getText().toString().trim();
                prestener.searchUser(content);
                break;
        }
    }



}
