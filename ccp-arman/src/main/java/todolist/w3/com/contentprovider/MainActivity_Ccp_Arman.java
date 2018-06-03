package todolist.w3.com.contentprovider;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import todolist.w3.com.contentprovider.bean.ToDo;
import todolist.w3.com.contentprovider.db.ToDoListDBAdapter;

public class MainActivity_Ccp_Arman extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextNewToDoString, editTextToDoId, editTextNewToDo, editTextPlace;
    private TextView textViewToDos;
    private Button buttonAddToDo, buttonRemoveToDo, buttonModifyToDo;

    private ToDoListDBAdapter toDoListDBAdapter;

    private List<ToDo> toDos;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ccp_arman);

        toDoListDBAdapter=ToDoListDBAdapter.getToDoListDBAdapterInstance(getApplicationContext());
        toDos=toDoListDBAdapter.getAllToDos();

        editTextNewToDoString=(EditText)findViewById(R.id.editTextNewToDoString);
        editTextToDoId=(EditText)findViewById(R.id.editTextToDoId);
        editTextNewToDo=(EditText)findViewById(R.id.editTextNewToDo);
        editTextPlace=(EditText)findViewById(R.id.editTextPlace);

        textViewToDos=(TextView)findViewById(R.id.textViewToDos);


        buttonAddToDo=(Button)findViewById(R.id.buttonAddToDo);
        buttonRemoveToDo=(Button)findViewById(R.id.buttonRemoveToDo);
        buttonModifyToDo=(Button)findViewById(R.id.buttonModifyToDo);

        buttonModifyToDo.setOnClickListener(this);
        buttonRemoveToDo.setOnClickListener(this);
        buttonAddToDo.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        setNewList();
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.buttonAddToDo) {
            addNewToDo();
        } else if (i == R.id.buttonRemoveToDo) {
            removeToDo();
        } else if (i == R.id.buttonModifyToDo) {
            modifyToDo();
        } else {
        }
    }

    private void setNewList(){
        textViewToDos.setText(getToDoListString());
    }

    private void addNewToDo(){
        toDoListDBAdapter.insert(editTextNewToDoString.getText().toString(), editTextPlace.getText().toString());
        setNewList();
    }

    private void removeToDo(){
        toDoListDBAdapter.delete(Integer.parseInt(editTextToDoId.getText().toString()));
        setNewList();
    }

    private void modifyToDo(){
        int id=Integer.parseInt(editTextToDoId.getText().toString());
        String newToDO=editTextNewToDo.getText().toString();
        toDoListDBAdapter.modify(id,newToDO);
        setNewList();
    }



    private String getToDoListString(){
        toDos=toDoListDBAdapter.getAllToDos();
        if(toDos!=null && toDos.size()>0){
            StringBuilder stringBuilder=new StringBuilder("");
            for(ToDo toDo:toDos){
                stringBuilder.append(toDo.getId()+", "+toDo.getToDo()+", "+toDo.getPlace()+"\n");
            }
            return stringBuilder.toString();
        }else {
            return "No todo items";
        }
    }
}
