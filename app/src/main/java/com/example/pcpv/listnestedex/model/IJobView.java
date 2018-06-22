package com.example.pcpv.listnestedex.model;

import java.util.List;

public interface IJobView {
    void getJobsSuccessfully(Jobs jobs);
    void getJobsFailure(Throwable throwable);
}
