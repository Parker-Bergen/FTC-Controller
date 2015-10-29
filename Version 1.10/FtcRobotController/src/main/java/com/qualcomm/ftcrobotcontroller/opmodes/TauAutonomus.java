package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
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
    double dis = 0;


    public TauAutonomus()
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
    }

    @Override
    public void loop()
    {
        if (DistanceSensor.getUltrasonicLevel() <= 27.0 && DistanceSensor.getUltrasonicLevel() >= 0.0)
        {
            motorRight1.setPower(0);
            motorRight2.setPower(0);
            motorLeft1.setPower(0);
            motorLeft2.setPower(0);

        }

        else
        {
            motorRight1.setPower(1);
            motorRight2.setPower(1);
            motorLeft1.setPower(1);
            motorLeft2.setPower(1);
        }
        telemetry.addData("Text", "*** Robot Data***");
        //telemetry.addData("DS","DS"+ String.format("%.2f", DistanceSensor));
        telemetry.addData("DS",  "DS: " + DistanceSensor.getUltrasonicLevel () );


    }

    @Override
    public void stop()
    {
    }

}