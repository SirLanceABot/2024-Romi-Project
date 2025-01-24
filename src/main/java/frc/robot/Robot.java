package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.MyCommands;
import frc.robot.controls.TriggerBindings;

class Robot extends TimedRobot
{
    private final RobotContainer robotContainer = new RobotContainer();
    private Command autonomousCommand = null;
    
    Robot()
    {
        System.out.println("Hello World!");

        MyCommands.createMyCommands(robotContainer);
        TriggerBindings.createBindings(robotContainer);
    }

    @Override
    public void robotPeriodic()
    {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void disabledInit()
    {
        System.out.println("Disabled Mode");
        SmartDashboard.putString("Mode", "Disabled");
    }

    @Override
    public void disabledPeriodic()
    {}

    @Override
    public void disabledExit()
    {}

    @Override
    public void autonomousInit()
    {
        System.out.println("Autonomous Mode");
        SmartDashboard.putString("Mode", "Autonomous");

        autonomousCommand = MyCommands.autonomousDriveAndSpinCommand();

        if(autonomousCommand != null)
            autonomousCommand.schedule();

        // MyCommands.drive3SecondsCommand()
        //     .schedule();

        // Commands.sequence(

        //     Commands.runOnce(
        //         () -> romiDrivetrain.arcadeDrive(0.5, 0.0),
        //         romiDrivetrain
        //     ),

        //     Commands.waitSeconds(3.0),

        //     Commands.runOnce(
        //         () -> romiDrivetrain.stopDrive(),
        //         romiDrivetrain
        //     )

        // ).schedule();

        // Commands.runOnce( () -> romiDrivetrain.arcadeDrive(0.5, 0.0), romiDrivetrain )
        //     .andThen( Commands.waitSeconds(3.0) )
        //     .andThen( Commands.runOnce( () -> romiDrivetrain.stopDrive(), romiDrivetrain) )
        //     .schedule();

        // Commands.run( () -> { leftMotor.set(0.15); rightMotor.set(0.15); } ).schedule();

        // Commands.waitSeconds(3.0)
        //     .andThen( Commands.runOnce( () -> { leftMotor.set(0.0); rightMotor.set(0.0); } ) ).schedule();
    }

    @Override
    public void autonomousPeriodic()
    {}

    @Override
    public void autonomousExit()
    {
        if(autonomousCommand != null)
        {
            autonomousCommand.cancel();
            autonomousCommand = null;
        }
    }

    @Override
    public void teleopInit()
    {
        System.out.println("Teleop Mode");
        SmartDashboard.putString("Mode", "Teleop");
    }

    @Override
    public void teleopPeriodic()
    {}

    @Override
    public void teleopExit()
    {}

    @Override
    public void testInit()
    {
        System.out.println("Test Mode");
        SmartDashboard.putString("Mode", "Test");
    }

    @Override
    public void testPeriodic()
    {}

    @Override
    public void testExit()
    {}
}
