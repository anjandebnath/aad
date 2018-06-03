package conten.w3.com.todoclient;

import android.net.Uri;

public interface ToDoProviderConstants {

    Uri CONTENT_URI_1 = Uri.parse
            ("content://todolist.w3.com.contentprovider/TODO_LIST");
    Uri CONTENT_URI_2 = Uri.parse
            ("content://todolist.w3.com.contentprovider/TODO_LIST_FROM_PLACE");
    Uri CONTENT_URI_3 = Uri.parse
            ("content://todolist.w3.com.contentprovider/TODOS_COUNT");

    String COLUMN_TODO_ID="task_id";
    String COLUMN_TODO="todo";
    String COLUMN_PLACE="place";
}
