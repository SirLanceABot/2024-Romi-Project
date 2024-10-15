package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.RomiDrivetrain;

public class ArcadeDriveCommand extends Command
{
    private final DoubleSupplier driveSpeedSupplier;
    private final DoubleSupplier rotationSpeedSupplier;
    private final RomiDrivetrain romiDrivetrain;

    public ArcadeDriveCommand(DoubleSupplier driveSpeedSupplier,
                                DoubleSupplier rotationSpeedSupplier,
                                RomiDrivetrain romiDrivetrain)
    {
        this.driveSpeedSupplier = driveSpeedSupplier;
        this.rotationSpeedSupplier = rotationSpeedSupplier;
        this.romiDrivetrain = romiDrivetrain;

        addRequirements(romiDrivetrain);
    }

    @Override
    public void initialize()
    {}

    @Override
    public void execute()
    {
        romiDrivetrain.arcadeDrive(driveSpeedSupplier, rotationSpeedSupplier);
    }

    @Override
    public void end(boolean interrupted)
    {
        romiDrivetrain.arcadeDrive(0.0, 0.0);
    }

    @Override
    public boolean isFinished()
    {
        return false;
    }
}
