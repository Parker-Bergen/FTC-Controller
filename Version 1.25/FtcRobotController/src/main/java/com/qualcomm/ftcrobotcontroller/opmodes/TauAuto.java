package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;


/**
 * Created by Pbergen on 9/28/2015.
 */
public class TauAuto extends OpMode
{
    DcMotor motorRight1;
    DcMotor motorRight2;
    DcMotor motorLeft1;
    DcMotor motorLeft2;
    Servo servo1;
    UltrasonicSensor DistanceSensor;
    ColorSensor lineSensor;
    String side = "right";
    int stage;


    public TauAuto()
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
        stage = 0;
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

//-------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public void loop()
    {
        telemetry.addData("Encoder Left", motorLeft1.getCurrentPosition());
        telemetry.addData("Encoder Right", motorRight1.getCurrentPosition());
        telemetry.addData("Color Value", lineSensor.red());
        telemetry.addData("Encoder Mode", motorLeft1.getMode());
        telemetry.addData("Stages", stage);

        if(stage ==0)
        {
            if(encodersReached(5))
            {
                driveForward();
            }


        }
        if(stage == 5)
        {
            if (onLine() && side.equals("right")) {
                motorRight1.setPower(0.3);
                motorRight2.setPower(0.3);
                motorLeft1.setPower(0);
                motorLeft2.setPower(0);
                side = "left";
            }

            if (onLine() && side.equals("left")) {
                motorRight1.setPower(0);
                motorRight2.setPower(0);
                motorLeft1.setPower(0.3);
                motorLeft2.setPower(0.3);
                side = "right";
            } else if (side.equals("right")) {
                motorRight1.setPower(0.3);
                motorRight2.setPower(0.3);
                motorLeft1.setPower(0);
                motorLeft2.setPower(0);
            } else if (side.equals("left")) {
                motorRight1.setPower(0);
                motorRight2.setPower(0);
                motorLeft1.setPower(0.3);
                motorLeft2.setPower(0.3);
            }
        }
    }

    public void reset_encoders()
    {
        motorRight1.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorRight2.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorLeft1.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorLeft2.setMode(DcMotorController.RunMode.RESET_ENCODERS);
    }

    public void revert_encoders()
    {
        motorRight1.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motorRight2.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motorLeft1.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motorLeft2.setMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
    }

    public void driveForward()
    {
        motorRight1.setPower(1);
        motorRight2.setPower(1);
        motorLeft1.setPower(1);
        motorLeft2.setPower(1);
    }

    public void turnRight()
    {
        motorRight1.setPower(1);
        motorRight2.setPower(1);
        motorLeft1.setPower(-1);
        motorLeft2.setPower(-1);
    }

    public void turnLeft()
    {
        motorRight1.setPower(-1);
        motorRight2.setPower(-1);
        motorLeft1.setPower(1);
        motorLeft2.setPower(1);
    }

    public void stopMotors()
    {
        motorRight1.setPower(0);
        motorRight2.setPower(0);
        motorLeft1.setPower(0);
        motorLeft2.setPower(0);
    }

    public boolean encodersReached(double encoderCount)
    {
        encoderCount = encoderCount*1120;

        if(motorLeft1. getCurrentPosition() >= encoderCount || motorRight1.getCurrentPosition() <= -encoderCount)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public void stop()
    {
    }
}