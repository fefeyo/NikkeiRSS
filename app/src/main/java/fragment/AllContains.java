package fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.fefeyo.nikkeirss.Item;
import com.fefeyo.nikkeirss.R;
import com.fefeyo.nikkeirss.RssListAdapter;
import com.fefeyo.nikkeirss.RssParserTask;
import com.fefeyo.nikkeirss.URLs;
import com.fefeyo.nikkeirss.ViewContainsActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllContains extends Fragment {
    private RssListAdapter adapter;
    private ArrayList<Item> arr;
    private static ListView list;


    public AllContains() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_all_contains, container, false);
        list = (ListView)v.findViewById(R.id.allListView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item item = arr.get(position);
                Intent intent = new Intent(getActivity(), ViewContainsActivity.class);
                intent.putExtra("TITLE",item.getTitle());
                intent.putExtra("LINK",item.getLink());
                startActivity(intent);
            }
        });
        arr = new ArrayList<>();
        adapter = new RssListAdapter(getActivity(),arr);
        //タスク起動
        RssParserTask task = new RssParserTask(getActivity(),adapter,list);
        task.execute(URLs.All);
        return v;
    }


}
