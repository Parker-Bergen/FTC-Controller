package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;
import com.qualcomm.robotcore.util.Range;


/**
 * Created by Pbergen on 8/4/2015.
 */
public class TauDemo extends OpMode
{
    DcMotor motorRight1;
    DcMotor motorRight2;
    DcMotor motorLeft1;
    DcMotor motorLeft2;
    Servo servo1;
    UltrasonicSensor DistanceSensor;

    double s1position = 0;


    public TauDemo()
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


        if (DistanceSensor.getUltrasonicLevel () <= 27.0 && DistanceSensor.getUltrasonicLevel () >= 0.0 && left >0 && right >0)
        {
            motorRight1.setPower(0);
            motorRight2.setPower(0);
            motorLeft1.setPower(0);
            motorLeft2.setPower(0);

        }

        else
        {
            motorRight1.setPower(right);
            motorRight2.setPower(right);
            motorLeft1.setPower(left);
            motorLeft2.setPower(left);
        }


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
            s1position = 1.0;
        }

        if (gamepad2.a)
        {
            s1position = -1.0;
        }

        else
        {

        }
        telemetry.addData("Text", "*** Robot Data***");
        //telemetry.addData("DS","DS"+ String.format("%.2f", DistanceSensor));
        telemetry.addData("DS",  "DS: " + DistanceSensor.getUltrasonicLevel () );
        telemetry.addData("Servo",  "Servo: " + servo1.getPosition () );

        servo1.setPosition(s1position);

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
