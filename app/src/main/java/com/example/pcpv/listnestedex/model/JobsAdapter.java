package com.example.pcpv.listnestedex.model;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.pcpv.listnestedex.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.JobsViewHolder> {
    private Context context;
    private List<Jobs> jobsList;

    @SuppressLint("CheckResult")
    public JobsAdapter(final Context context, List<Jobs> jobsList) {
        this.context = context;
        this.jobsList = jobsList;
    }

    @NonNull
    @Override
    public JobsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.job_list_item_layout, parent, false);
        return new JobsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobsViewHolder holder, int position) {
        holder.bindView(this.jobsList.get(position));
    }

    @Override
    public int getItemCount() {
        return jobsList.size();
    }

    class JobsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.job_list_item_ic_title)
        protected ImageView icTitle;
        @BindView(R.id.job_list_item_txt_title)
        protected TextView txtTitle;
        @BindView(R.id.job_list_item_recycler_view)
        protected RecyclerView recyclerView;
        private JobAdapter adapter;
        private Jobs jobs;

        JobsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @SuppressLint("CheckResult")
        void bindView(final Jobs jobs) {
            this.jobs = jobs;
            if (jobs.isExpanded()) {
                this.adapter = new JobAdapter(context, jobs.getJobs());
                this.recyclerView.setLayoutManager(new GridLayoutManager(context, 1));
                this.recyclerView.setHasFixedSize(true);
                this.recyclerView.setAdapter(adapter);
            }
            this.txtTitle.setText(jobs.getTitle());

            icTitle.setImageResource(jobs.isExpanded()
                    ?R.mipmap.ic_up
                    :  R.mipmap.ic_down);

            YoYo.with(jobs.isExpanded()
                    ? Techniques.SlideInDown
                    : Techniques.SlideOutUp)
                    .duration(700)
                    .onStart(new YoYo.AnimatorCallback() {
                        @Override
                        public void call(Animator animator) {
                            if (jobs.isExpanded()) {
                                recyclerView.setVisibility(View.VISIBLE);
                            }
                        }
                    })
                    .onEnd(new YoYo.AnimatorCallback() {
                        @Override
                        public void call(Animator animator) {
                            if (!jobs.isExpanded()) {
                                recyclerView.setVisibility(View.GONE);
                            }
                        }
                    })
                    .playOn(recyclerView);
        }

        @OnClick(R.id.job_list_item_linear_layout)
        protected void onClickTitle() {
            jobs.setExpanded(!jobs.isExpanded());

            YoYo.with(Techniques.RotateIn)
                    .duration(700)
                    .onEnd(new YoYo.AnimatorCallback() {
                        @Override
                        public void call(Animator animator) {
                            icTitle.setImageResource(jobs.isExpanded()
                                    ?R.mipmap.ic_up
                                    :  R.mipmap.ic_down);
                        }
                    })
                    .playOn(icTitle);

            notifyItemChanged(getAdapterPosition());
        }

    }
}

