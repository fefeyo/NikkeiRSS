package com.fefeyo.nikkeirss;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Sam on 2015/02/20.
 */
public class RssParserTask extends AsyncTask<String,Integer, RssListAdapter>{

    private Context context;
    private RssListAdapter adapter;
    private ProgressDialog pd;
    private ListView list;
    private static int count = 0;

    //コンストラクタ
    public RssParserTask(Context context, RssListAdapter adapter,ListView list){
        this.context = context;
        this.adapter = adapter;
        this.list = list;
    }

    //タスクを実行した直後にコールされる
    @Override
    protected void onPreExecute(){
        pd = new ProgressDialog(context);
        Log.v("log",context+"");
        pd.setMessage("Now Loading...");
        if(count == 0) {
            pd.show();
            count++;
        }
    }

    //バックグラウンドにおける処理を担う。タスク実行時に渡された値を引数とする
    @Override
    protected RssListAdapter doInBackground(String... params) {
        RssListAdapter result = null;
        try{
            // HTTP経由でアクセスし、InputStreamを取得する
            URL url = new URL(params[0]);
            InputStream is = url.openConnection().getInputStream();
            result = parseXML(is);
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    //メインスレッド上で実行される
    protected void onPostExecute(RssListAdapter adapter){
        pd.dismiss();
        //ここに setAdapter
        list.setAdapter(adapter);
    }

    //XMLをパースする
    public RssListAdapter parseXML(InputStream is)throws IOException,XmlPullParserException {
        XmlPullParser parser = Xml.newPullParser();
        try{
            parser.setInput(is,null);
            int eventType = parser.getEventType();
            Item currentItem = null;
            while(eventType != XmlPullParser.END_DOCUMENT){
                String tag = null;
                switch (eventType){
                    case XmlPullParser.START_TAG:
                        tag = parser.getName();

                        if(tag.equals("item")){
                            currentItem = new Item();
                        }else if(currentItem != null){
                            if(tag.equals("title")) {
                                currentItem.setTitle(parser.nextText());
                            }else if(tag.equals("date")){
                                currentItem.setDate(parser.nextText());
                            }else if(tag.equals("link")){
                                currentItem.setLink(parser.nextText());
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        tag = parser.getName();
                        if(tag.equals("item")){
                            adapter.add(currentItem);
                        }
                        break;
                }
                eventType = parser.next();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return adapter;
    }

}
