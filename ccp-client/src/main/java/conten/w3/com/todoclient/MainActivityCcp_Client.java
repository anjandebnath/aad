package conten.w3.com.todoclient;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivityCcp_Client extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG=MainActivityCcp_Client.class.getSimpleName();

    private TextView textViewContent,textViewCount;

    private EditText editTextWhere;

    private Button buttonGetAllToDos, buttonGetToDoForPlace, buttonNext;

    private ContentResolver contentResolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ccp_client);

        textViewContent=(TextView)findViewById(R.id.textViewContent);
        textViewCount=(TextView)findViewById(R.id.textViewCount);
        editTextWhere=(EditText)findViewById(R.id.editTextWhere);

        buttonGetAllToDos=(Button)findViewById(R.id.buttonGetAllToDos);
        buttonGetToDoForPlace=(Button)findViewById(R.id.buttonGetToDoForPlace);
        buttonNext=(Button)findViewById(R.id.buttonNext);

        buttonGetAllToDos.setOnClickListener(this);
        buttonGetToDoForPlace.setOnClickListener(this);
        buttonNext.setOnClickListener(this);

        contentResolver=getContentResolver();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setCount();
        setAllToDos();
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.buttonGetAllToDos) {
            setAllToDos();
        } else if (i == R.id.buttonGetToDoForPlace) {
            setSpecificPlaceToDos();
        } else if (i == R.id.buttonNext) {
            startNextActivity();
        }
    }

    private void startNextActivity(){
        Intent intent=new Intent(getApplicationContext(),SecondActivity.class);
        startActivity(intent);
    }
    private void setAllToDos(){
        Cursor cursor=contentResolver.query(ToDoProviderConstants.CONTENT_URI_1, null,null,null,null,null);
        StringBuilder stringBuilder=new StringBuilder("");
        if(cursor!=null &cursor.getCount()>0){

            while(cursor.moveToNext()){
                stringBuilder.append(cursor.getLong(0)+", "+cursor.getString(1)+", "+cursor.getString(2)+"\n");
            }
        }
        cursor.close();
        textViewContent.setText(stringBuilder.toString());
    }

    private void setSpecificPlaceToDos(){
        String place=editTextWhere.getText().toString();
        StringBuilder stringBuilder=new StringBuilder("");
        Cursor cursor=contentResolver.query(ToDoProviderConstants.CONTENT_URI_2,null,null,new String[]{place},null,null);

        if(cursor!=null &cursor.getCount()>0){

            while(cursor.moveToNext()){
                stringBuilder.append(cursor.getLong(0)+", "+cursor.getString(1)+"\n");
            }
        }
        cursor.close();
        textViewContent.setText(stringBuilder.toString());
    }

    private void setCount(){
        Cursor cursor=contentResolver.query(ToDoProviderConstants.CONTENT_URI_3,null,null,null,null,null);
        if(cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
            textViewCount.setText("Total ToDos Count: "+cursor.getInt(0));
        }
        cursor.close();
    }
}
