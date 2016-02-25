package com.example.cpinto.littlefield;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    private Integer Station1NumberMachines= 4;
    private Integer Station2NumberMachines= 3;
    private Integer Station3NumberMachines= 4;

    //updatable Numbers of machine per station
    private EditText editTextMachineNumber1;
    private EditText editTextMachineNumber2;
    private EditText editTextMachineNumber3;

    //Machine Capacities per day
    private TextView textViewMachineCapacityDaily1;
    private TextView textViewMachineCapacityDaily2;
    private TextView textViewMachineCapacityDaily3;
    private TextView textViewMachineCapacityDaily4;

    //Times per Lot
    private TextView textViewStation1TimePerLot;
    private TextView textViewStation2TimePerLot;
    private TextView textViewStation3TimePerLot;
    private TextView textViewStation4TimePerLot;

    //Actual number per station
    private TextView textViewSystem1MachineNumber;
    private TextView textViewSystem2MachineNumber;
    private TextView textViewSystem3MachineNumber;
    private TextView textViewSystem4MachineNumber;


    private Button update;

    //Stats for station 1

    //Stats for station 2

    //Stats for station 3

    //Stats for station 4


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView= inflater.inflate(R.layout.fragment_main2,container,false);

        //Fill content here
        //Setup current views
        editTextMachineNumber1=(EditText)rootView.findViewById(R.id.editTextStation1MachineNumber);
        editTextMachineNumber2=(EditText)rootView.findViewById(R.id.editTextStation2MachineNumber);
        editTextMachineNumber3=(EditText)rootView.findViewById(R.id.editTextStation3MachineNumber);
        update=(Button)rootView.findViewById(R.id.buttonUpdateMachines);
        textViewStation1TimePerLot=(TextView)rootView.findViewById(R.id.textViewSystem1TimePerLot);
        textViewStation2TimePerLot=(TextView)rootView.findViewById(R.id.textViewSystem2TimePerLot);
        textViewStation3TimePerLot=(TextView)rootView.findViewById(R.id.textViewSystem3TimePerLot);
        textViewStation4TimePerLot=(TextView)rootView.findViewById(R.id.textViewSystem4TimePerLot);
        textViewMachineCapacityDaily1=(TextView)rootView.findViewById(R.id.capacityMachine1);
        textViewMachineCapacityDaily2=(TextView)rootView.findViewById(R.id.capacityMachine2);
        textViewMachineCapacityDaily3=(TextView)rootView.findViewById(R.id.capacityMachine3);
        textViewMachineCapacityDaily4=(TextView)rootView.findViewById(R.id.capacityMachine4);
        textViewSystem1MachineNumber=(TextView)rootView.findViewById(R.id.system1NumberMachines);
        textViewSystem2MachineNumber=(TextView)rootView.findViewById(R.id.system2NumberMachines);
        textViewSystem3MachineNumber=(TextView)rootView.findViewById(R.id.system3NumberMachines);
        textViewSystem4MachineNumber=(TextView)rootView.findViewById(R.id.system4NumberMachines);

        //Update current views
        //First update number of machines per station
        updateViews();


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Activate update function
                    //First update number of machines per station
                  updateViews();
                    //Then update Capacity per station

                }
        });



        return rootView;
    }// End onCreateView

private  void updateViews(){
    Station1NumberMachines=Integer.parseInt(editTextMachineNumber1.getText().toString());
    Station2NumberMachines=Integer.parseInt(editTextMachineNumber2.getText().toString());
    Station3NumberMachines=Integer.parseInt(editTextMachineNumber3.getText().toString());
    textViewSystem1MachineNumber.setText(String.valueOf(Station1NumberMachines));
    textViewSystem2MachineNumber.setText(String.valueOf(Station2NumberMachines));
    textViewSystem3MachineNumber.setText(String.valueOf(Station3NumberMachines));
    textViewSystem4MachineNumber.setText(String.valueOf(Station2NumberMachines));
}

}//End MainActivityFragment
