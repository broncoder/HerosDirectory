package com.example.dota2heros;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class DetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activeity_detail);

        Hero hero = getIntent().getExtras().getParcelable("myHero");
        String name = hero.getName();
        
        TextView textView = (TextView) findViewById(R.id.rowText);
        textView.setText(name);

        ImageView imageView = (ImageView) findViewById(R.id.rowImage);
        if(name.equals("Abaddon"))
        	imageView.setImageResource(R.drawable.abaddon);
        else if(name.equals("Karroch"))
        	imageView.setImageResource(R.drawable.beast);
        else if(name.equals("Traxex"))
        	imageView.setImageResource(R.drawable.drow);
        else if(name.equals("Nagasiren"))
        	imageView.setImageResource(R.drawable.naga);
        else if(name.equals("Lanaya"))
        	imageView.setImageResource(R.drawable.temp);
        
        TextView textView2 = (TextView) findViewById(R.id.rowText2);
        String text = getDetailText(name);
        if(text.length()>0)
        	textView2.setText(text);
    }
    private String getDetailText(String name) {
    	InputStream inputStream;
    	if(name.equals("Abaddon"))
    		inputStream = getResources().openRawResource(R.raw.abadon);
    	else if(name.equals("Karroch"))
    		inputStream = getResources().openRawResource(R.raw.beast);
    	else if(name.equals("Traxex"))
    		inputStream = getResources().openRawResource(R.raw.drow);
    	else if(name.equals("Nagasiren"))
    		inputStream = getResources().openRawResource(R.raw.naga);
    	else if(name.equals("Lanaya"))
    		inputStream = getResources().openRawResource(R.raw.temp);
    	else 
    		return "";
        
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        
        int i;
        try {
        	i = inputStream.read();
        	while (i != -1)
        	{
        		byteArrayOutputStream.write(i);
        		i = inputStream.read();
        	}
         inputStream.close();
        } catch (IOException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
        }
     
        return byteArrayOutputStream.toString();
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
        	final Intent intent = new Intent(DetailActivity.this, InfoActivity.class);
        	startActivity(intent);
        	return true;
        }
        return super.onOptionsItemSelected(item);
    }
}