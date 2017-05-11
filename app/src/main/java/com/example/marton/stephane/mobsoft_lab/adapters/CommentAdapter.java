package com.example.marton.stephane.mobsoft_lab.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.marton.stephane.mobsoft_lab.R;
import com.example.marton.stephane.mobsoft_lab.interactor.comment.CommentsInteractor;
import com.example.marton.stephane.mobsoft_lab.models.Comment;
import com.example.marton.stephane.mobsoft_lab.models.SimilarAnime;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by Marton on 2016.11.29..
 */

public class CommentAdapter extends BaseAdapter {
    private ArrayList<Comment> items;

    public CommentAdapter(ArrayList<Comment> items) {
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Inject
    CommentsInteractor commentsInteractor;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.comment_item, null);
            holder = new ViewHolder();
            holder.usernameText = (TextView) convertView.findViewById(R.id.username);
            holder.commentText = (EditText) convertView.findViewById(R.id.comment);
            holder.editButton = (Button) convertView.findViewById(R.id.editButton);
            holder.deleteButton = (Button) convertView.findViewById(R.id.deleteButton);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.usernameText.setText(((Comment) getItem(position)).getUserName());
        holder.commentText.setText(((Comment) getItem(position)).getComment());
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Comment> al = new ArrayList<Comment>();
                al.add((Comment) getItem(position));
                commentsInteractor.updateComments(al);
            }
        });
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentsInteractor.removeComments((Comment) getItem(position));
            }
        });

        return convertView;
    }

    /* Helper class for managing items in the list view
     */
    private static class ViewHolder {
        TextView usernameText;
        EditText commentText;
        Button editButton;
        Button deleteButton;
    }
}
