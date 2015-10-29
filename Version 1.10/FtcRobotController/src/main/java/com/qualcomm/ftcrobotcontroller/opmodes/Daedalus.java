package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by Pbergen on 8/4/2015.
 */
public class Daedalus extends OpMode
{
    DcMotor motorRight1;
    DcMotor motorRight2;
    DcMotor motorRight3;
    DcMotor motorLeft1;
    DcMotor motorLeft2;
    DcMotor motorLeft3;


    DcMotorController mainc;




    public Daedalus()
    {
    }

    @Override
    public void init()
    {
        mainc = hardwareMap.dcMotorController.get("Controller_C");
        motorRight1 = hardwareMap.dcMotor.get("Right1");
        motorRight2 = hardwareMap.dcMotor.get("Right2");
        motorRight2 = hardwareMap.dcMotor.get("Right3");
        motorLeft1 = hardwareMap.dcMotor.get("Left1");
        motorLeft2 = hardwareMap.dcMotor.get("Left2");
        motorRight2 = hardwareMap.dcMotor.get("Left3");
        motorLeft1.setDirection(DcMotor.Direction.REVERSE);
        motorLeft2.setDirection(DcMotor.Direction.REVERSE);
        motorLeft3.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop()
    {
        float left = gamepad1.left_stick_y;
        float right = gamepad1.right_stick_y;

        right = Range.clip(right, -1, 1);
        left = Range.clip(left, -1, 1);

        right = (float)scaleInput(right);
        left =  (float)scaleInput(left);

        motorRight1.setPower(right);
        motorRight2.setPower(right);
        motorRight3.setPower(right);
        motorLeft1.setPower(left);
        motorLeft2.setPower(left);
        motorLeft3.setPower(left);

        if (gamepad1.left_bumper) {
            // if the A button is pushed on gamepad1, increment the position of
            // the arm servo.
        }

        if (gamepad1.right_bumper) {
            // if the Y button is pushed on gamepad1, decrease the position of
            // the arm servo
        }


        if (gamepad2.left_bumper)
        {

        }

        if (gamepad2.right_bumper)
        {

        }

        if (gamepad2.y)
        {

        }

        else
        {

        }


    }

    @Override
    public void stop()
    {
    }

    double scaleInput(double dVal)  {
        double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };

        // get the corresponding index for the scaleInput array.
        int index = (int) (dVal * 16.0);
        if (index < 0) {
            index = -index;
        } else if (index > 16) {
            index = 16;
        }

        double dScale = 0.0;
        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }

        return dScale;
    }

}
