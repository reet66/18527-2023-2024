package org.firstinspires.ftc.teamcode;

// import all the library's needed

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.concurrent.TimeUnit;


@TeleOp(name="Dave Code 1.7.2", group="Iterative Opmode")
public class Main extends OpMode
{
    //the wheels :0
    private DcMotor LF_Motor;
    private DcMotor LB_Motor;
    private DcMotor RF_Motor;
    private DcMotor RB_Motor;
    //plane launcher
    private Servo Plane_Servo;
    //linear actuator
    private DcMotor LA_Motor;
    private DcMotor LA_Rotation;
    //Intake System
    private CRServo Intake_Servo;
    //Claw
    private Servo Claw_Servo;
    private Servo Claw_Joint;
    private DcMotor LS_Motor;

    public void init() {
        //declare that the motors really exist
        LF_Motor = hardwareMap.dcMotor.get("LF_Motor");
        LB_Motor = hardwareMap.dcMotor.get("LB_Motor");
        RF_Motor = hardwareMap.dcMotor.get("RF_Motor");
        RB_Motor = hardwareMap.dcMotor.get("RB_Motor");

        //Linear actuator setup
        LA_Motor = hardwareMap.dcMotor.get("LA_Motor");
        LA_Rotation = hardwareMap.dcMotor.get("LA_Rotation");

        LA_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LA_Rotation.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Airplane launcher
        Plane_Servo = hardwareMap.get(Servo.class, "Plane_Servo");
        //Claw
        Claw_Servo = hardwareMap.get(Servo.class, "Claw_Servo");
        //Intake system
        Intake_Servo = hardwareMap.get(CRServo.class, "Intake_Servo");
        //Linear Slide
        LS_Motor = hardwareMap.dcMotor.get("LS_Motor");
        //Claw Joint
        Claw_Joint = hardwareMap.get(Servo.class, "Claw_Joint");

        //reverse the right motors, because... idk
        RF_Motor.setDirection(DcMotorSimple.Direction.REVERSE);
        RB_Motor.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    @Override
    public void loop() {
        //gamepad input to variable
        double y = gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x * 1.1;
        double rx = -gamepad1.right_stick_x;

        //gamepad 2 input to varible
        double Linear_Slide = gamepad2.left_stick_y;

        int Extended_Position = 9400;
        int Lowered_Position = 0;
        int Rotated_position = -96;

        //Drive Motors
        //math to determine the speed and direction
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double LF_Motor_Power = (y + x + rx) / denominator;
        double LB_Motor_Power = (y - x + rx) / denominator;
        double RF_Motor_Power = (y - x - rx) / denominator;
        double RB_Motor_Power = (y + x - rx) / denominator;

        //send power to intake servo
        //Claw_Servo.setPower(1);

        //send power to the motors to update their position

        //toggle mode for slow driving
        if (gamepad1.start) {
            LF_Motor.setPower(LF_Motor_Power / 2);
            LB_Motor.setPower(LB_Motor_Power / 2);
            RF_Motor.setPower(RF_Motor_Power / 2);
            RB_Motor.setPower(RB_Motor_Power / 2);
        }
        else {
            LF_Motor.setPower(LF_Motor_Power);
            LB_Motor.setPower(LB_Motor_Power);
            RF_Motor.setPower(RF_Motor_Power);
            RB_Motor.setPower(RB_Motor_Power);
        }

        //servos, motors and other stuff

        //!!!DO NOT TOUCH THIS CODE!!!
        //I have no clue how this works but it scares me and if you touch it, it might break
        //resets the position of servo
        if (gamepad1.y) {
            Plane_Servo.setPosition(Plane_Servo.getPosition()+90);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Plane_Servo.setPosition(Plane_Servo.getPosition()-90);
        }
        else {
            Plane_Servo.setPosition(Plane_Servo.getPosition()+0);
        }
        //!!!DO NOT TOUCH THIS CODE!!!

        //linear actuator
        if (gamepad1.dpad_down) {LA_Rotation.setTargetPosition(Lowered_Position);
            LA_Rotation.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            LA_Rotation.setPower(0.5);
        }
        if (gamepad1.dpad_up) {
            LA_Motor.setTargetPosition(1500);
            LA_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            LA_Motor.setPower(1);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            LA_Rotation.setTargetPosition(Rotated_position);
            LA_Rotation.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            LA_Rotation.setPower(0.5);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            LA_Motor.setTargetPosition(0);
            LA_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            LA_Motor.setPower(0.3);
        }
        //linear actuator up down motion
        if (gamepad1.right_bumper) {
            LA_Motor.setTargetPosition(Lowered_Position);
            LA_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            LA_Motor.setPower(1);
        }
        if (gamepad1.left_bumper) {
            LA_Motor.setTargetPosition(Extended_Position);
            LA_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            LA_Motor.setPower(1);
        }

        //Linear slide System
        if (gamepad2.dpad_up) {
            LS_Motor.setPower(1);
        }
        else {
            LS_Motor.setPower(0);
        }
        if (gamepad2.dpad_down) {
            LS_Motor.setPower(-1);
        }
        else {
            LS_Motor.setPower(0);
        }

        //Claw Related Code
        //Intake System
        Intake_Servo.setDirection(DcMotorSimple.Direction.FORWARD);
        //Claw controls for gamepad
        if (gamepad2.b) {
            Claw_Servo.setPosition(0);
        }
        if(gamepad2.a) {
            Claw_Servo.setPosition(0.5);
        }
        //Claw Joint
        if (gamepad2.left_bumper) {
            Claw_Joint.setPosition(0.5);
        }
        if (gamepad2.right_bumper) {
            Claw_Joint.setPosition(0.5);
        }

        // Get the current position of the armMotor
        double current_position_telemetry = LA_Motor.getCurrentPosition();
        double extended_position_telemetry = LA_Motor.getTargetPosition(); //I hate making variable names, its going to be the death of me.

        telemetry.addData("Encoder Position", current_position_telemetry);
        telemetry.addData("Final Position", extended_position_telemetry);
        telemetry.update();
    }
}