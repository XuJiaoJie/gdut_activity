package com.rdc.gdut_activity.adapter;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import com.rdc.gdut_activity.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ThatNight on 2017.5.16.
 */

public class SignUpAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mFormList;
    private Map<String, String> mFormMap;

    public SignUpAdapter(Context context, List<String> formList) {
        mContext = context;
        mFormList = formList;
        mFormMap = new HashMap<>();
    }

    @Override
    public int getCount() {
        return mFormList.size();
    }

    @Override
    public Object getItem(int i) {
        return mFormList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        EdittextHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_list_sign_up, null);
            holder = new EdittextHolder();
            holder.mEtLayout = (TextInputLayout) view.findViewById(R.id.et_item_sign_up);
            view.setTag(holder);
        } else {
            holder = (EdittextHolder) view.getTag();
        }
        holder.mEtLayout.setHint(mFormList.get(i));
        holder.mEtLayout.setTag(i);
        //// TODO: 2017.5.16 传输可输入的参数
        // holder.mEtLayout.setInputType();
        holder.mEditText = holder.mEtLayout.getEditText();
        holder.mEditText.addTextChangedListener(new SignUpTextWatcher(holder));
        return view;
    }

    class EdittextHolder {
        TextInputLayout mEtLayout;
        EditText mEditText;
    }

    class SignUpTextWatcher implements TextWatcher {
        private EdittextHolder mHolder;

        public SignUpTextWatcher(EdittextHolder holder) {
            mHolder = holder;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            mHolder.mEtLayout.setError("有错误!");
            mHolder.mEtLayout.setErrorEnabled(true);
        }

        @Override
        public void afterTextChanged(Editable editable) {
            int position = (Integer)mHolder.mEtLayout.getTag();
            mFormMap.put(mFormList.get(position), editable.toString());
        }
    }

}
