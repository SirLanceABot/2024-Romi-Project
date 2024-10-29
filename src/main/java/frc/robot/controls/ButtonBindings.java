package frc.robot.controls;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.RobotContainer;
import frc.robot.commands.ArcadeDriveCommand;
import frc.robot.commands.MyCommands;
import frc.robot.subsystems.RomiDrivetrain;

public abstract class ButtonBindings 
{
    private static CommandXboxController xbox;
    private static RomiDrivetrain romiDrivetrain;
    private static DoubleSupplier leftYAxisSupplier;
    private static DoubleSupplier leftXAxisSupplier;

    private ButtonBindings()
    {}

    public static void createBindings(RobotContainer robotContainer)
    {
        xbox = robotContainer.getXbox();
        romiDrivetrain = robotContainer.getRomiDrivetrain();

        configSuppliers();
        configDefaultCommands();
    }

    private static void configSuppliers()
    {
        leftYAxisSupplier = () -> -xbox.getRawAxis(1);
        leftXAxisSupplier = () -> -xbox.getRawAxis(0);
    }

    private static void configDefaultCommands()
    {
        if(romiDrivetrain != null)
        {
            romiDrivetrain.setDefaultCommand(
                Commands.runEnd(
                    () -> romiDrivetrain.arcadeDrive(leftYAxisSupplier, leftXAxisSupplier),
                    () -> { romiDrivetrain.stopDrive(); System.out.println("Stopped"); },
                    romiDrivetrain
                )
                // MyCommands.arcadeDriveCommand(leftYAxisSupplier, leftXAxisSupplier)
                // romiDrivetrain.arcadeDriveCommand(leftYAxisSupplier, leftXAxisSupplier)
                // new ArcadeDriveCommand(leftYAxisSupplier, leftXAxisSupplier, romiDrivetrain)
                // Commands.runOnce(
                //     () -> romiDrivetrain.arcadeDrive(leftYAxisSupplier, leftXAxisSupplier), 
                //     romiDrivetrain
                // )
            );
        }
    }
}
