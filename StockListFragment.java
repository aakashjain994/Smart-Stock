package com.codingblocks.stocks;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 */
public class StockListFragment extends Fragment implements AdapterView.OnItemClickListener, StocksAyncTaskInterface {


    public StockListFragment() {
        // Required empty public constructor
    }
    StockArrayAdapter adapter;
    ArrayList<Stock> stocks;
    ProgressDialog progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_stock_list, container, false);

        stocks = new ArrayList<>();


        SharedPreferences sp = getActivity().getSharedPreferences("stocks", Context.MODE_PRIVATE);
        Set<String> tickerSet = sp.getStringSet("tickers", null);
        ArrayList<String> ticker = new ArrayList<>();
        if (tickerSet != null)
            ticker.addAll(tickerSet);
        else {
            ticker.add("MSFT");
            ticker.add("fb");
        }

        String urlString = getURLString(ticker);
        StocksFetcherAsyncTask task = new StocksFetcherAsyncTask();
        task.listener = this;
        task.execute(urlString);
        progress = new ProgressDialog(getContext());
        progress.setTitle("Getting stocks Data");
        progress.setMessage("Wait!");
        progress.show();

        ListView lv = (ListView) v.findViewById(R.id.batchListView);
        adapter = new StockArrayAdapter(getContext(), stocks);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
        return v;
    }

    private String getURLString(ArrayList<String> companyTickers) {
        return "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22YHOO%22%2C%22GOOG%22%2C%22MSFT%22)%0A%09%09&env=http%3A%2F%2Fdatatables.org%2Falltables.env&format=json";
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Batch b = adapter.getItem(position);
//        SharedPreferences sp = getSharedPreferences("batch_clicked", MODE_PRIVATE);
//        SharedPreferences.Editor editor = sp.edit();
//        editor.putString("last_batch", b.name);
//        editor.commit();
//
        Intent i = new Intent();
        i.setClass(getContext(), DetailActivity.class);
        i.putExtra("stock", adapter.getItem(position));
//        i.putExtra("studentArray", new Student[10]);
        startActivity(i);
//        startActivityForResult(i, 0);
    }

    //   @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
////        if (data == null)
////            return;
////        Bundle b = data.getExtras();
////        String studentName = b.getString("studentName");
////        Toast.makeText(this, "student clicked " + studentName, Toast.LENGTH_LONG).show();
//    }

    @Override
    public void stocksTaskOnComplete(Stock[] a) {
        stocks.clear();
        for (Stock s: a) {
            stocks.add(s);
        }
        adapter.notifyDataSetChanged();
        progress.dismiss();
    }




}

