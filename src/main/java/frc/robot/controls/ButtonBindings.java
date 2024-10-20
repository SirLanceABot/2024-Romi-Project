package frc.robot.controls;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.RobotContainer;
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
                Commands.run(
                    () -> romiDrivetrain.arcadeDrive(leftYAxisSupplier, leftXAxisSupplier), 
                    romiDrivetrain
                )
            );
        }
    }
}
