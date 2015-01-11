package com.softwareG06.sanaldersanem;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class Giris extends Activity {
	

	Button one,two,three,four;
	final boolean checked_state[]={false,false,false,false}; //The array that holds the checked state of the checkbox items
	final CharSequence[] colors_check={"KPSS","YGS","LYS","Sizden Gelen"}; //items in the alertdialog that displays checkboxes
	final int CHECKBOX_ALERTDIALOG=1;

	static String user,name;
	Bundle veriAl=new Bundle();

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_screen);
		veriAl = getIntent().getExtras();
		user    = veriAl.getString("uname");
		name   = veriAl.getString("name");
		
		one=(Button)findViewById(R.id.bOne);
		two=(Button)findViewById(R.id.bTwo);
		three=(Button)findViewById(R.id.bThree);
		four=(Button)findViewById(R.id.bFour);
		
		one.setOnClickListener(onClickListener);
        two.setOnClickListener(onClickListener);
        three.setOnClickListener(onClickListener);
        four.setOnClickListener(onClickListener);
}

private OnClickListener onClickListener = new OnClickListener() {
     @Override
     public void onClick(View v) {
         switch(v.getId()){
             case R.id.bOne:
            	    showDialog(CHECKBOX_ALERTDIALOG);
             break;
             case R.id.bTwo:
            	 Intent oyatmaOlustur=new Intent(Giris.this,OynatmaListesiOlustur.class);
            	 oyatmaOlustur.putExtra("uname",name );
            	 startActivity(oyatmaOlustur);
             break;
             case R.id.bThree:
            	 Intent test=new Intent(Giris.this,MakeChoice.class);
            	 test.putExtra("uname",user );
            	 test.putExtra("name",name );
            	 startActivity(test);
             break;
             case R.id.bFour:
            	 showAlert();
             break;
         }

   }
};

public void showAlert(){
	AlertDialog alertDialog = new AlertDialog.Builder(this).create();
	alertDialog.setTitle("DeadLock...");
	alertDialog.setMessage("Uygulama ders kapsam�nda SoftwareG06 taraf�ndan yap�lm��t�r. \n-------------------------\nGRUP �YELER�\n------------------------- \n\t �Tayip T�RK \n\t �An�l E�ER \n\t �Yusuf BAYKAL \n\t �M.Ali �elik \n\t �G�ksel Hamal�\n");
	alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
	public void onClick(DialogInterface dialog, int which) {
		 dialog.dismiss();
	}
	});
	alertDialog.setIcon(R.drawable.logomini);
	alertDialog.show();
}

/*triggered by showDialog method. onCreateDialog creates a dialog*/
@Override
public Dialog onCreateDialog(int id) {
switch (id) {

case CHECKBOX_ALERTDIALOG:

AlertDialog.Builder builder1=new AlertDialog.Builder(Giris.this)
.setTitle("Kurs Se�")
.setMultiChoiceItems(colors_check, null, new DialogInterface.OnMultiChoiceClickListener() {

@Override
public void onClick(DialogInterface dialog, int which, boolean isChecked) {
// TODO Auto-generated method stub

//storing the checked state of the items in an array
checked_state[which]=isChecked;
}
})
.setPositiveButton("OK", new DialogInterface.OnClickListener() {

@Override
public void onClick(DialogInterface dialog, int which) {
// TODO Auto-generated method stub
	Intent intent;
	
String display_checked_colors = "";
for(int i=0;i<4;i++){
if(checked_state[i]==true){
display_checked_colors=display_checked_colors+" "+colors_check[i];
if (i==0) {

	intent=new Intent(Giris.this,YGSMain.class);
	 intent.putExtra("uname",user );
	startActivity(intent);
} else if (i==1) {
	intent=new Intent(Giris.this,YGSMain.class);
	 intent.putExtra("uname",user );
	startActivity(intent);
}
else if (i==2) {
	intent=new Intent(Giris.this,YGSMain.class);
	 intent.putExtra("uname",user );
	startActivity(intent);
}
else if (i==3) {
	intent=new Intent(Giris.this,OynatmaListesiAl.class);
	 intent.putExtra("uname",user );
	startActivity(intent);
}


}

}

//clears the String used to store the displayed text
display_checked_colors=null;

//clears the array used to store checked state
for(int i=0;i<checked_state.length;i++){
if(checked_state[i]==true){
checked_state[i]=false;
}
}

//used to dismiss the dialog upon user selection.
dialog.dismiss();
}
});
AlertDialog alertdialog1=builder1.create();
return alertdialog1;


}
return null;

}

@Override
protected void onPrepareDialog(int id, Dialog dialog) {
// TODO Auto-generated method stub

switch (id) {
case CHECKBOX_ALERTDIALOG:
AlertDialog prepare_checkbox_dialog=(AlertDialog)dialog;
ListView list_checkbox=prepare_checkbox_dialog.getListView();
for(int i=0;i<list_checkbox.getCount();i++){
list_checkbox.setItemChecked(i, false);
}
break;


}

}	
}
