package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;
import com.qualcomm.robotcore.util.Range;


/**
 * Created by Pbergen on 8/4/2015.
 */
public class TauHang extends OpMode
{
    DcMotor motorRight1;
    DcMotor motorRight2;
    DcMotor motorLeft1;
    DcMotor motorLeft2;
    DcMotor Hang1;
    DcMotor Hang2;


    double s1position;


    public TauHang()
    {
        s1position = 0.0d;
    }

    public void sleep(long millis)
    {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
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
        Hang1 = hardwareMap.dcMotor.get("hang1");
        Hang2 = hardwareMap.dcMotor.get("hang2");

        motorRight1.setDirection(DcMotor.Direction.REVERSE);
        motorRight2.setDirection(DcMotor.Direction.REVERSE);
        Hang1.setDirection(DcMotor.Direction.REVERSE);


    }

    @Override
    public void loop()
    {
        float right = gamepad1.left_stick_y;
        float left = gamepad1.right_stick_y;

        right = Range.clip(right, -1, 1);
        left = Range.clip(left, -1, 1);

        right = (float)scaleInput(right);
        left =  (float)scaleInput(left);

        motorRight1.setPower(right);
        motorRight2.setPower(right);
        motorLeft1.setPower(left);
        motorLeft2.setPower(left);


        if (gamepad2.left_bumper)
        {
        }

        if (gamepad2.right_bumper)
        {
        }

        if (gamepad2.y)
        {
            s1position = 1.0;
        }

        if (gamepad2.a)
        {
            s1position = -1.0;
        }

        if (gamepad1.a)
        {
            // if the A button is pushed on gamepad1, increment the position of
            // the arm servo.
            Hang1.setPower(-1);
            Hang2.setPower(-1);

        }
        if (gamepad1.y)
        {
            // if the Y button is pushed on gamepad1, decrease the position of
            // the arm servo
            Hang1.setPower(1);
            Hang2.setPower(1);
        }

        if (gamepad1.b)
        {
            // if the Y button is pushed on gamepad1, decrease the position of
            // the arm servo
            Hang1.setPower(0);
            Hang2.setPower(0);
        }
        else
        {
        }

        telemetry.addData("Text", "*** Robot Data***");
        //telemetry.addData("DS","DS"+ String.format("%.2f", DistanceSensor));

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
