package com.example.david.wed21marsfirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "Firebase";

    DatabaseReference itemRef;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef = database.getReference("message");

        itemRef = database.getReference("items");

//        itemRef.child("majs").setValue(false);
//        itemRef.child("ärtor").setValue(false);
//        itemRef.child("morötter").setValue(false);


        itemRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

//                Boolean value = dataSnapshot.child("majs").getValue(Boolean.class);
//                Log.d(TAG, "Value for majs is: " + value);

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Item item = child.getValue(Item.class);
                    if (item.name.equals("Ärtor"))
                        Log.d(TAG, "ärtor finns ");
                    Log.d(TAG, "name: " + item.name + " done: " + item.done );

                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


    }

    public void save(View view) {
        Item item = new Item(editText.getText().toString(), false);
        itemRef.child(item.name.toLowerCase()).setValue( item);
    }

}
