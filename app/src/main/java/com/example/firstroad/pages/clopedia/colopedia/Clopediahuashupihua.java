package com.example.firstroad.pages.clopedia.colopedia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.firstroad.MainActivity;
import com.example.firstroad.R;
import com.example.firstroad.classes.Comment;
import com.example.firstroad.pages.clopedia.ColopediaAdapter;

import java.util.ArrayList;
import java.util.List;

/**非遗百科
 * 桦树皮画
 */
public class Clopediahuashupihua extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private List<Comment> mComment;
    private TextView commentSubmit;
    private ColopediaAdapter adapter;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clopedia_huashupihua);

        iniViews();
        iniList();
        iniRecyclerView();

    }

    private void iniViews(){
        mComment = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.colopedia_recycler);
        commentSubmit = (TextView) findViewById(R.id.comment_submit);
        editText = (EditText) findViewById(R.id.colopedia_comment_content);

        commentSubmit.setOnClickListener(this);
    }

    private void iniRecyclerView(){
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new ColopediaAdapter(mComment);
        recyclerView.setAdapter(adapter);
    }

    private void iniList(){
        mComment = MainActivity.comments;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.comment_submit:
                String content = editText.getText().toString();
                mComment.add(new Comment(R.drawable.baikepinglun_1_head, "小道夫",
                    content, 0));
                MainActivity.comments = mComment;
                adapter.notifyDataSetChanged();
                break;

            default:

                break;
        }

    }
}
