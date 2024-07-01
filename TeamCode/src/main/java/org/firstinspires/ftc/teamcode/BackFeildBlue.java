package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous (name = "Back Feild Blue")
public class BackFeildBlue extends LinearOpMode {
    private DcMotor LF_Motor;
    private DcMotor LB_Motor;
    private DcMotor RF_Motor;
    private DcMotor RB_Motor;

    @Override
    public void runOpMode() throws InterruptedException {
        //declare that the motors really exist
        LF_Motor = hardwareMap.dcMotor.get("LF_Motor");
        LB_Motor = hardwareMap.dcMotor.get("LB_Motor");
        RF_Motor = hardwareMap.dcMotor.get("RF_Motor");
        RB_Motor = hardwareMap.dcMotor.get("RB_Motor");

        //i still don't know why i do this but it works
        RF_Motor.setDirection(DcMotorSimple.Direction.REVERSE);
        RB_Motor.setDirection(DcMotorSimple.Direction.REVERSE);

        //main code
        waitForStart();
        strafeRight(800);
    }
    public void moveForward (double power, int time){ //public void moveForward (int power, int time){
        LF_Motor.setPower(power);
        LB_Motor.setPower(power);
        RF_Motor.setPower(power);
        RB_Motor.setPower(power);
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        LF_Motor.setPower(0);
        LB_Motor.setPower(0);
        RF_Motor.setPower(0);
        RB_Motor.setPower(0);
    }
    public void strafeLeft (int time) {
        LF_Motor.setPower(1);
        LB_Motor.setPower(-1);
        RF_Motor.setPower(-1);
        RB_Motor.setPower(1);
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        LF_Motor.setPower(0);
        LB_Motor.setPower(0);
        RF_Motor.setPower(0);
        RB_Motor.setPower(0);
    }
    public void strafeRight (int time) {
        LF_Motor.setPower(-1);
        LB_Motor.setPower(1);
        RF_Motor.setPower(1);
        RB_Motor.setPower(-1);
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        LF_Motor.setPower(0);
        LB_Motor.setPower(0);
        RF_Motor.setPower(0);
        RB_Motor.setPower(0);
    }
    public void MotorOff (int time) {
        LF_Motor.setPower(0);
        LB_Motor.setPower(0);
        RF_Motor.setPower(0);
        RB_Motor.setPower(0);
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        LF_Motor.setPower(0);
        LB_Motor.setPower(0);
        RF_Motor.setPower(0);
        RB_Motor.setPower(0);
    }
    public void stop (int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
//while (opModeIsActive()) {
//            // If the distance in centimeters is less than the number after the <, follow instructions following
//            if (right_Distance.getDistance(DistanceUnit.CM) < 50) {
//                moveForward(1,  50);
//                strafeLeft(280);
//                stop(1000000); //don't ask
//            } else {  // Otherwise, stop the motor
//                // If the distance in centimeters is less than the number after the <, follow instructions following
//                if (left_Distance.getDistance(DistanceUnit.CM) < 50) {
//                    moveForward(1, 110);
//                    strafeRight(230);
//                    stop(1000000);//don't ask
//                } else {  // Otherwise, stop the motor
//                    moveForward(1, 20);
//                }
//            }
//        }