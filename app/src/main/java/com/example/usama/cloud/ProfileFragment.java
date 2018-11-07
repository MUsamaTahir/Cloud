package com.example.usama.cloud;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ProfileFragment extends Fragment {
    private EditText editTextName, editTextCountry, editTextCity,editTextPhone;
    Button saveBtn;
    FirebaseUser fuser;
    User user;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_profile,container,false);
        editTextPhone = rootView.findViewById(R.id.edit_text_phone);
        editTextName = rootView.findViewById(R.id.edit_text_name);
        editTextCity = rootView.findViewById(R.id.edit_text_city);
        editTextCountry = rootView.findViewById(R.id.edit_text_country);
        saveBtn=rootView.findViewById(R.id.button_save);
        loaduserdata();
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveuserInfo();
            }
        });
        return rootView;

    }

    public void loaduserdata()
    {

        fuser=FirebaseAuth.getInstance().getCurrentUser();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        Query query = ref.child("Users").orderByChild("email").equalTo(fuser.getEmail());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // dataSnapshot is the "issue" node with all children with id 0

                    for (DataSnapshot user : dataSnapshot.getChildren()) {
                        // do something with the individual "issues"
                        User usersb = user.getValue(User.class);
                        editTextPhone.setText(usersb.phoneNo);
                        editTextName.setText(usersb.name);
                        editTextCountry.setText(usersb.country);
                        editTextCity.setText(usersb.city);


                    }
                } else {
                  //  Toast.makeText(.this, "User Data not found", Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });


    }
    private void saveuserInfo()
    {
        String displayname=editTextName.getText().toString().trim();
        if(displayname.isEmpty())
        {
            editTextName.setError("Name Required");
            editTextName.requestFocus();
            return;
        }
        String name,phoneNo,city,country,email;
        name=editTextName.getText().toString();
        phoneNo=editTextPhone.getText().toString();
        city=editTextCity.getText().toString();
        country=editTextCountry.getText().toString();
        email=fuser.getEmail();
        user=new User(name,email,city,country,phoneNo);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        ref.child("Users").push().setValue(user);
        Toast.makeText(getActivity(),"Saved Successfully",Toast.LENGTH_LONG).show();
    }

}
