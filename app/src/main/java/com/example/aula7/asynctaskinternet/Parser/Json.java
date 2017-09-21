package com.example.aula7.asynctaskinternet.Parser;

import com.example.aula7.asynctaskinternet.Models.Comment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aula7 on 21/09/17.
 */

public class Json {
    public static List<Comment> getData(String content) throws JSONException {
        JSONArray jsonArray = new JSONArray(content);
        List<Comment> commentList  =new ArrayList<>();

        for (int i=0; i < jsonArray.length(); i++){
            JSONObject item = jsonArray.getJSONObject(i);

            Comment comment = new Comment();
            comment.setId(item.getInt("id"));
            comment.setEmail(item.getString("email"));
            comment.setBody(item.getString("body"));

            commentList.add(comment);
        }
        return commentList;
    }
}
