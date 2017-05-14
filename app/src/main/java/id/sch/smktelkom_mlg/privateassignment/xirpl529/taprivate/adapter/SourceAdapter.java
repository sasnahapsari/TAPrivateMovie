package id.sch.smktelkom_mlg.privateassignment.xirpl529.taprivate.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl529.taprivate.R;
import id.sch.smktelkom_mlg.privateassignment.xirpl529.taprivate.ScrollingActivity;
import id.sch.smktelkom_mlg.privateassignment.xirpl529.taprivate.model.Result;


public class SourceAdapter extends RecyclerView.Adapter<SourceAdapter.ViewHolder> {
    ArrayList<Result> list;
    SourceAdapter.ISourceAdapter mISourceAdapter;
    Context context;

    public SourceAdapter(Context context, ArrayList<Result> list) {
        this.list = list;
        this.context = context;
        mISourceAdapter = (SourceAdapter.ISourceAdapter) context;
    }

    @Override
    public SourceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.source_list, parent, false);
        SourceAdapter.ViewHolder vh = new SourceAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(SourceAdapter.ViewHolder holder, int position) {
        final Result results = list.get(position);
        holder.tvName.setText(results.title);
        holder.tvDesc.setText(results.overview);
        Glide.with(context)
                .load("http://image.tmdb.org/t/p/w500" + results.poster_path)
                .into(holder.gambar);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ScrollingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("movie_title", results.title);
                intent.putExtra("poster_path", results.backdrop_path);
                intent.putExtra("description", results.overview);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        return 0;
    }

    public interface ISourceAdapter {
        void showArticles(String title, String overview);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView gambar;
        TextView tvName;
        TextView tvDesc;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            gambar = (ImageView) itemView.findViewById(R.id.imageViewPoster);
            tvName = (TextView) itemView.findViewById(R.id.textViewName);
            tvDesc = (TextView) itemView.findViewById(R.id.textViewDesc);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
        }
    }
}
