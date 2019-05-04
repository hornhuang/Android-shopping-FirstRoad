package com.example.firstroad.pages.clopedia;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.firstroad.R;
import com.example.firstroad.classes.Comment;

import org.w3c.dom.Text;

import java.util.List;

public class ColopediaAdapter extends RecyclerView.Adapter<ColopediaAdapter.ViewHolder>{

    private List<Comment> mList ;

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView authorIcon;
        private TextView  praiseNum;
        private TextView  authorName;
        private TextView  commontContent;
        private ImageView touchGood;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            authorIcon = itemView.findViewById(R.id.comment_author_image);
            authorName = itemView.findViewById(R.id.comment_author_name);
            praiseNum  = itemView.findViewById(R.id.comment_praise_nums);
            commontContent = itemView.findViewById(R.id.comment_content);
            touchGood = itemView.findViewById(R.id.touch_good);
        }
    }

    public ColopediaAdapter(List<Comment> mList){
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.clopedia_list_item_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.authorIcon.setImageResource(mList.get(i).getImageId());
        viewHolder.authorName.setText(mList.get(i).getAuthorId());
        viewHolder.commontContent.setText(mList.get(i).getCommentContent());
        viewHolder.praiseNum.setText(mList.get(i).getPraiseNum()+"");
        if (mList.get(i).getPraiseNum()>0){
            viewHolder.touchGood.setImageResource(R.drawable.goods_purple);
        }else {
            viewHolder.touchGood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!mList.get(i).isPraised()){
                        viewHolder.touchGood.setImageResource(R.drawable.goods_purple);
                        mList.get(i).setPraiseNum(mList.get(i).getPraiseNum()+1);
                        viewHolder.praiseNum.setText(mList.get(i).getPraiseNum()+"");
                        mList.get(i).setPraised(true);
                    }
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

}
