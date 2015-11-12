package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.Range;


/**
 * Created by Pbergen on 9/28/2015.
 */
public class TauAutonomous extends OpMode
{
    DcMotor motorRight1;
    DcMotor motorRight2;
    DcMotor motorLeft1;
    DcMotor motorLeft2;
    Servo servo1;
    UltrasonicSensor DistanceSensor;
    ColorSensor lineSensor;

    double s1position;
    boolean onLine;
    long now;


    public TauAutonomous()
    {
        s1position = 0.0d;
        onLine = false;
        now = 0L;
    }

    public void sleep(long millis)
    {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public double elapsedTime(long start)
    {
        now = System.currentTimeMillis();
        return (now - start) / 1000.0;
    }

    public void turnLeft(int seconds)
    {
        long target = System.currentTimeMillis() + seconds * 1000;
        motorRight1.setPower(0.5);
        motorRight2.setPower(0.5);
        motorLeft1.setPower(0);
        motorLeft2.setPower(0);

        while (!onLine)
        {
            if (lineSensor.red() < 50)
                onLine = false;
            if (lineSensor.red() >= 50)
                onLine = true;

            telemetry.addData("Text", "*** Robot Data***");
            //telemetry.addData("DS","DS"+ String.format("%.2f", DistanceSensor));
            telemetry.addData("DS",  "DS: " + DistanceSensor.getUltrasonicLevel());
            telemetry.addData("Servo",  "Servo: " + servo1.getPosition());

            if (System.currentTimeMillis() >= target)
                turnRight(seconds);
        }

        motorRight1.setPower(0);
        motorRight2.setPower(0);
        motorLeft1.setPower(0);
        motorLeft2.setPower(0);
    }

    public void turnRight(int seconds)
    {
        long target = System.currentTimeMillis() + seconds * 1000;

        motorRight1.setPower(0);
        motorRight2.setPower(0);
        motorLeft1.setPower(0.5);
        motorLeft2.setPower(0.5);

        while (!onLine)
        {
            if (lineSensor.red() < 50)
                onLine = false;
            if (lineSensor.red() >= 50)
                onLine = true;

            telemetry.addData("Text", "*** Robot Data***");
            //telemetry.addData("DS","DS"+ String.format("%.2f", DistanceSensor));
            telemetry.addData("DS",  "DS: " + DistanceSensor.getUltrasonicLevel());
            telemetry.addData("Servo",  "Servo: " + servo1.getPosition());
        }

        motorRight1.setPower(0);
        motorRight2.setPower(0);
        motorLeft1.setPower(0);
        motorLeft2.setPower(0);
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
    }

    @Override
    public void loop()
    {
        if (lineSensor.red() < 50)
            onLine = false;
        if (lineSensor.red() >= 50)
            onLine = true;

        /*motorRight1.setPower(0);
        motorRight2.setPower(0);
        motorLeft1.setPower(0);
        motorLeft2.setPower(0);

        sleep(1000); // sleeps for one second

        motorRight1.setPower(1);
        motorRight2.setPower(1);
        motorLeft1.setPower(1);
        motorLeft2.setPower(1);

        sleep(1000);

        motorRight1.setPower(1);
        motorRight2.setPower(1);
        motorLeft1.setPower(0);
        motorLeft2.setPower(0);

        sleep(1000);

        motorRight1.setPower(0);
        motorRight2.setPower(0);
        motorLeft1.setPower(1);
        motorLeft2.setPower(1);

        sleep(1000);*/

        if (onLine)
        {
            motorRight1.setPower(0.5);
            motorRight2.setPower(0.5);
            motorLeft1.setPower(0.5);
            motorLeft2.setPower(0.5);
        }
        else
        {
            turnLeft(2);
        }

        telemetry.addData("Text", "*** Robot Data***");
        //telemetry.addData("DS","DS"+ String.format("%.2f", DistanceSensor));
        telemetry.addData("DS",  "DS: " + DistanceSensor.getUltrasonicLevel());
        telemetry.addData("Servo",  "Servo: " + servo1.getPosition());
    }

    @Override
    public void stop()
    {
    }
}