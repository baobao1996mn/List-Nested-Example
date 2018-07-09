package com.example.pcpv.listnestedex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.pcpv.listnestedex.model.IJobView;
import com.example.pcpv.listnestedex.model.Job;
import com.example.pcpv.listnestedex.model.Jobs;
import com.example.pcpv.listnestedex.model.JobsAdapter;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements IJobView {
    @BindView(R.id.activity_main_recycler_view)
    protected RecyclerView recyclerView;

    private JobsAdapter adapter;
    private IGitHub service;
    private List<Jobs> jobsList;
    private String[] keys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("https://jobs.github.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(IGitHub.class);

        jobsList = new ArrayList<>();
        keys = new String[]{"React Native",
                "Java", "Swift", "Angular", "Python", "Ruby", "Dart", "Kotlin"};

        for (String key : keys) {
            final String _key = key;
            service.getJobs(key)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .doOnNext(new Consumer<List<Job>>() {
                        @Override
                        public void accept(List<Job> jobList) {
                            Jobs jobs = new Jobs(_key, jobList);
                            getJobsSuccessfully(jobs);
                        }
                    })
                    .doOnError(new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) {
                            Jobs jobs = new Jobs(_key, new ArrayList<Job>());
                            getJobsSuccessfully(jobs);
                            getJobsFailure(throwable);
                        }
                    }).subscribe();
        }

    }

    @Override
    public void getJobsSuccessfully(Jobs jobs) {
        jobsList.add(jobs);
        if (jobsList.size() == keys.length) {
            this.adapter = new JobsAdapter(this, jobsList);
            this.recyclerView.setHasFixedSize(true);
            this.recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
            this.recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void getJobsFailure(Throwable throwable) {
        Toast.makeText(getBaseContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
