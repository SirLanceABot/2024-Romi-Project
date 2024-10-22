package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.subsystems.RomiDrivetrain;

public abstract class MyCommands
{
    private static RomiDrivetrain romiDrivetrain;

    private MyCommands()
    {}

    public static void createMyCommands(RobotContainer robotContainer)
    {
        romiDrivetrain = robotContainer.getRomiDrivetrain();
    }

    public static Command arcadeDriveCommand(DoubleSupplier driveSpeedSupplier, DoubleSupplier rotationSpeedSupplier)
    {
        return romiDrivetrain.arcadeDriveCommand(driveSpeedSupplier, rotationSpeedSupplier);
    }
}
