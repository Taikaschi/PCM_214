package de.fhs.pcm_214;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity_Katalog extends ActionBarActivity {

    private ListView list;
    ArrayList<Item> model = new ArrayList<Item>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_katalog);

        list = (ListView)findViewById(R.id.listView);
        initModel();
        list.setAdapter(createAdapter(null));
        list.setTextFilterEnabled(true);
    }

    public void initModel(){
        model.add(new Item("Käsekuchen"));
        model.add(new Item("Kartoffelsalat"));
        model.add(new Item("Putenbrust"));
        model.add(new Item("Bratwurst und Sauerkraut"));
        model.add(new Item("Jägerschnitzel"));
        model.add(new Item("Gefüllte Paprika"));
        model.add(new Item("Pommes Frites"));
        model.add(new Item("Blaubeerkuchen"));
        model.add(new Item("Chilli con carne"));
        model.add(new Item("Schmandkuchen"));
        model.add(new Item("Hefeklöße"));
        model.add(new Item("Graupensuppe"));
        model.add(new Item("Nudelsuppe"));
    }

    public ItemArrayAdapter createAdapter(String filter){

        ArrayList<Item> filteredModel = new ArrayList<Item>();

        if(filter != null){
            for(int i=0; i<model.size(); i++){
                Item item = model.get(i);
                if(item.recipeName.toLowerCase().contains(filter.toLowerCase())){
                    filteredModel.add(item);
                }
            }
            return  new ItemArrayAdapter(filteredModel, this);
        }
        return new ItemArrayAdapter(model, this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_katalog, menu); //baut das xml auf??
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnCloseListener(new SearchView.OnCloseListener(){
            @Override
            public boolean onClose()
            {
                list.setAdapter(createAdapter(null));
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            onSearchRequested();
            return true;
        }
        else if(id == R.id.action_ok)
        {
            pickChecked();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onNewIntent(Intent intent)
    {
        if(Intent.ACTION_SEARCH.equals(intent.getAction()))
        {
            String query = intent.getStringExtra(SearchManager.QUERY);
            list.setAdapter(createAdapter(query));
        }
    }

    public void pickChecked(){

        ArrayList<Item> auswahlListe = new ArrayList<>();
        int maxItems = Math.min(model.size(), 4);
        for(int i=0; i<model.size(); i++)
        {
            Item item = (Item) model.get(i);
            if(item.checked && auswahlListe.size()<maxItems)
            {
                auswahlListe.add(item);
            }
        }

        for(int i=0; i<auswahlListe.size(); i++)
        {
            Item item = (Item) auswahlListe.get(i);
            Log.d("", item.recipeName);
        }
    }

}
