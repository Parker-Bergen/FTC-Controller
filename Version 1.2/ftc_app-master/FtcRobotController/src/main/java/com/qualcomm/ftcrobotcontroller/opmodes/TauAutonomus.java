package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;


/**
 * Created by Pbergen on 9/28/2015.
 */
public class TauAutonomus extends OpMode
{
    DcMotor motorRight1;
    DcMotor motorRight2;
    DcMotor motorLeft1;
    DcMotor motorLeft2;
    UltrasonicSensor DistanceSensor;
    ColorSensor colorNXT;
    ColorSensor Line;
    double dis = 0;


    public TauAutonomus()
    {
    }

    public void sleep(long milis)
    {
        try{
            Thread.sleep(milis);
            }catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init()
    {
        motorRight1 = hardwareMap.dcMotor.get("Left1");
        motorRight2 = hardwareMap.dcMotor.get("Left2");
        motorLeft1 = hardwareMap.dcMotor.get("Right1");
        motorLeft2 = hardwareMap.dcMotor.get("Right2");
       DistanceSensor = hardwareMap.ultrasonicSensor.get("Distance");
        colorNXT = hardwareMap.colorSensor.get("colorNXT");
        //colorNXT = hardwareMap.colorSensor.get("Cdet");
        Line = hardwareMap.colorSensor.get("Line");
        colorNXT.enableLed(false);
        Line.enableLed(true);
        motorRight1.setDirection(DcMotor.Direction.REVERSE);
        motorRight2.setDirection(DcMotor.Direction.REVERSE);
    }

    //@Override
    public void loop()
    {
        runforward(2);
        /*right(1);
        foo();
        */nodo();
    }

    public void runforward(int tiles)
    {
        int count =0;
        if(count <= tiles)
        {
            motorRight1.setPower(1);
            motorRight2.setPower(1);
            motorLeft1.setPower(1);
            motorLeft2.setPower(1);
            sleep(1000);
            count++;
        }
        else
        {
            motorRight1.setPower(0);
            motorRight2.setPower(0);
            motorLeft1.setPower(0);
            motorLeft2.setPower(0);
        }
    }

    public void right(int r)
    {
        int count =0;
        while(count <= r)
        {
            motorRight1.setPower(1);
            motorRight2.setPower(1);
            motorLeft1.setPower(0);
            motorLeft2.setPower(0);
        }
        motorRight1.setPower(0);
        motorRight2.setPower(0);
        motorLeft1.setPower(0);
        motorLeft2.setPower(0);
    }

    public void foo()
    {
        int run = 0;
        while(run == 0)
        {
            if (DistanceSensor.getUltrasonicLevel() <= 24.5 && DistanceSensor.getUltrasonicLevel() >= 0.0)
            {
                run = 1;
            }
            else
            {
                motorRight1.setPower(1);
                motorRight2.setPower(1);
                motorLeft1.setPower(1);
                motorLeft2.setPower(1);
            }
        }
        motorRight1.setPower(0);
        motorRight2.setPower(0);
        motorLeft1.setPower(0);
        motorLeft2.setPower(0);
    }

    public void print()
    {
        telemetry.addData("Text", "*** Robot Data***");
        telemetry.addData("DS", "DS: " + DistanceSensor.getUltrasonicLevel());

        if(colorNXT.red()>colorNXT.blue())
        {
            telemetry.addData("Beacon", "Color: Red");
        }
        if(colorNXT.blue()>colorNXT.red())
        {
            telemetry.addData("Beacon", "Color: Blue");
        }
        telemetry.addData("Beacon Values", "Color: " + colorNXT.red() + " " + colorNXT.green() + " " + colorNXT.blue());

        if(Line.red()< 50)
        {
            telemetry.addData("Line", "Color: Black");
        }

        if(Line.red()> 50)
        {

            telemetry.addData("Line", "Color: White");
        }
        telemetry.addData("Line Values", "Color: " + Line.red() + " " + Line.green() + " " + Line.blue());
    }

    //@Override
    //public void stop()
    //{
    //}
    public void nodo()
    {

    }


}