package com.example.aula7.asynctaskinternet.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aula7.asynctaskinternet.Models.Comment;
import com.example.aula7.asynctaskinternet.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aula7 on 21/09/17.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    List<Comment> commentList = new ArrayList<>();
    Context context;

    public CommentAdapter(List<Comment> commentList, Context context) {
        this.commentList = commentList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //configurar el viewholder


        //obtener el archivo item.xml
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent,false);

        //pasra los componentes del archivo  item.xml para hacer referencvia de ellos
        ViewHolder viewHolder = new ViewHolder(item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Encargado de trabajar los componentes del item.xml
        holder.textViewemail.setText(commentList.get(position).getEmail());
        holder.textViewbody.setText(commentList.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    // clase que pwermite ghacer referencia a los componentes (de item.xml)
    public class ViewHolder   extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textViewemail;
        TextView textViewbody;

        public ViewHolder(View item) {
            super(item);
            imageView= (ImageView) item.findViewById(R.id.id_img_item);
            textViewemail= (TextView) item.findViewById(R.id.id_tv_email);
            textViewbody= (TextView) item.findViewById(R.id.id_tv_body);
        }


    }
}
