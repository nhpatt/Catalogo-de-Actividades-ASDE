package com.nhpatt.asde.mvp.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nhpatt.asde.R;
import com.nhpatt.asde.models.Event;
import com.nhpatt.asde.mvp.activities.EventListener;
import com.nhpatt.asde.mvp.activities.EventsAdapter;
import com.nhpatt.asde.mvp.activities.MainActivity;
import com.nhpatt.asde.mvp.presenters.EventListPresenter;

import java.util.List;

/**
 * @author Hugo Nebreda
 */
public class EventListFragment extends AbstractFragment<EventListPresenter>
        implements EventListPresenter.EventListView, EventListener {

    private RecyclerView eventRecyclerView;
    private View progressList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.event_list, container, false);
        eventRecyclerView = (RecyclerView) view.findViewById(R.id.event_recycler);
        progressList = view.findViewById(R.id.progress_list);
        return view;
    }

    @Override
    protected EventListPresenter createPresenter() {
        return new EventListPresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        progressList.setVisibility(View.VISIBLE);
        getPresenter().searchEventList();
    }

    @Override
    public void onSuccess(final List<Event> events) {
        super.onSuccess();
        progressList.setVisibility(View.GONE);
        eventRecyclerView.setAdapter(new EventsAdapter(events, this));
    }

    @Override
    public void onError(String error) {
        super.onError(error);
        progressList.setVisibility(View.GONE);
    }

    @Override
    public void click(Event event) {
        ((MainActivity) getActivity()).goToDetail(event);
    }

}

