package com.rdc.gdut_activity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rdc.gdut_activity.R;
import com.rdc.gdut_activity.base.BaseRecyclerViewAdapter;
import com.rdc.gdut_activity.bean.Student;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.hdodenhof.circleimageview.CircleImageView;

public class EnrollStatusAdapter extends BaseRecyclerViewAdapter<Student> {

    private Context mContext;

    public EnrollStatusAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_enroll_msg, parent, false);
        return new ParticipantViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ParticipantViewHolder) holder).bindView(mDataList.get(position));
    }

    class ParticipantViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_user_icon)
        CircleImageView mIvUserIcon;
        @InjectView(R.id.tv_user_nick_name)
        TextView mTvUserNickName;
        @InjectView(R.id.tv_info)
        TextView mTvInfo;
        @InjectView(R.id.tv_contact_way)
        TextView mTvContactWay;


        public ParticipantViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
//            (R.layout.item_list_enroll_msg)
        }

        public void bindView(Student student) {
            mTvUserNickName.setText(student.getUsername());
            if (student.getMobilePhoneNumberVerified() != null && student.getMobilePhoneNumberVerified()) {
                mTvContactWay.setText(student.getMobilePhoneNumber());
            } else {
                mTvContactWay.setText("该用户尚未填写联系方式");
            }
//            tools:text="自动化学院\n15 级物联网工程 4 班"
            mTvInfo.setText(student.getCollege() + "\n" + student.getGrade() + " " + student.getMajor());
            Picasso.with(mContext)
                    .load(student.getIcon())
                    .placeholder(R.drawable.ueser_icon)
                    .into(mIvUserIcon);
        }
    }
}
