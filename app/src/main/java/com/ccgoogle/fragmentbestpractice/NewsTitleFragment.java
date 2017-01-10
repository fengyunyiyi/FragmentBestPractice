package com.ccgoogle.fragmentbestpractice;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenjunlong on 2017/1/9.
 */

public class NewsTitleFragment extends Fragment implements AdapterView.OnItemClickListener {

    private List<News> newsList;
    private NewsAdapter adapter;
    private ListView newsTitleListView;
    private boolean isTwoPane;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        newsList = getNews();
        adapter = new NewsAdapter(activity, R.layout.news_item, newsList);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_title_frag, container, false);
        newsTitleListView = (ListView) view.findViewById(R.id.news_title_list_view);
        newsTitleListView.setAdapter(adapter);
        newsTitleListView.setOnItemClickListener(this);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        if(getActivity().findViewById(R.id.news_content_layout)!=null){
            isTwoPane = true;
        }else{
            isTwoPane = false;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        News news = newsList.get(position);
        if(isTwoPane){
            NewsContentFragment newsContentFragment = (NewsContentFragment) getFragmentManager().findFragmentById(R.id.news_content_fragment);
            newsContentFragment.refresh(news.title,news.content);
        }else{
            NewsContentActivity.actionStart(getActivity(),news.title,news.content);
        }

    }

    private List<News> getNews(){
        List<News> newsList = new ArrayList<>();
        News news1 = new News();
        news1.title = "Succeed in College as aLearning Disabled Student";
        news1.content = "College freshmen will soon learn to live with a roommate,adjust to a new" +
                "social scene and survive less-than-stellar dining hall food.Students with learnninng disabilities will face these" +
                "trasitions while also grappling with a few more hurdles.";
        newsList.add(news1);
        News news2 = new News();
        news2.title = "Google Android exec poached by China's Xiaomi";
        news2.content = "China's Xiaomi has poached a key Google executive involved i the tech giant's Android" +
                "phones, in a move seen as a coup for the rapidly growing Chinese smartphone maker.";
        newsList.add(news2);
        return newsList;
    }
}
