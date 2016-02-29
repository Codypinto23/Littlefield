package com.example.cpinto.littlefield;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    private Integer Station1NumberMachines= 4;
    private Integer Station2NumberMachines= 3;
    private Integer Station3NumberMachines= 4;

    private int batchSize=60;

    private double  capacityPerDayStation1;
    private double  capacityPerDayStation2;
    private double  capacityPerDayStation3;
    private double  capacityPerDayStation4;

    private double timePerLotStation1;
    private double timePerLotStation2;
    private double timePerLotStation3;
    private double timePerLotStation4;


    //Assumptions
    private double demandArrivalMin;
    private double demandArrivalMax;
    private double lengthOfDay;
    private double contractMin;
    private double contractMax;
    private double revenueMax;
    private double revenueHourlyLoss;
    private double totalLotTime;
    private double daysRequired;

    //Simulation
    private int daysToSimulate;
    private double utilizationStation1;
    private double utilizationStation2;
    private double utilizationStation3;
    private double minLeadTime;
    private double maxLeadTime;
    private double totalRevenue;


    //-------------EDIT TEXTS and TEXT VIEWS------------------------


    //total time
    private TextView textViewTotalLotTime;
    //batch size
    private EditText editTextBatchSize;

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

    //Demand Arrival
    private EditText editTextDemandMin;
    private EditText editTextDemandMax;

    //Contract
    private EditText editTextContractMin;
    private EditText editTextContractMax;

    //Length Of Day
    private EditText editTextLengthOfDay;

    //Revenue
    private EditText editTextRevenueHourlyLoss;

    //Simulation
    private EditText editTextDaysToSimulate;
    private TextView textViewUtilizationStation1;
    private TextView textViewUtilizationStation2;
    private TextView textViewUtilizationStation3;
    private TextView textviewTotalRevenue;
    private TextView textViewDaysRequired;

    private Button update;
    private Button simulate;

    //---------------STATS PER STATION--------------------

    final private int minutesPerHour=60;


    //Stats for station 1
    final private double setupTimeStation1=0;//in hours
    final private double runTimeStation1= .3; //hours/unit

    //Stats for station 2
    final  private double setupTimeStation2=.5;
    final  private double runTimeStation2=.25;

    //Stats for station 3
    final   private double setupTimeStation3=1;
    final    private double runTimeStation3=.01;


    //Stats for station 4
    private double setupTimeStation4=1.5;
    private double runTimeStation4=.3;


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
        editTextBatchSize=(EditText)rootView.findViewById(R.id.editTextBatchSize);
        //Assumptions
        editTextDemandMin=(EditText)rootView.findViewById(R.id.editTextMinArrival);
        editTextDemandMax=(EditText)rootView.findViewById(R.id.editTextMaxArrival);
        editTextContractMin=(EditText)rootView.findViewById(R.id.editTextContractMin);
        editTextContractMax=(EditText)rootView.findViewById(R.id.editTextContractMax);
        editTextLengthOfDay=(EditText)rootView.findViewById(R.id.editTextSecondsInDay);
        editTextRevenueHourlyLoss=(EditText)rootView.findViewById(R.id.editTextRevenueHourlyLoss);
        //Simulation
        editTextDaysToSimulate=(EditText)rootView.findViewById(R.id.editTextLengthSimulation);
        textViewUtilizationStation1=(TextView)rootView.findViewById(R.id.textViewUtilizationStation1);
        textViewUtilizationStation2=(TextView)rootView.findViewById(R.id.textViewUtilizationStation2);
        textViewUtilizationStation3=(TextView)rootView.findViewById(R.id.textViewUtilizationStation3);
        textviewTotalRevenue=(TextView)rootView.findViewById(R.id.textViewTotalRevenue);
        simulate=(Button)rootView.findViewById(R.id.buttonSimulate);
        textViewTotalLotTime=(TextView)rootView.findViewById(R.id.totalLotTime);
        textViewDaysRequired=(TextView)rootView.findViewById(R.id.textViewDaysRequired);

        //Update current views
        updateAssumptions();
        //Before anything, update batch size
        updateBatchSize();
        //First update number of machines per station, then adjust capacity
        updateViews();
        updateCapacity();
        updateTimePerLot();


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Update Assumptions
                updateAssumptions();
                //Activate update function
                updateBatchSize();
                //First update number of machines per station
                updateViews();
                //Then update Capacity per station
                updateCapacity();
                //Then update time per lot
                updateTimePerLot();
            }
        });


        simulate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runSimulation();
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

    private void updateCapacity(){

        //Machine 1
        capacityPerDayStation1=24/(batchSize*runTimeStation1+setupTimeStation1)*batchSize*Station1NumberMachines;
        DecimalFormat decimalFormat= new DecimalFormat("###,###.##");
        textViewMachineCapacityDaily1.setText(decimalFormat.format(capacityPerDayStation1));
        //Machine 2
        capacityPerDayStation2=24/(batchSize*runTimeStation2+setupTimeStation2)*batchSize*Station2NumberMachines;
        textViewMachineCapacityDaily2.setText(decimalFormat.format(capacityPerDayStation2));
        //Machine 3
        capacityPerDayStation3=24/(batchSize*runTimeStation3+setupTimeStation3)*batchSize*Station3NumberMachines;
        textViewMachineCapacityDaily3.setText(decimalFormat.format(capacityPerDayStation3));
        //Machine 4
        capacityPerDayStation4=24/(batchSize*runTimeStation4+setupTimeStation4)*batchSize*Station2NumberMachines;
        textViewMachineCapacityDaily4.setText(decimalFormat.format(capacityPerDayStation4));
    }

    private void updateTimePerLot(){
        //Machine 1
        timePerLotStation1=setupTimeStation1+runTimeStation1*batchSize;
        textViewStation1TimePerLot.setText((String.valueOf(timePerLotStation1)));
        //Machine 2
        timePerLotStation2=setupTimeStation2+runTimeStation2*batchSize;
        textViewStation2TimePerLot.setText((String.valueOf(timePerLotStation2)));
        //Machine 3
        timePerLotStation3=setupTimeStation3+runTimeStation3*batchSize;
        textViewStation3TimePerLot.setText((String.valueOf(timePerLotStation3)));
        //Machine 4
        timePerLotStation4=setupTimeStation4+runTimeStation4*batchSize;
        textViewStation4TimePerLot.setText((String.valueOf(timePerLotStation4)));

        totalLotTime=timePerLotStation1+.5*timePerLotStation2+timePerLotStation3+timePerLotStation4*.5;
        textViewTotalLotTime.setText(String.valueOf(totalLotTime)+" hours");
    }

    private void updateBatchSize(){
        batchSize=Integer.parseInt(editTextBatchSize.getText().toString());
    }

    private void updateAssumptions(){
        demandArrivalMin=Double.parseDouble(editTextDemandMin.getText().toString());
        demandArrivalMax=Double.parseDouble(editTextDemandMax.getText().toString());
        lengthOfDay=Double.parseDouble(editTextLengthOfDay.getText().toString());
        contractMin=Double.parseDouble(editTextContractMin.getText().toString());
        contractMax=Double.parseDouble(editTextContractMax.getText().toString());
        revenueMax=Double.parseDouble(editTextRevenueHourlyLoss.getText().toString());
    }

    private void runSimulation(){
        //Update current views
        updateAssumptions();
        //Before anything, update batch size
        updateBatchSize();
        //First update number of machines per station, then adjust capacity
        updateViews();
        updateCapacity();
        updateTimePerLot();
        //Run-------------------------------
        daysToSimulate=Integer.parseInt(editTextDaysToSimulate.getText().toString());



    //Average Demand for each day
        int [] demandArrivalPerDay= new int[daysToSimulate];
        Random rn= new Random();
        int min= (int)demandArrivalMin;
        int max= (int)demandArrivalMax;

        double [] revenueAverages= new double[daysToSimulate];
        for (int j=0; j<daysToSimulate; j++) {


            for (int i = 0; i < demandArrivalPerDay.length; i++) {
                demandArrivalPerDay[i] = rn.nextInt((max - min) + 1) + min;
            }

            minLeadTime = 24 / (timePerLotStation1 + .5* timePerLotStation2 + timePerLotStation3 + .5*timePerLotStation4);
            maxLeadTime = minLeadTime + .2;
            //Average Lead time for each day
            double[] leadTimePerDay = new double[daysToSimulate];

            for (int i = 0; i < leadTimePerDay.length; i++) {
                leadTimePerDay[i] = minLeadTime + (maxLeadTime - minLeadTime) * rn.nextDouble();
            }

            //Revenue Calculation
            double[] revenuePerDay = new double[daysToSimulate];
            revenueHourlyLoss = revenueMax / ((contractMax - contractMin) * 24);


            for (int i = 0; i < revenuePerDay.length; i++) {
                if (leadTimePerDay[i] > contractMax) {
                    revenuePerDay[i] = 0;
                } else if (leadTimePerDay[i] < contractMin) {
                    revenuePerDay[i] = revenueMax * demandArrivalPerDay[i];
                } else {
                    revenuePerDay[i] = (revenueMax - (leadTimePerDay[i] * 24 - (contractMin * 24)) * revenueHourlyLoss) * demandArrivalPerDay[i];
                }
            }

            totalRevenue = 0;
            for (int i = 0; i < revenuePerDay.length; i++) {
                totalRevenue += revenuePerDay[i];
            }
            revenueAverages[j]+=totalRevenue;
        }// end for revenue max

        double revenuesAveraged=0;
        for (int i=0; i<revenueAverages.length; i++){
            revenuesAveraged+=revenueAverages[i];
        }
        revenuesAveraged/=daysToSimulate;

        DecimalFormat decimalFormat= new DecimalFormat("###,###.##");
        textviewTotalRevenue.setText(decimalFormat.format(revenuesAveraged));

        daysRequired=daysToSimulate*24/60; // $total cash/ 24 seconds/day
        textViewDaysRequired.setText(decimalFormat.format(daysRequired));

    }// end runSimulation

}//End MainActivityFragment
