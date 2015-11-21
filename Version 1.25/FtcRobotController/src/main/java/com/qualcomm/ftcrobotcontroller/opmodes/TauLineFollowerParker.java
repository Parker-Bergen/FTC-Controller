package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;


/**
 * Created by Pbergen on 9/28/2015.
 */
public class TauLineFollowerParker extends OpMode
{
    DcMotor motorRight1;
    DcMotor motorRight2;
    DcMotor motorLeft1;
    DcMotor motorLeft2;
    Servo servo1;
    UltrasonicSensor DistanceSensor;
    ColorSensor lineSensor;
    String side = "right";


    public TauLineFollowerParker()
    {
    }


    @Override
    public void init()
    {
        motorRight1 = hardwareMap.dcMotor.get("Left1");
        motorRight2 = hardwareMap.dcMotor.get("Left2");
        motorLeft1 = hardwareMap.dcMotor.get("Right1");
        motorLeft2 = hardwareMap.dcMotor.get("Right2");
        DistanceSensor = hardwareMap.ultrasonicSensor.get("Distance");
        motorRight1.setDirection(DcMotor.Direction.REVERSE);
        motorRight2.setDirection(DcMotor.Direction.REVERSE);

        servo1 = hardwareMap.servo.get("servo");
        lineSensor = hardwareMap.colorSensor.get("Line");
        side = "right";
    }
//-------------------------------------------------------------------------------------------------------------------------------------
    public boolean onLine()
    {
        if (lineSensor.red() >= 50)
        {
            return true;
        }

        else
        {
            return false;
        }
    }

    public void lineFollower()
    {
        telemetry.addData("Color Value", lineSensor.red());
        if(onLine() && side.equals("right"))
        {
            motorRight1.setPower(0.3);
            motorRight2.setPower(0.3);
            motorLeft1.setPower(0);
            motorLeft2.setPower(0);
            side = "left";
        }

        if(onLine() && side.equals("left"))
        {
            motorRight1.setPower(0);
            motorRight2.setPower(0);
            motorLeft1.setPower(0.3);
            motorLeft2.setPower(0.3);
            side = "right";
        }

        else if(side.equals("right"))
        {
            motorRight1.setPower(0.3);
            motorRight2.setPower(0.3);
            motorLeft1.setPower(0);
            motorLeft2.setPower(0);
        }

        else if(side.equals("left"))
        {
            motorRight1.setPower(0);
            motorRight2.setPower(0);
            motorLeft1.setPower(0.3);
            motorLeft2.setPower(0.3);
        }
    }
//-------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public void loop()
    {
    }

    @Override
    public void stop()
    {
    }
}