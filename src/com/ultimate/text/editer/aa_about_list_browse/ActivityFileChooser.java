package com.ultimate.text.editer.aa_about_list_browse;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList; 
import java.util.Collections;
import java.util.List;
import java.text.DateFormat; 

import org.apache.http.entity.FileEntity;

import com.ultimate.text.editer.aa.FileBrowser;
import com.ultimate.text.editer.aa.pmTextEdit;
import com.ultimate.text.editer.aa.ebin.R;



import android.net.Uri;
import android.os.Bundle; 
import android.os.Environment;
import android.app.ListActivity;
import android.content.Intent; 
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView; 
import android.widget.Toast;

public class ActivityFileChooser extends ListActivity {

	private File currentDir;
    private FileArrayAdapter adapter;
    ListView lv;
    List<Item>dir = new ArrayList<Item>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.activity_fileexplorer);
        
        
        lv = (ListView) findViewById(android.R.id.list); //Because extend list activity, should use android list
        
        currentDir = new File(Environment.getExternalStorageDirectory().getPath());
        fill(currentDir); 
        
    }
       

    
    
    
    
    
    
    
    
    
    
    private void fill(File f)
    {
    	File[]dirs = f.listFiles(); 
		 this.setTitle(f.getPath());
		 dir = new ArrayList<Item>();
		 List<Item>fls = new ArrayList<Item>();
		 try{
			 for(File ff: dirs)
			 { 
				Date lastModDate = new Date(ff.lastModified()); 
				DateFormat formater = DateFormat.getDateTimeInstance();
				String date_modify = formater.format(lastModDate);
				if(ff.isDirectory()){
					
					
					File[] fbuf = ff.listFiles(); 
					int buf = 0;
					if(fbuf != null){ 
						buf = fbuf.length;
					} 
					else buf = 0; 
					String num_item = String.valueOf(buf);
					if(buf == 0) num_item = num_item + " item";
					else num_item = num_item + " items";
					
					//String formated = lastModDate.toString();
					dir.add(new Item(ff.getName(),num_item,date_modify,ff.getAbsolutePath(),"directory_icon")); 
				}
				else
				{
					
					String[] it=ff.getName().toString().split("\\.");
					String ext=it[it.length-1].toString();
			//		Toast.makeText(getApplicationContext(), ext, Toast.LENGTH_LONG).show();
					
					if(ext.toUpperCase().equals("C")){
					fls.add(new Item(ff.getName(),ff.length() + " Byte", date_modify, ff.getAbsolutePath(),"a_cp"));
					}else if(ext.toUpperCase().equals("CPP")){
						fls.add(new Item(ff.getName(),ff.length() + " Byte", date_modify, ff.getAbsolutePath(),"a_cp"));
					}else if(ext.toUpperCase().equals("CSS")){
						fls.add(new Item(ff.getName(),ff.length() + " Byte", date_modify, ff.getAbsolutePath(),"a_css"));	
					}else if(ext.toUpperCase().equals("XML")){
						fls.add(new Item(ff.getName(),ff.length() + " Byte", date_modify, ff.getAbsolutePath(),"a_xml"));
					}else if(ext.toUpperCase().equals("JAVA")){
						fls.add(new Item(ff.getName(),ff.length() + " Byte", date_modify, ff.getAbsolutePath(),"a_java"));
					}else if(ext.toUpperCase().equals("JS")){
						fls.add(new Item(ff.getName(),ff.length() + " Byte", date_modify, ff.getAbsolutePath(),"a_js"));
					}else if(ext.toUpperCase().equals("JSP")){
						fls.add(new Item(ff.getName(),ff.length() + " Byte", date_modify, ff.getAbsolutePath(),"a_jsp"));
					}else if(ext.toUpperCase().equals("HTML")){
						fls.add(new Item(ff.getName(),ff.length() + " Byte", date_modify, ff.getAbsolutePath(),"a_html"));
					}else if(ext.toUpperCase().equals("HTM")){
						fls.add(new Item(ff.getName(),ff.length() + " Byte", date_modify, ff.getAbsolutePath(),"a_htm"));
					}else if(ext.toUpperCase().equals("TXT")){
						fls.add(new Item(ff.getName(),ff.length() + " Byte", date_modify, ff.getAbsolutePath(),"a_text"));
					}
				}
			 }
		 }catch(Exception e)
		 {    
			 
		 }
		 Collections.sort(dir);
		 Collections.sort(fls);
		 dir.addAll(fls);
		 if(!f.getPath().equalsIgnoreCase("/"))
			 dir.add(0,new Item("..","Parent Directory","",f.getParent(),"directory_up"));
		 
		 adapter = new FileArrayAdapter(ActivityFileChooser.this,R.layout.file_view,dir);
		 lv.setAdapter(adapter); 
		 
    }
    
    
    
    
    
    
    @Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Item o = adapter.getItem(position);
	
		if(o.getImage().equalsIgnoreCase("directory_icon")||o.getImage().equalsIgnoreCase("directory_up")){
				currentDir = new File(o.getPath());
				fill(currentDir);
		}
		else
		{
			Item oo = dir.get(position);
			String ff =  oo.getPath().toString();
		
			
			Uri uri = Uri.parse(ff);
			//		Ui obj = new Ui();
			//obj.vv(ff);
			
//	    CharSequence filePath = (CharSequence) oo.getPath();
//	        Intent inn = new Intent("com.ultimate.text.editer.aa.pmTextEdit");
//	        Bundle bu = new Bundle();
//	        bu.putString("path10", oo.getPath().toString()+"/");
//	        inn.putExtras(bu);
//	        
//	        startActivity(inn);
		
		
		//	Intent inn = new Intent(Intent.ACTION_VIEW);
			Intent inn = new Intent("com.ultimate.text.editer.aa.pmTextEdit");
			inn.setDataAndType(uri, "text/plain");
			startActivity(inn);
	
	        
          //setResult(1, (new Intent("com.ultimate.text.editer.aa.pmTextEdit")).setAction((String) filePath));
            finish();				
		}
	}
    
    
    
    private void onFileClick(Item o)
    {
    	//Toast.makeText(this, "Folder Clicked: "+ currentDir, Toast.LENGTH_SHORT).show();
    	Intent intent = new Intent();
        intent.putExtra("GetPath",currentDir.toString());
        intent.putExtra("GetFileName",o.getName());
        setResult(RESULT_OK, intent);
        finish();
    }
}
