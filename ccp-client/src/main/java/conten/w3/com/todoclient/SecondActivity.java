package conten.w3.com.todoclient;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class SecondActivity extends AppCompatActivity implements View.OnClickListener{

    private static String TAG=SecondActivity.class.getSimpleName();

    ContentResolver contentResolver;

    private EditText editTextNewToDoString, editTextPlace, editTextToDoId, editTextNewToDo, editTextDeleteToDoId;
    private Button buttonAddToDo, buttonModifyToDo,buttonDeleteToDo;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        editTextNewToDo=(EditText)findViewById(R.id.editTextNewToDo);
        editTextNewToDoString=(EditText)findViewById(R.id.editTextNewToDoString);
        editTextPlace=(EditText)findViewById(R.id.editTextPlace);
        editTextToDoId=(EditText)findViewById(R.id.editTextToDoId);
        editTextDeleteToDoId=(EditText)findViewById(R.id.editTextDeleteToDoId);

        buttonAddToDo=(Button)findViewById(R.id.buttonAddToDo);
        buttonModifyToDo=(Button)findViewById(R.id.buttonModifyToDo);
        buttonDeleteToDo=(Button)findViewById(R.id.buttonDeleteToDo);

        buttonAddToDo.setOnClickListener(this);
        buttonModifyToDo.setOnClickListener(this);
        buttonDeleteToDo.setOnClickListener(this);

        contentResolver=getContentResolver();
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.buttonAddToDo) {
            addToDo();
        } else if (i == R.id.buttonModifyToDo) {
            updateToDo();
        } else if (i == R.id.buttonDeleteToDo) {
            deleteToDo();
        } else {
        }
    }

    private void deleteToDo() {
        String todoId=editTextDeleteToDoId.getText().toString();
        contentResolver.delete(ToDoProviderConstants.CONTENT_URI_1,ToDoProviderConstants.COLUMN_TODO_ID +" = ?",new String[]{todoId});
        finish();
    }

    private void addToDo(){
        
        String todo=editTextNewToDoString.getText().toString();
        String place=editTextPlace.getText().toString();

        ContentValues contentValues=new ContentValues();
        contentValues.put(ToDoProviderConstants.COLUMN_TODO,todo);
        contentValues.put(ToDoProviderConstants.COLUMN_PLACE,place);

        contentResolver.insert(ToDoProviderConstants.CONTENT_URI_1,contentValues);
        String firstType = contentResolver.getType(ToDoProviderConstants.CONTENT_URI_1);
        Log.v(TAG,""+firstType);
        String secondType = contentResolver.getType(ToDoProviderConstants.CONTENT_URI_2);
        Log.v(TAG,""+secondType);
        String thirdType = contentResolver.getType(ToDoProviderConstants.CONTENT_URI_3);
        Log.v(TAG,""+thirdType);
        finish();
    }

    private void updateToDo(){
        ContentValues contentValues=new ContentValues();
        contentValues.put(ToDoProviderConstants.COLUMN_TODO,editTextNewToDo.getText().toString());
        String whereClause= ToDoProviderConstants.COLUMN_TODO_ID+" = ?";
        String [] whereValues=new String []{editTextToDoId.getText().toString()};

        contentResolver.update(ToDoProviderConstants.CONTENT_URI_1,contentValues,whereClause,whereValues);
        finish();
    }


}
