package com.example.paynsplit;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class getTheBillAndSplitTheBill extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_the_bill_and_split_the_bill);
        TextView billview = (TextView)findViewById(R.id.bill);

        int GroupTotal=groupTransactions.groupTotal;
        int PersonalTotal=ViewTransaction.personalTotal;
        long groupMemberCount=groupTransactions.groupMemberCount;

        long perMemberContribution =GroupTotal/groupMemberCount;


        Toast.makeText(getApplicationContext(),"Number Of Group Members"+groupMemberCount,Toast.LENGTH_LONG).show();



//        Toast.makeText(getApplicationContext(),"Hey mayur here"+PersonalTotal,Toast.LENGTH_LONG).show();


        if(PersonalTotal<perMemberContribution)
        {
            long  ammountToPayed=(perMemberContribution-PersonalTotal);
            billview.setText("Group Total Is :-  "+GroupTotal+"\n\nPersonal Contribution  :-  "+PersonalTotal+"\n\nPer Member\nContribution is "+perMemberContribution+"\n\nYou Have To pay "+ammountToPayed);

        }
        else if(perMemberContribution<PersonalTotal) {

            long  ammountToRecived=(PersonalTotal-perMemberContribution);
            billview.setText("Group Total Is :-  "+GroupTotal+"\n\nPersonal Contribution  :-  "+PersonalTotal+"\n\nPer Mermber\nContribution is "+perMemberContribution+"\n\nYou Have To Receive "+ammountToRecived);

        }
        else if(perMemberContribution==PersonalTotal)
        {
            billview.setText("Group Total Is :-  "+GroupTotal+"\n\nPersonal Contribution  :-  "+PersonalTotal+"\n\nPer Mermber\nContribution is "+perMemberContribution+"\n\n Great Job ! \nNothing to pay Or Receive !");

        }
    }
}
