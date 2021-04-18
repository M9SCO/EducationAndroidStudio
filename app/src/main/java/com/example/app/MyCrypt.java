package com.example.app;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MyCrypt {
    public static String toMd5(String str) {
        MessageDigest m = null;

        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        m.update(str.getBytes(), 0, str.length());
        return new BigInteger(1, m.digest()).toString(16);
    }
}
    /*
<EditText
        android:id="@+id/registrationPassword"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="24dp"
        android:layout_marginTop="25dp"

        android:layout_marginEnd="24dp"
        android:autofillHints=""
        android:hint="Password"
        android:imeOptions="actionDone"
        android:importantForAutofill="no"
        android:inputType="textPassword"
        android:selectAllOnFocus="true"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="1.0"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/registrationUsername" />

    <EditText
        android:id="@+id/registrationUsername"
        android:layout_width="0dp"
        android:layout_height="52dp"
        android:layout_marginStart="24dp"
        android:layout_marginTop="75dp"
        android:layout_marginEnd="24dp"
        android:autofillHints=""
        android:ems="10"
        android:hint="Username"
        android:inputType="textPersonName"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.0"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <EditText
        android:id="@+id/registrationPasswordAgain"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="24dp"
        android:layout_marginTop="25dp"
        android:layout_marginEnd="24dp"
        android:ems="10"
        android:hint="Password Again"
        android:inputType="textPassword"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/registrationPassword"
        android:importantForAutofill="no" />

    <Button
        android:id="@+id/registration"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="150dp"
        android:layout_marginLeft="150dp"
        android:layout_marginTop="40dp"
        android:layout_marginEnd="24dp"
        android:layout_marginRight="24dp"
        android:text="Signed up"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/registrationPasswordAgain" />

    <TextView
        android:id="@+id/textErrors"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="25dp"
        android:textColor="#FF0000"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/registrationPasswordAgain" />


*/

    /*


    private EditText registered_username;
    private EditText registered_password;
    private EditText registered_password_again;
    private TextView errors;
    private Button registered;

    private void check_valid_data(){
        String error_out = "";

        if (!registered_password.getText().toString().equals(registered_password_again.getText().toString()))
            error_out += "\n" + getResources().getString(R.string.different_password);
        else if (registered_password.getText().toString().length() <6)
            error_out += "\n" + getResources().getString(R.string.short_password);
        if (registered_username.getText().toString().length() <=3)
            error_out += "\n" + getResources().getString(R.string.short_username);
        else if ((registered_username.getText().toString().length() >=16))
            error_out += "\n" + getResources().getString(R.string.large_username);
        registered.setEnabled(error_out.isEmpty());
        errors.setText(error_out);

    }

    public FragmentSignUp() {
        // Required empty public constructor


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        registered_username = (EditText) view.findViewById(R.id.registrationUsername);
        registered_password = (EditText) view.findViewById(R.id.registrationPassword);
        registered_password_again = (EditText) view.findViewById(R.id.registrationPasswordAgain);
        registered = (Button) view.findViewById(R.id.registration);
        errors = (TextView) view.findViewById(R.id.textErrors);
//        registered.setOnClickListener(this);



//        TextWatcher checker = new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                check_valid_data();}
//            @Override
//            public void afterTextChanged(Editable editable) {}};
//
//        registered_username.addTextChangedListener(checker);
//        registered_password_again.addTextChangedListener(checker);
//        registered_password.addTextChangedListener(checker);

    }



    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        String login = registered_username.getText().toString();
        String password = registered_password.getText().toString();
        MyKnownUsers.set_pass(login, password, getActivity().getApplicationContext());
        FragmentSignIn myFragment = new FragmentSignIn();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, myFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();



    }
*/