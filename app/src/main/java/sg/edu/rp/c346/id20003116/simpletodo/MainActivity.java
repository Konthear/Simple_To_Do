package sg.edu.rp.c346.id20003116.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etInput;
    Button btnAdd;
    Button btnDelete;
    Button btnClear;
    ListView lvToDo;
    Spinner spinner;
    ArrayList<String> alToDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInput = findViewById(R.id.editTodo);
        btnAdd = findViewById(R.id.buttonAdd);
        btnDelete = findViewById(R.id.buttonDel);
        btnClear = findViewById(R.id.buttonClear);
        lvToDo = findViewById(R.id.toDoList);
        spinner = findViewById(R.id.spinner);

        alToDo = new ArrayList<String>();
        ArrayAdapter<String> aaToDo = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alToDo);
        lvToDo.setAdapter(aaToDo);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position) {
                    case 0:
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);

                        etInput.setHint("Type in a new task here");

                        btnAdd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String strText = etInput.getText().toString();
                                alToDo.add(strText);
                                aaToDo.notifyDataSetChanged();
                            }
                        });

                        break;
                    case 1:
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);

                        etInput.setHint("Type in the index of the task to be removed");

                        btnDelete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (alToDo.isEmpty() == true) {
                                    Toast toast = Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_LONG);
                                    toast.show();
                                } else if (alToDo.isEmpty() == false) {
                                    int pos = Integer.parseInt(etInput.getText().toString());

                                    if (alToDo.size() <= pos) {
                                        Toast toast = Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_LONG);
                                        toast.show();
                                    } else if (alToDo.size() >= pos) {
                                        alToDo.remove(pos);
                                        aaToDo.notifyDataSetChanged();
                                    }
                                }
                            }
                        });
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alToDo.clear();
                aaToDo.notifyDataSetChanged();
            }
        });
    }
}