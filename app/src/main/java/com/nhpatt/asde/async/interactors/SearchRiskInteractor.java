package com.nhpatt.asde.async.interactors;

import com.nhpatt.asde.async.EventBusUtil;
import com.nhpatt.asde.async.RetrofitAPI;
import com.nhpatt.asde.async.services.GitHubService;
import com.nhpatt.asde.models.Contributor;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

/**
 * @author Javier Gamarra
 */
public class SearchRiskInteractor extends AbstractInteractor {
    @Override
    public void runOnBackground() throws IOException {

        GitHubService github = RetrofitAPI.getRetrofit().create(GitHubService.class);

        Call<List<Contributor>> call = github.contributors("nhpatt", "javascript-learning-group");

        List<Contributor> contributors = call.execute().body();

        EventBusUtil.post(String.valueOf(contributors.get(0)));


    }
}