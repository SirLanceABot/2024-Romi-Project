package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class RomiDrivetrain extends SubsystemBase
{
    private final Spark leftMotor = new Spark(0);
    private final Spark rightMotor = new Spark(1);
    private final DifferentialDrive differentialDrive = new DifferentialDrive(leftMotor, rightMotor);

    public RomiDrivetrain()
    {
        rightMotor.setInverted(true);
        differentialDrive.setSafetyEnabled(false);
    }

    public void arcadeDrive(double driveSpeed, double rotationSpeed)
    {
        differentialDrive.arcadeDrive(driveSpeed, rotationSpeed);
    }

    public void arcadeDrive(DoubleSupplier driveSpeedSupplier, DoubleSupplier rotationSpeedSupplier)
    {
        arcadeDrive(driveSpeedSupplier.getAsDouble(), rotationSpeedSupplier.getAsDouble());
    }

    public void stopDrive()
    {
        differentialDrive.stopMotor();
    }

    public Command stopDriveCommand()
    {
        return runOnce( this::stopDrive );
    }

    public Command arcadeDriveCommand(DoubleSupplier driveSpeedSupplier, DoubleSupplier rotationSpeedSupplier)
    {
        return run( () -> arcadeDrive(driveSpeedSupplier, rotationSpeedSupplier) );
    }

    public Command autonomousDriveCommand(double driveSpeed, double driveTimeSeconds)
    {
        return
        arcadeDriveCommand( () -> driveSpeed, () -> 0.0)
            .withTimeout(driveTimeSeconds)
        .andThen(stopDriveCommand());
    }

    public Command autonomousSpinCommand(double spinSpeed, double spinTimeSeconds)
    {
        return
        arcadeDriveCommand( () -> 0.0, () -> spinSpeed )
            .withTimeout(spinTimeSeconds)
        .andThen(stopDriveCommand());
    }

    private Runnable onlyDriveBackwardRunnable(DoubleSupplier driveSpeedSupplier)
    {
        return
        () -> arcadeDrive(
            () -> MathUtil.clamp(driveSpeedSupplier.getAsDouble(), -1.0, 0.0),
            () -> 0.0
        );
    }

    public Command onlyDriveBackwardCommand(DoubleSupplier driveSpeedSupplier)
    {
        return run( onlyDriveBackwardRunnable(driveSpeedSupplier) );
    }
}
