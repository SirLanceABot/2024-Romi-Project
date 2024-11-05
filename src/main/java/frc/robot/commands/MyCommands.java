package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.RobotContainer;
import frc.robot.subsystems.RomiDrivetrain;
import frc.robot.subsystems.RomiLED;

public abstract class MyCommands
{
    private static RomiDrivetrain romiDrivetrain;
    private static RomiLED greenLED;
    private static RomiLED redLED;

    private MyCommands()
    {}

    public static void createMyCommands(RobotContainer robotContainer)
    {
        romiDrivetrain = robotContainer.getRomiDrivetrain();
        greenLED = robotContainer.getGreenLED();
        redLED = robotContainer.getRedLED();
    }

    public static Command arcadeDriveCommand(DoubleSupplier driveSpeedSupplier, DoubleSupplier rotationSpeedSupplier)
    {
        return romiDrivetrain.arcadeDriveCommand(driveSpeedSupplier, rotationSpeedSupplier);
    }

    public static Command drive3SecondsCommand()
    {
        return
        redLED.offCommand()
            .andThen( greenLED.onCommand() )
            .andThen( Commands.runOnce( () -> romiDrivetrain.arcadeDrive(0.5, 0.0), romiDrivetrain ) )
            .andThen( Commands.waitSeconds(3.0) )
            .andThen( Commands.runOnce( () -> romiDrivetrain.stopDrive(), romiDrivetrain) )
            .andThen( greenLED.offCommand() )
            .andThen( redLED.onCommand() );
    }
}
