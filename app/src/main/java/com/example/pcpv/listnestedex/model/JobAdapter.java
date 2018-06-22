package com.example.pcpv.listnestedex.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.pcpv.listnestedex.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder> {
    private Context context;
    private List<Job> jobs;

    JobAdapter(Context context, List<Job> jobs) {
        this.context = context;
        this.jobs = jobs;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.job_item_layout, parent, false);
        return new JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {
        holder.bindView(jobs.get(position), position == getItemCount() - 1);
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }


    class JobViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.job_item_divider)
        protected View divider;
        @BindView(R.id.job_item_img_logo)
        protected ImageView imgLogo;
        @BindView(R.id.job_item_txt_company)
        protected TextView txtCompany;
        @BindView(R.id.job_item_txt_title)
        protected TextView txtTitle;
        @BindView(R.id.job_item_txt_location)
        protected TextView txtLocation;
        @BindView(R.id.job_item_txt_type)
        protected TextView txtType;
        @BindView(R.id.job_item_img_checked)
        protected ImageView imgChecked;

        private Job job;
        JobViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @SuppressLint("CheckResult")
        void bindView(Job job, boolean isLast) {
            this.job = job;
            this.divider.setVisibility(isLast ? View.GONE : View.VISIBLE);
            this.txtCompany.setText(job.getCompany());
            this.txtLocation.setText(job.getLocation());
            this.txtTitle.setText(job.getTitle());
            this.txtType.setText(job.getType());
            Glide.with(context)
                    .setDefaultRequestOptions(new RequestOptions())
                    .load(job.getLogo())
                    .transition(withCrossFade())
                    .into(this.imgLogo);
            imgChecked.setImageDrawable(context
                    .getResources()
                    .getDrawable(job.isChecked()
                            ? R.drawable.ic_checked
                            : R.drawable.ic_unchecked
                    )
            );
        }

        @OnClick(R.id.job_item_linear_layout)
        protected  void onClickItem(){
            job.setChecked(!job.isChecked());
            imgChecked.setImageDrawable(context
                    .getResources()
                    .getDrawable(job.isChecked()
                            ? R.drawable.ic_checked
                            : R.drawable.ic_unchecked
                    )
            );
        }
    }
}
