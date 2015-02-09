package com.example.dota2heros;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {
	final Context context = this;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final List<Hero> heros = new ArrayList<Hero>();
        heros.add(new Hero("Abaddon", "abaddon_logo.png"));
        heros.add(new Hero("Karroch","beastmaster_logo.png"));
        heros.add(new Hero("Traxex","drowranger_logo.png"));
        heros.add(new Hero("Nagasiren","nagasiren_logo.png"));
        heros.add(new Hero("Lanaya","templarassassin_logo.png"));
        
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new MyAdapter(this, R.layout.activeity_detail, heros));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                
                	Hero hero = heros.get(position);
                	final Intent intent = new Intent(MainActivity.this, DetailActivity.class).putExtra("myHero", hero);
                	if(position<4){
                		startActivity(intent);
                	}
                	else{
            		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
            			context);
        			// set title
        			alertDialogBuilder.setTitle("Warning");
        			// set dialog message
        			alertDialogBuilder
        				.setMessage("For Adults Only! Click Yes if ur 18+")
        				.setCancelable(false)
        				.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
        					public void onClick(DialogInterface dialog,int id) {
        						startActivity(intent);
        					}
        				  })
        				.setNegativeButton("No",new DialogInterface.OnClickListener() {
        					public void onClick(DialogInterface dialog,int id) {
        						// if this button is clicked, just close
        						// the dialog box and do nothing
        						dialog.cancel();
        					}
        				});
         
        				// create alert dialog
        				AlertDialog alertDialog = alertDialogBuilder.create();
         
        				// show it
        				alertDialog.show();
                	}
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.uninstall) {
        	Uri packageURI = Uri.parse("package:com.example.dota2heros");
        	Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
        	startActivity(uninstallIntent);
        }
        else if(id == R.id.information){
        	final Intent intent = new Intent(MainActivity.this, InfoActivity.class);
        	startActivity(intent);
        	return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
