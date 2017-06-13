package id.sch.smktelkom_mlg.privateassignment.xirpl529.taprivate;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl529.taprivate.adapter.SourceAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl529.taprivate.model.Result;
import id.sch.smktelkom_mlg.privateassignment.xirpl529.taprivate.model.SourcesResponse;
import id.sch.smktelkom_mlg.privateassignment.xirpl529.taprivate.service.GsonGetRequest;
import id.sch.smktelkom_mlg.privateassignment.xirpl529.taprivate.service.VolleySingleton;


/**
 * A simple {@link Fragment} subclass.
 */
public class PopFragment extends Fragment {

    ArrayList<Result> mList = new ArrayList<>();
    SourceAdapter mAdapter;

    public PopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pop, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.pop_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mAdapter = new SourceAdapter(this.getActivity(), mList);
        recyclerView.setAdapter(mAdapter);

        downloadDataPopular();
    }

    private void downloadDataPopular() {
        String url = "https://api.themoviedb.org/3/movie/popular?api_key=68004564c00d30019ea0a99012a0cfe4";

        GsonGetRequest<SourcesResponse> myRequest = new GsonGetRequest<SourcesResponse>
                (url, SourcesResponse.class, null, new Response.Listener<SourcesResponse>() {

                    @Override
                    public void onResponse(SourcesResponse response) {
                        Log.d("FLOW", "onResponse: " + (new Gson().toJson(response)));


                        mList.addAll(response.results);
                        mAdapter.notifyDataSetChanged();

                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("FLOW", "onErrorResponse: ", error);
                    }
                });
        VolleySingleton.getInstance(this.getActivity()).addToRequestQueue(myRequest);
    }
}