package com.suelengc.bookplan.service.chapter;

import com.suelengc.bookplan.model.Chapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChapterConverter {

    public List<Chapter> fromJsonToList(String inputJson, String isbn) {
        List<Chapter> chapters = new ArrayList<Chapter>();

        try {
            JSONObject jsonObject = new JSONObject(inputJson);

            if (jsonObject.has("ISBN:" + isbn)) {
                JSONObject jsonISBN = jsonObject.getJSONObject("ISBN:" + isbn);

                if (jsonISBN.has("details")) {
                    JSONObject jsonDetails = jsonISBN.getJSONObject("details");

                    if (jsonDetails.has("table_of_contents")) {
                        JSONArray jsonTableOfContents = jsonDetails.getJSONArray("table_of_contents");

                        for(int i = 0; i < jsonTableOfContents.length(); i++) {
                            JSONObject jsonChapter = jsonTableOfContents.getJSONObject(i);

                            Chapter chapter = getChapterFromJsonObject(jsonChapter);
                            chapters.add(chapter);
                        }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return chapters;
    }

    private Chapter getChapterFromJsonObject(JSONObject jsonChapter) throws JSONException {
        String chapterTitle = "";
        if (jsonChapter.has("title")) {
            chapterTitle = jsonChapter.getString("title");
        }

        String chapterLabel = "";
        if (jsonChapter.has("label")) {
            chapterLabel = jsonChapter.getString("label");
        }

        Integer chapterFirstPage= 0;
        if (jsonChapter.has("pagenum")) {
            chapterFirstPage = jsonChapter.getInt("pagenum");
        }

        return new Chapter(chapterTitle, chapterLabel, chapterFirstPage);
    }
}
